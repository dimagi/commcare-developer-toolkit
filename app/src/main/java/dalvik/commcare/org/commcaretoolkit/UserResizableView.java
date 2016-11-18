package dalvik.commcare.org.commcaretoolkit;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by amstone326 on 11/23/15.
 */
public class UserResizableView extends View {

    private static final double TOUCH_THRESHOLD = 75;
    private static final int X_START = 35;

    private float cornerPositionX;
    private float cornerPositionY;

    private boolean resizeInProcess;
    private float lastResizeTouchX;
    private float lastResizeTouchY;

    private ResizeListener listener;
    private boolean inAspectRatioMode;
    private float originalAspectRatio;

    public UserResizableView(Context context) {
        super(context);
        initRectangle();
    }

    public UserResizableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initRectangle();
    }

    private void initRectangle() {
        // Initialize the resizable rectangle to take up ~1/2 of the available space (can't just
        // use the view's getWidth() or getHeight() because onMeasure() hasn't yet been called)
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        cornerPositionX = metrics.widthPixels / 2;
        // account for the fact that ~1/2 of the screen height is taken up by content that is not
        // the resizable rectangle
        cornerPositionY = metrics.heightPixels / 4;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Pair<Float, Float> userPos = getCurrentUserPosition(ev);
        float userX = userPos.first;
        float userY = userPos.second;

        final int action = MotionEventCompat.getActionMasked(ev);
        switch(action) {
            case MotionEvent.ACTION_DOWN:
                handleUserTouch(userX, userY);
                break;
            case MotionEvent.ACTION_MOVE:
                handleUserDrag(userX, userY);
                break;
            case MotionEvent.ACTION_UP:
                handleUserRelease();
        }
        return true;
    }

    private void handleUserTouch(float x, float y) {
        if (!resizeInProcess && userTouchNearCurrentCorner(x, y)) {
            resizeInProcess = true;
            lastResizeTouchX = x;
            lastResizeTouchY = y;
        }
    }

    private void handleUserDrag(float x, float y) {
        if (resizeInProcess) {

            final float dx = x - lastResizeTouchX;
            final float dy = y - lastResizeTouchY;

            if (inAspectRatioMode) {
                doAspectRatioScale(dx, dy);
            } else {
                doNormalScale(dx, dy);
            }

            invalidate();
            lastResizeTouchX = x;
            lastResizeTouchY = y;
        }
    }

    private void doNormalScale(float dx, float dy) {
        cornerPositionX += dx;
        cornerPositionY += dy;
    }

    private void doAspectRatioScale(float dx, float dy) {
        if (dx > dy) {
            dy = dx * (1/originalAspectRatio);
        } else {
            dx = dy * originalAspectRatio;
        }
        cornerPositionX += dx;
        cornerPositionY += dy;
    }

    private void handleUserRelease() {
        resizeInProcess = false;
    }

    private Pair<Float, Float> getCurrentUserPosition(MotionEvent ev) {
        final int pointerIndex = MotionEventCompat.getActionIndex(ev);
        final float x = MotionEventCompat.getX(ev, pointerIndex);
        final float y = MotionEventCompat.getY(ev, pointerIndex);
        return new Pair<>(x, y);
    }

    private boolean userTouchNearCurrentCorner(float x, float y) {
        return Math.abs(cornerPositionX - x) < TOUCH_THRESHOLD &&
                Math.abs(cornerPositionY - y) < TOUCH_THRESHOLD;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        applyMinAndMaxRequirements();
        redraw(canvas);
        listener.onResize(cornerPositionX - X_START, cornerPositionY);
    }

    private void applyMinAndMaxRequirements() {
        float boundedX = Math.max(getMinAllowedWidth(), Math.min(cornerPositionX, getMaxWidth()));
        float boundedY = Math.max(getMinAllowedHeight(), Math.min(cornerPositionY, getMaxHeight()));
        if (inAspectRatioMode) {
            // If one of the coordinates got constrained, adjust the other to match
            if (boundedX != cornerPositionX) {
                boundedY = boundedX * (1/originalAspectRatio);
            } else if (boundedY != cornerPositionY) {
                boundedX = boundedY * originalAspectRatio;
            }
        }
        cornerPositionX = boundedX;
        cornerPositionY = boundedY;
    }

    public float getMinAllowedHeight() {
        return (float)Math.floor(getMaxHeight() / 10);
    }

    public float getMinAllowedWidth() {
        return (float)Math.floor(getMaxWidth() / 10);
    }

    public float getMaxWidth() {
        return getMeasuredWidth() - (getMeasuredWidth() / 20);
    }

    public float getMaxHeight() {
        return getMeasuredHeight() - (getMeasuredHeight() / 15);
    }

    private float getCircleRadius() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            return getMeasuredWidth() / 45;
        } else {
            return getMeasuredHeight() / 25;
        }
    }

    private void redraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.cc_brand_color));
        canvas.drawRect(X_START, 0, cornerPositionX, cornerPositionY, paint);
        paint.setColor(getResources().getColor(R.color.cc_neutral_color));
        canvas.drawCircle(cornerPositionX, cornerPositionY, getCircleRadius(), paint);
    }

    public void setResizeListener(ResizeListener listener) {
        this.listener = listener;
    }

    private void setLockedAspectRatio(Pair<Float, Float> dimens) {
        inAspectRatioMode = true;
        cornerPositionX = dimens.first;
        cornerPositionY = dimens.second;
        originalAspectRatio = cornerPositionX / cornerPositionY;
        invalidate();
    }

    public void clearAspectRatio() {
        inAspectRatioMode = false;
    }

    /**
     *
     * @return If the user-provided dimensions were valid for this screen
     */
    public boolean processUserProvidedAspectRatio(float width, float height) {
        Pair<Float, Float> lockedDimens = checkValidAspectRatio(width, height);
        if (lockedDimens != null) {
            setLockedAspectRatio(lockedDimens);
            return true;
        }
        return false;
    }

    private Pair<Float, Float> checkValidAspectRatio(float width, float height) {
        if (width > getMaxWidth() || height > getMaxHeight()) {
            // If the entered dimens for the box won't actually fit on screen, scale down
            double widthScaleDownFactor = getMaxWidth() / width;
            double heightScaleDownFactor = getMaxHeight() / height;
            double finalScaleDownFactor = Math.min(widthScaleDownFactor, heightScaleDownFactor);
            width = (float)Math.floor(width * finalScaleDownFactor);
            height = (float)Math.floor(height * finalScaleDownFactor);
        }

        if (width < getMinAllowedWidth() || height < getMinAllowedHeight()) {
            // The (potentially adjusted) display dimens can't be smaller than the min dimens that
            // look reasonable on this screen
            return null;
        }

        return new Pair<>(width, height);
    }

}

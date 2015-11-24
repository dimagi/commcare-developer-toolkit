package dalvik.commcare.org.commcaredevelopertoolkit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by amstone326 on 11/23/15.
 */
public class UserResizableView extends View {

    private static final double TOUCH_THRESHOLD = 75;
    private static final float STARTING_CORNER_POS = 500;

    private float cornerPositionX;
    private float cornerPositionY;

    private boolean resizeInProcess;
    private float lastResizeTouchX;
    private float lastResizeTouchY;

    private ResizeListener listener;

    public UserResizableView(Context context) {
        super(context);
        initRectangle();
    }

    public UserResizableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initRectangle();
    }

    private void initRectangle() {
        cornerPositionX = STARTING_CORNER_POS;
        cornerPositionY = STARTING_CORNER_POS;
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
            Log.i("11/23", "handleUserTouch");
            Log.i("11/23", "User x: " + x);
            Log.i("11/23", "User y: " + y);
            resizeInProcess = true;
            lastResizeTouchX = x;
            lastResizeTouchY = y;
        }
    }

    private void handleUserDrag(float x, float y) {
        if (resizeInProcess) {
            Log.i("11/23", "handleUserDrag");
            Log.i("11/23", "User x: " + x);
            Log.i("11/23", "User y: " + y);
            final float dx = x - lastResizeTouchX;
            final float dy = y - lastResizeTouchY;
            Log.i("11/23", "dx: " + dx);
            Log.i("11/23", "dy: " + dy);

            cornerPositionX += dx;
            cornerPositionY += dy;
            Log.i("11/23", "x position set to: " + cornerPositionX);
            Log.i("11/23", "y position set to: " + cornerPositionY);

            invalidate();

            lastResizeTouchX = x;
            lastResizeTouchY = y;
        }
    }

    private void handleUserRelease() {
        Log.i("11/23", "handleUserRelease");
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
        listener.onResize(cornerPositionX, cornerPositionY);
    }

    private void applyMinAndMaxRequirements() {
        cornerPositionX = Math.max(75, Math.min(cornerPositionX, getMeasuredWidth()-50));
        cornerPositionY = Math.max(75, Math.min(cornerPositionY, getMeasuredHeight()-50));
    }

    private void redraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.cc_brand_color));
        canvas.drawRect(0, 0, cornerPositionX, cornerPositionY, paint);
        paint.setColor(getResources().getColor(R.color.cc_neutral_color));
        canvas.drawCircle(cornerPositionX, cornerPositionY, 30, paint);
    }

    public void setResizeListener(ResizeListener listener) {
        this.listener = listener;
    }

}

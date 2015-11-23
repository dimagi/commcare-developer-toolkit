package dalvik.commcare.org.commcaredevelopertoolkit.utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
        Log.i("11/23", "User touched x: " + userX);
        Log.i("11/23", "User touched y: " + userY);

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
            lastResizeTouchX = y;
        }
    }

    private void handleUserDrag(float x, float y) {
        if (resizeInProcess) {
            final float dx = x - lastResizeTouchX;
            final float dy = y - lastResizeTouchY;
            cornerPositionX += dx;
            cornerPositionY += dy;

            invalidate();

            lastResizeTouchX = x;
            lastResizeTouchY = y;
        }
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
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("black"));
        canvas.drawRect(0, 0, cornerPositionX, cornerPositionY, paint);
        paint.setColor(Color.parseColor("red"));
        canvas.drawCircle(cornerPositionX, cornerPositionY, 30, paint);
    }


}

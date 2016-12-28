package info.nukoneko.android.lib.nkanimation;

import android.graphics.Canvas;
import android.view.MotionEvent;

@SuppressWarnings("WeakerAccess")
public abstract class NKAnimationBaseController {
    private NKAnimationView surfaceView;
    private boolean dismissFlag = false;

    abstract public void onUpdate();
    abstract public void onDraw(Canvas c);

    public NKAnimationBaseController() {
    }

    final protected void setSurfaceView(NKAnimationView surfaceView) {
        this.surfaceView = surfaceView;
    }

    public boolean onTouchEvent(MotionEvent event){
        return true;
    }

    public NKAnimationView getSurfaceView() {
        return surfaceView;
    }

    final protected void dismiss() {
        dismissFlag = true;
    }

    final boolean isDismiss() {
        return dismissFlag;
    }
}

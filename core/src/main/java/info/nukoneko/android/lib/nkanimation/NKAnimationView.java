package info.nukoneko.android.lib.nkanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public final class NKAnimationView extends SurfaceView
        implements SurfaceHolder.Callback, Runnable {
    private int canvasColor = Color.BLACK;
    private final NKAnimationManager animManager;
    private Thread thread;

    public NKAnimationView(Context context) {
        this(context, null, 0);
    }

    public NKAnimationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NKAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        animManager = new NKAnimationManager(this);
        getHolder().addCallback(this);
        this.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public void run() {
        while (thread != null){
            animManager.onUpdate();
            this.draw(getHolder());
        }
    }

    public void setCanvasBackgroundColor(int color) {
        canvasColor = color;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.thread = null;
    }

    private void draw(SurfaceHolder holder){
        Canvas c = holder.lockCanvas();
        if ( c == null){
            return;
        }
        animManager.onDraw(c);
        holder.unlockCanvasAndPost(c);
    }

    // dispatch
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return animManager.onTouchEvent(event);
    }

    final int getCanvasColor() {
        return canvasColor;
    }

    public <T extends NKAnimationBaseController> void addController(T controller) {
        controller.setSurfaceView(this);
        animManager.addController(controller);
    }
}

package info.nukoneko.android.lib.nkanimation;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public final class NKAnimationView extends SurfaceView
        implements SurfaceHolder.Callback, Runnable {
    private String backgroundColor = "#FF000000";
    private final NKAnimationManager animManager;
    private Thread thread;

    public NKAnimationView(Context context) {
        super(context);
        animManager = new NKAnimationManager(this);
        getHolder().addCallback(this);
    }

    /**
     * validation してない
     * @param backgroundColor 背景色 ARGB
     */
    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    @Override
    public void run() {
        while (thread != null){
            animManager.onUpdate();
            this.draw(getHolder());
        }
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

    public <T extends NKAnimationBaseController> void addController(T controller) {
        controller.setSurfaceView(this);
        animManager.addController(controller);
    }

    /**
     * convert ARGB to int array.
     * @return [a, r, g, b]
     */
    public int[] getARGB(){
        int[] ret = new int[4];
        for(int i=0; i<ret.length; i++){
            ret[i] = hexToInt(backgroundColor.charAt(i*2), backgroundColor.charAt(i*2+1));
        }
        return ret;
    }

    private int hexToInt(char a, char b){
        int x = a < 65 ? a-48 : a-55;
        int y = b < 65 ? b-48 : b-55;
        return x*16+y;
    }
}

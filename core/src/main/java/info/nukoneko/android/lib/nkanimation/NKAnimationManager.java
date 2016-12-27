package info.nukoneko.android.lib.nkanimation;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

class NKAnimationManager {
    private final NKAnimationView surfaceView;
    private ArrayList<NKAnimationBaseController> taskList = new ArrayList<>();

    NKAnimationManager(NKAnimationView surfaceView) {
        this.surfaceView = surfaceView;
    }

    boolean onTouchEvent(MotionEvent event) {
        for (NKAnimationBaseController anim : taskList) {
            anim.onTouchEvent(event);
        }
        return true;
    }

    void onDraw(Canvas c) {
        final int[] argb = surfaceView.getARGB();
        c.drawARGB(argb[0], argb[1], argb[2], argb[3]);
        for (NKAnimationBaseController anim : taskList) {
            anim.onDraw(c);
        }
    }

    void onUpdate() {
        for (NKAnimationBaseController anim : taskList) {
            if (anim.isDismiss()) this.taskList.remove(anim);
            else anim.onUpdate();
        }
    }

    <T extends NKAnimationBaseController> void addController(T controller) {
        taskList.add(controller);
    }
}

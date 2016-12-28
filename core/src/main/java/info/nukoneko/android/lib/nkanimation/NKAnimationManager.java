package info.nukoneko.android.lib.nkanimation;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.LinkedList;

class NKAnimationManager {
    private final NKAnimationView surfaceView;
    private LinkedList<NKAnimationBaseController> controllers = new LinkedList<>();

    NKAnimationManager(NKAnimationView surfaceView) {
        this.surfaceView = surfaceView;
    }

    boolean onTouchEvent(MotionEvent event) {
        final LinkedList<NKAnimationBaseController> _controllers = controllers;
        for (NKAnimationBaseController anim : _controllers) {
            anim.onTouchEvent(event);
        }
        return true;
    }

    void onDraw(Canvas c) {
        c.drawColor(surfaceView.getCanvasColor());
        final LinkedList<NKAnimationBaseController> _controllers = controllers;
        for (NKAnimationBaseController anim : _controllers) {
            anim.onDraw(c);
        }
    }

    void onUpdate() {
        final LinkedList<NKAnimationBaseController> _controllers = controllers;
        for (NKAnimationBaseController anim : _controllers) {
            if (anim.isDismiss()) this.controllers.remove(anim);
            else anim.onUpdate();
        }
    }

    <T extends NKAnimationBaseController> void addController(T controller) {
        controllers.add(controller);
    }
}

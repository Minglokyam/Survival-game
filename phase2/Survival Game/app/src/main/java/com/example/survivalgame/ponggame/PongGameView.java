package com.example.survivalgame.ponggame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.survivalgame.User;

public class PongGameView extends SurfaceView implements View {
    private ActivityInterface activityInterface;

    private Canvas canvas;
    /**
     * The Thread of this game
     */
    private PongGameThreadPresenter pongGameThreadPresenter;

    /**
     * The screen width
     */
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /**
     * The screen height
     */
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    private float touchReference;

    public PongGameView(Context context, ActivityInterface activityInterface, User user) {
        super(context);
        this.activityInterface = activityInterface;
        pongGameThreadPresenter = new PongGameThreadPresenter(this, user, screenWidth, screenHeight);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(
                new SurfaceHolder.Callback() {
                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {
                        pongGameThreadPresenter.setRunning(true);
                        pongGameThreadPresenter.start();
                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder, int a, int b, int c) {
                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {
                    }
                });
    }

    /**
     * citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/
     */
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                // point on the right of the peddle
                if (motionEvent.getX() > touchReference) {
                    pongGameThreadPresenter.paddleMoveRight();
                } else { // point on the left of the peddle
                    pongGameThreadPresenter.paddleMoveLeft();
                }
                break;
            case MotionEvent.ACTION_UP:
                pongGameThreadPresenter.paddleStop();
                break;
        }
        return true;
    }

    @Override
    public Canvas lockCanvas() {
        return getHolder().lockCanvas();
    }

    @Override
    public void unlockCanvasAndPost(Canvas canvas) {
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void toMain() {
        activityInterface.toMain();
    }

    @Override
    public void toDodge() {
        activityInterface.toDodge();
    }

    @Override
    public void setTouchReference(float newTouchReference) {
        touchReference = newTouchReference;
    }

//    @Override
//    public SurfaceHolder obtainHolder() {
//        return getHolder();
//    }

    @Override
    public void drawCircle() {
    }

    @Override
    public void drawRect() {

    }
}

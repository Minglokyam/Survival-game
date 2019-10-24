package com.example.survivalgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PongGameView extends SurfaceView implements Runnable {
    /**
     * The screen width
     */
    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /**
     * The screen height
     */
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    Thread thread = null;

    volatile boolean playing;

    boolean pause = true;

    private long fps;

    private SurfaceHolder surfaceHolder;

    private Canvas canvas;

    private PongGameManager pongGameManager;

    Paint paint;

    public PongGameView(Context context) {
        super(context);
        pongGameManager = new PongGameManager(screenWidth, screenHeight);
        surfaceHolder = getHolder();
        setFocusable(true);
        paint = new Paint();
        paint.setTextSize(36);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    public void update(long fps) {
        pongGameManager.update(fps);
        if(User.getLife() == 0){
            pause = true;
        }
    }

    public void draw() {
        canvas = null;
        try {
            synchronized (surfaceHolder) {
                canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.argb(255, 255, 255, 255));
                canvas.drawText("Life: " + User.getLife(), screenWidth / 10, screenHeight / 10, paint);
                pongGameManager.draw(canvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.draw(canvas);
        if (canvas != null) {
            pongGameManager.draw(canvas);
        }
    }

    @Override
    public void run() {
        while (playing) {
            long startTime = System.currentTimeMillis();
            if (!pause) {
                update(fps);
            }
            draw();
            long timeInterval = System.currentTimeMillis() - startTime;
            if (timeInterval > 1) {
                fps = 1000 / timeInterval;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        RectPaddle rectPaddle = pongGameManager.getRectPaddle();
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                pause = false;
                if (motionEvent.getX() > rectPaddle.getXCoordinate() + rectPaddle.getWidth() / 2) {
                    pongGameManager.paddleMoveRight();
                } else {
                    pongGameManager.paddleMoveLeft();
                }
                break;
            case MotionEvent.ACTION_UP:
                pongGameManager.paddleStop();
                break;
        }
        return true;
    }

    public void onPause() {
        playing = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    public void onResume() {
        playing = true;
        thread = new Thread(this);
        thread.start();
    }
}

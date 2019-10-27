package com.example.survivalgame;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.time.Duration;

public class PongGameView extends SurfaceView{
    /**
     * The screen width
     */
    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /**
     * The screen height
     */
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    PongGameThread thread;

    volatile boolean playing;

    private boolean pause = true;

    private long fps;

    private SurfaceHolder surfaceHolder;

    private Canvas canvas;

    private PongGameManager pongGameManager;

    Paint paint;

    Duration pongDuration;

    public PongGameView(Context context) {
        super(context);
        pongGameManager = new PongGameManager(screenWidth, screenHeight);
        surfaceHolder = getHolder();
        setFocusable(true);
        paint = new Paint();
        paint.setTextSize(36);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        pongDuration = Duration.ofSeconds(180);
    }

    public Duration getPongDuration(){
        return pongDuration;
    }

    public void setPongDuration(Duration newPongDuration){
        pongDuration = newPongDuration;
    }

    public void update() {
        pongGameManager.update(fps);
        if(User.getLife() == 0){
            pause = true;
            playing = false;
            thread.setRun(playing);

            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);

        }else if(pongDuration.getSeconds() <= 0){
            pause = true;
            playing = false;
            thread.setRun(playing);
        }
    }

    public void draw() {
        canvas = null;
        try {
            synchronized (surfaceHolder) {
                canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.argb(255, 255, 255, 255));
                canvas.drawText("Life: " + User.getLife(), screenWidth / 10, screenHeight / 20, paint);
                canvas.drawText("Total time: " + User.getTotalDuration().getSeconds(), 3 * screenWidth / 20, screenHeight / 20, paint);
                canvas.drawText("Game time: " + pongDuration.getSeconds(), 15 * screenWidth / 20, screenHeight / 20, paint);
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
            thread.setRun(playing);
            thread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    public void onResume() {
        playing = true;
        thread = new PongGameThread(this);
        thread.setRun(playing);
        thread.start();
    }

    public void setFps(long n) {
        fps = n;
    }

    public boolean getPause(){
        return pause;
    }
}

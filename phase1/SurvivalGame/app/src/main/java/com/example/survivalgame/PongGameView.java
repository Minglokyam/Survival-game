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
    /** The screen width */
    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    /** The screen height */
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    /** A Pong Game Thread */
    PongGameThread thread;

    private boolean stop = true;

    private long FPS;

    private SurfaceHolder surfaceHolder;

    private Canvas canvas;

    private PongGameManager pongGameManager;

    Paint paintText;

    Duration pongDuration;

    public Duration getPongDuration(){
        return pongDuration;
    }

    public void setPongDuration(Duration newPongDuration){
        pongDuration = newPongDuration;
    }

    public PongGameView(Context context) {
        super(context);
        pongGameManager = new PongGameManager(screenWidth, screenHeight);
        surfaceHolder = getHolder();
        setFocusable(true);
        paintText = new Paint();
        paintText.setTextSize(36);
        paintText.setTypeface(Typeface.DEFAULT_BOLD);
        pongDuration = Duration.ofSeconds(15);
    }

    public void update() {
        pongGameManager.update(FPS);
        User.setScore(User.getScore() + 1);
        if(User.getLife() == 0){
            stop = true;
            thread.setPlaying(false);
            thread.interrupt();

            Intent intent = new Intent(getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);

        }else if(pongDuration.getSeconds() <= 0){
            stop = true;
            thread.setPlaying(false);
            thread.interrupt();
            Intent intent = new Intent(getContext(), DodgeGameActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        }
    }

    public void draw() {
        canvas = null;
        try {
            synchronized (surfaceHolder) {
                canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.rgb(255, 255, 255));

                canvas.drawText("Life: " + User.getLife(), 0, 32, paintText);
                canvas.drawText("Total time: " + User.getTotalDuration().getSeconds(), 0, 64, paintText);
                canvas.drawText("Game time: " + pongDuration.getSeconds(), 0, 96, paintText);
                canvas.drawText("Score: " + User.getScore(), 0, 128, paintText);

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
                stop = false;
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

    public void pause() {
        try {
            thread.setPlaying(false);
            thread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    public void resume() {
        thread = new PongGameThread(this);
        thread.start();
    }

    public void setFPS(long newFPS) {
        FPS = newFPS;
    }

    public boolean getStop(){
        return stop;
    }
}

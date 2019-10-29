package com.example.survivalgame;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.content.Context;
import android.view.SurfaceView;
import android.graphics.Bitmap;
import android.view.SurfaceHolder.Callback;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.time.Duration;

class RunningGameView extends SurfaceView {
    RunningGameThread thread;
    private SurfaceHolder holder;

    // the moving speed of the objects in the game.
    public static int movingSpeed = 10;

    // the image holder of the runner.
    Bitmap runnerBmp;

    // the image holder of the coin.
    Bitmap coinBmp;

    // the image holder of the ground.
    Bitmap groundBmp;

    // the image holder of the spike.
    Bitmap spikesBmp;

    // the duration time of the running game.
    private Duration runningDuration;

    // the fps of the running game.
    private long fps;

    private RunningGameActivity runningGameActivity;

    private User user;

    RunningGameManager manager;

    private Paint paintText = new Paint();

    /**
     * set up the GameView.
     */
    public RunningGameView(Context context, User user) {
        super(context);
        this.user = user;
        runningDuration = Duration.ofSeconds(9);
        runningGameActivity = (RunningGameActivity) context;
        thread = new RunningGameThread(this, user);
        holder = getHolder();
        holder.addCallback(
                new Callback() {

                    public void surfaceDestroyed(SurfaceHolder holder0) {
                    }

                    public void surfaceCreated(SurfaceHolder holder0) {
                        thread.setRunning();
                        thread.start();
                    }

                    public void surfaceChanged(SurfaceHolder holder0, int a, int b, int c) {
                    }
                });

        // get the image of the objects.
        runnerBmp = BitmapFactory.decodeResource(getResources(), R.drawable.runner);
        coinBmp = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        groundBmp = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
        spikesBmp = BitmapFactory.decodeResource(getResources(), R.drawable.spikes);

        manager = new RunningGameManager(this);
    }

    /**
     * getter of the runningDuration
     */
    public Duration getRunningDuration() {
        return runningDuration;
    }

    /**
     * setter of the runningDuration.
     */
    public void setRunningDuration(Duration newRunningDuration) {
        runningDuration = newRunningDuration;
    }

    /**
     * setter of the fps in the running game.
     */
    public void setFps(long n) {
        fps = n;
    }

    /** getter of the fps in the running game. */
    public long getFps() {
        return fps;
    }

    /**
     * make the runner jump and restart the game once touching on the screen.
     */
    public boolean onTouchEvent(MotionEvent event) {
        manager.runner.onTouch();
        return false;
    }

    /**
     * update the objects and current game status.
     */
    public void update() {
        user.setScore(user.getScore() + 1);
        manager.update();
        updateCoin();
        updateSpike();

        // when the game time runs out, jump to next game.
        if (runningDuration.getSeconds() <= 0) {
           runningGameActivity.toPong();
        }
    }

    /**
     * update the coin when it moves out of the screen or the runner touches it.
     */
    public void updateCoin() {
        for (int i = 0; i < manager.coin.size(); i++) {
            Rect runner1 = manager.runner.getBounds();
            Rect coin1 = manager.coin.get(i).getBounds();
            if (manager.coin.get(i).CheckCollision(runner1, coin1)) {
                // remove the coin once the runner touch this coin.
                manager.coin.remove(i);
                // add points to the score when the runner touches a coin.
                user.setScore(user.getScore() + 100);
                break;
            }
        }
    }

    /**
     * update the spike when it moves out of the screen or the runner touches it.
     */
    public void updateSpike() {
        for (int i = 0; i < manager.spikes.size(); i++) {
            Rect runner1 = manager.runner.getBounds();
            Rect spike1 = manager.spikes.get(i).GetBounds();
            // end the game once the runner touches the spikes.
            if (manager.spikes.get(i).checkCollision(runner1, spike1)) {
                user.setLife(user.getLife() - 1);
                manager.spikes.remove(i);
                i--;
                if (user.getLife() == 0) {
                   runningGameActivity.toMain();
                    break;
                }
            }
        }
    }

    /**
     * Draw all the objects on the screen.
     */
    public void draw(Canvas canvas) {
        super.draw(canvas);
        update();
        canvas.drawColor(Color.WHITE);
        paintText.setTextSize(40);
        // draw the score and highest score.
        canvas.drawText("Life: " + user.getLife(), 0, 32, paintText);
        canvas.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64, paintText);
        canvas.drawText("Game time: " + runningDuration.getSeconds(), 0, 96, paintText);
        canvas.drawText("Score: " + user.getScore(), 0, 128, paintText);

        // draw the runner.
        manager.runner.onDraw(canvas);

        // draw the coin
        for (int i = 0; i < manager.coin.size(); i++) {
            manager.coin.get(i).onDraw(canvas);
        }

        // draw the spikes
        for (int i = 0; i < manager.spikes.size(); i++) {
            manager.spikes.get(i).onDraw(canvas);
        }

        // draw the ground.
        manager.ground.onDraw(canvas);
    }
}


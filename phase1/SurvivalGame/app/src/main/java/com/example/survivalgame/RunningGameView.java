package com.example.survivalgame;

import android.content.Intent;
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

    // a list of runner.
    private List<Runner> runner = new ArrayList<>();

    // the image holder of the coin.
    Bitmap coinBmp;

    // a list of coin.
    private List<Coin> coin = new ArrayList<>();

    // the image holder of the ground.
    Bitmap groundBmp;

    // ground.
    private Ground ground;

    // the image holder of the spike.
    Bitmap spikesBmp;

    // a list of spikes.
    private List<Spikes> spikes = new ArrayList<>();

    // the timer of the coin.
    private int timerCoins = 0;

    // the timer of the spike.
    private int timerSpike = 0;

    // random timer of the spike.
    private int timerRandomSpikes = 0;

    // the duration time of the running game.
    private Duration runningDuration;

    // the fps of the running game.
    private long fps;

    private RunningGameActivity runningGameActivity;

    /**
     * set up the GameView.
     */
    public RunningGameView(Context context) {
        super(context);
        runningDuration = Duration.ofSeconds(30);
        runningGameActivity = (RunningGameActivity) context;
        thread = new RunningGameThread(this);
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

        // add runner and ground to the game.
        runner.add(new Runner(this, runnerBmp, 50, 50));
        ground = new Ground(this, groundBmp, 0, 0);
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
        for (Runner runners : runner) {
            runners.onTouch();
        }
        return false;
    }

    /**
     * update the objects and current game status.
     */
    public void update() {
        User.setScore(User.getScore() + 1);
        updateTimers();
        updateCoin();
        updateSpike();

        // when the game time runs out, jump to next game.
        if (runningDuration.getSeconds() <= 0) {
           runningGameActivity.toPong();
        }
    }

    /**
     * update the timers to randomly generate the coins and spikes.
     */
    private void updateTimers() {

        timerCoins++;

        timerSpike++;

        // randomly generate spikes.
        switch (timerRandomSpikes) {
            case 0:
                if (timerSpike >= 125) {
                    spikes.add(new Spikes(this, spikesBmp, this.getWidth() + 24, 0));
                    Random randomSpikes = new Random();
                    timerRandomSpikes = randomSpikes.nextInt(3);
                    timerSpike = 0;
                }
                break;

            case 1:
                if (timerSpike >= 175) {
                    spikes.add(new Spikes(this, spikesBmp, this.getWidth() + 24, 0));
                    Random randomSpikes = new Random();
                    timerRandomSpikes = randomSpikes.nextInt(3);
                    timerSpike = 0;
                }
                break;
            case 2:
                if (timerSpike >= 100) {
                    spikes.add(new Spikes(this, spikesBmp, this.getWidth() + 24, 0));
                    Random randomSpikes = new Random();
                    timerRandomSpikes = randomSpikes.nextInt(3);
                    timerSpike = 0;
                }
                break;
        }

        // randomly generate coins.
        if (timerCoins >= 100) {
            Random randomCoin = new Random();
            int random;
            random = randomCoin.nextInt(5);

            switch (random) {
                case 1:
                    int currentCoin = 1;
                    int xx = 1;
                    while (currentCoin <= 5) {
                        coin.add(new Coin(this, coinBmp, this.getWidth() + (64 * xx), 130));
                        currentCoin++;
                        xx++;
                    }
                    break;

                case 2:
                    coin.add(new Coin(this, coinBmp, this.getWidth() + 32, 150));
                    coin.add(new Coin(this, coinBmp, this.getWidth() + 96, 130));
                    coin.add(new Coin(this, coinBmp, this.getWidth() + 160, 150));
                    coin.add(new Coin(this, coinBmp, this.getWidth() + 224, 130));
                    coin.add(new Coin(this, coinBmp, this.getWidth() + 288, 150));
            }
            timerCoins = 0;
        }
    }

    /**
     * update the coin when it moves out of the screen or the runner touches it.
     */
    public void updateCoin() {
        for (int i = 0; i < coin.size(); i++) {
            Rect runner1 = runner.get(0).getBounds();
            Rect coin1 = coin.get(i).getBounds();

            if (coin.get(i).getX() < -80) {
                coin.remove(i);
                i--;
            }

            if (coin.get(i).CheckCollision(runner1, coin1)) {
                // remove the coin once the runner touch this coin.
                coin.remove(i);

                // add points to the score when the runner touches a coin.
                User.setScore(User.getScore() + 100);
            }
        }
    }

    /**
     * update the spike when it moves out of the screen or the runner touches it.
     */
    public void updateSpike() {
        for (int i = 0; i < spikes.size(); i++) {

            Rect runner1 = runner.get(0).getBounds();
            Rect spike1 = spikes.get(i).GetBounds();

            if (spikes.get(i).getX() < -80) {
                spikes.remove(i);
                break;
            }

            // end the game once the runner touches the spikes.
            if (spikes.get(i).checkCollision(runner1, spike1)) {
                User.setLife(User.getLife() - 1);
                spikes.remove(i);
                i--;
                if (User.getLife() == 0) {
                   runningGameActivity.toMain();
                    break;
                }
            }
        }
    }

    /**
     * Draw all the objects on the screen.
     */
    Paint paintText = new Paint();
    public void draw(Canvas canvas) {
        super.draw(canvas);
        update();
        canvas.drawColor(Color.WHITE);
        paintText.setTextSize(40);
        // draw the score and highest score.
        canvas.drawText("Life: " + User.getLife(), 0, 32, paintText);
        canvas.drawText("Total time: " + User.getTotalDuration().getSeconds(), 0, 64, paintText);
        canvas.drawText("Game time: " + runningDuration.getSeconds(), 0, 96, paintText);
        canvas.drawText("Score: " + User.getScore(), 0, 128, paintText);

        // draw the runner.
        for (Runner runners : runner) {
            runners.onDraw(canvas);
        }

        // draw the coin
        for (int i = 0; i < coin.size(); i++) {
            coin.get(i).onDraw(canvas);
        }

        // draw the spikes
        for (int i = 0; i < spikes.size(); i++) {
            spikes.get(i).onDraw(canvas);
        }

        // draw the ground.
        ground.onDraw(canvas);
    }
}


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

class RunningGameView extends SurfaceView {
    RunningGameThread thread;
    private SurfaceHolder holder;

    // the moving speed of the objects in the game.
    public static int globalxSpeed = 10;

    // the image holder of the runner.
    Bitmap runnerBmp;

    // a list of runner.
    private List<runner> runner = new ArrayList<>();

    // the image holder of the coin.
    Bitmap coinBmp;

    // a list of coin.
    private List<coin> coin = new ArrayList<>();

    // the image holder of the ground.
    Bitmap groundBmp;

    // ground.
    private ground ground;

    // the image holder of the spike.
    Bitmap spikesBmp;

    // a list of spikes.
    private List<Spikes> spikes = new ArrayList<>();

    // current score in the game.
    public static int score = 0;

    // the highest score.
    public static int highScore;

    // the timer of the coin.
    private int timerCoins = 0;

    // the timer of the spike.
    private int timerSpike = 0;

    // random timer of the spike.
    private int timerRandomSpikes = 0;

    // the current status of the game: "Running" or "MainMenu"
    private String Menu = "Running";

    /**
     * set up the GameView.
     */
    public RunningGameView(Context context) {
        super(context);

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
        runner.add(new runner(this, runnerBmp, 50, 50));
        ground = new ground(this, groundBmp, 0, 0);
    }

    /**
     * make the runner jump and restart the game once touching on the screen.
     */
    public boolean onTouchEvent(MotionEvent event) {
        for (runner runners : runner) {
            runners.onTouch();
        }

        if (Menu == "MainMenu") {
            Menu = "Running";
            startGame();
        }
        return false;
    }

    /**
     * update the score, highest score and those timers.
     */
    public void update() {
        score += 1;

        updateTimers();

        if (score > highScore) {
            highScore = score;
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
                        coin.add(new coin(this, coinBmp, this.getWidth() + (64 * xx), 130));
                        currentCoin++;
                        xx++;
                    }
                    break;

                case 2:
                    coin.add(new coin(this, coinBmp, this.getWidth() + 32, 150));
                    coin.add(new coin(this, coinBmp, this.getWidth() + 96, 130));
                    coin.add(new coin(this, coinBmp, this.getWidth() + 160, 150));
                    coin.add(new coin(this, coinBmp, this.getWidth() + 224, 130));
                    coin.add(new coin(this, coinBmp, this.getWidth() + 288, 150));
            }
            timerCoins = 0;
        }
    }

    /**
     * make the game start.
     */
    public void startGame() {
        runner.add(new runner(this, runnerBmp, 50, 50));
    }

    /**
     * remove all objects in the screen once the runner is dead.
     */
    public void endGame() {
        Menu = "MainMenu";

        timerCoins = 0;
        timerSpike = 0;

        for (int i = 0; i < coin.size(); i++) {
            coin.remove(i);
        }
        for (int i = 0; i < spikes.size(); i++) {
            spikes.remove(i);
        }

        runner.remove(0);
    }

    Paint paintText = new Paint();

    /**
     * Draw all the objects on the screen.
     */
    public void draw(Canvas canvas) {
        super.draw(canvas);
        update();
        canvas.drawColor(Color.WHITE);

        if (Menu.equals("Running")) {

            paintText.setTextSize(40);

            // draw the score and highest score.
            canvas.drawText("Score: " + score, 0, 32, paintText);
            canvas.drawText("Highest Score: " + highScore, 0, 64, paintText);
            // 怎么计时？
            //        canvas.drawText("Running time: " + System.currentTimeMillis(), 0, 96, paintText);

            // draw the runner.
            for (runner runners : runner) {
                runners.onDraw(canvas);
            }

            // draw the coin
            for (int i = 0; i < coin.size(); i++) {
                coin.get(i).onDraw(canvas);
                Rect runner1 = runner.get(0).getBounds();
                Rect coin1 = coin.get(i).getBounds();

                if (coin.get(i).getX() < 0 - 32) {
                    coin.remove(i);
                }

                if (coin.get(i).CheckCollision(runner1, coin1)) {
                    // remove the coin once the runner touch this coin.
                    coin.remove(i);

                    // add points to the score when the runner touches a coin.
                    score += 100;
                }
            }

            // draw the spikes
            for (int i = 0; i < spikes.size(); i++) {
                spikes.get(i).onDraw(canvas);
                Rect runner1 = runner.get(0).getBounds();
                Rect spike1 = spikes.get(i).GetBounds();

                if (spikes.get(i).getX() < 0 - 32) {
                    spikes.remove(i);
                    break;
                }

                // end the game once the runner touches the spikes.
                if (spikes.get(i).checkCollision(runner1, spike1)) {
                    score = 0;
                    endGame();
                    break;
                }
            }

            // draw the ground.
            ground.onDraw(canvas);
        }
    }
}


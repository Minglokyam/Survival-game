package com.example.survivalgame;

import android.graphics.Canvas;

class RunningGameThread extends Thread {
    private RunningGameView view;

    // the fps of the game
    private static final long FPS = 30;

    // check whether the runner is running
    boolean running;

    public RunningGameThread(RunningGameView view) {
        this.view = view;
    }

    public void setRunning() {
        this.running = true;
    }

    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime = 0;
        long sleepTime;
        while (running) {
            Canvas canvas = null;
            try {
                canvas = view.getHolder().lockCanvas();
                synchronized (view.getHolder()) {
                    view.draw(canvas);
                }
            } finally {
                if (canvas != null) {
                    view.getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0) {
                    sleep(sleepTime);
                } else {
                    sleep(10);
                }
            } catch (Exception e) {
            }
        }
    }
}
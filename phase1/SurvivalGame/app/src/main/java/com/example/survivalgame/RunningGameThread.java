package com.example.survivalgame;

import android.graphics.Canvas;

class RunningGameThread extends Thread {
    private RunningGameView view;

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
        long sleepTime;
        while (running) {
            long startTime = System.currentTimeMillis();
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
            sleepTime = view.getFps() - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0) {
                    sleep(sleepTime);
                } else {
                    sleep(10);
                }
            } catch (Exception e) {
            }
            long timeInterval = System.currentTimeMillis() - startTime;
            User.setTotalDuration(User.getTotalDuration().plusMillis(timeInterval));
            view.setRunningDuration(view.getRunningDuration().minusMillis(timeInterval));
            if (timeInterval > 1) {
                view.setFps(1000 / timeInterval);
            }
        }
    }
}
package com.example.survivalgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Runner {
    // the first and second coordinate of the runner.
    private static int x;
    private static int y;

    // the gravity.
    private static int gravity = 1;

    // the running speed.
    private static int vSpeed = 1;

    // the runner's height.
    private static int runnerHeight;

    // the height this runner will jump.
    private static int jumppower = -20;

    private Bitmap bmp;
    private RunningGameView view;

    // the width and height of the screen.
    private int width;
    private int height;

    /**
     * Build a runner.
     */
    Runner(RunningGameView view, Bitmap bmp, int x, int y) {
        Runner.x = x;
        Runner.y = y;
        this.view = view;
        this.bmp = bmp;

        this.width = bmp.getWidth();
        this.height = bmp.getHeight();

        runnerHeight = bmp.getHeight();
    }

    /**
     * draw the runner.
     */
    void onDraw(Canvas canvas) {
        update();
        canvas.drawBitmap(bmp, x, y, null);
    }

    private void update() {
        checkGround();
    }

    /**
     * change the speed when hitting the ground and when jumping at highest point.
     */
    private void checkGround() {
        if (y < view.getHeight() - Ground.height - runnerHeight) {
            vSpeed += gravity;
            if (y > view.getHeight() - Ground.height - runnerHeight - vSpeed) {
                vSpeed = view.getHeight() - Ground.height - runnerHeight;
            }
        } else if (vSpeed > 0) {
            vSpeed = 0;
            y = view.getHeight() - Ground.height - runnerHeight;
        }
        y += vSpeed;
    }

    /**
     * make the runner jump when touching the screen.
     */
    void onTouch() {
        if (y >= view.getHeight() - Ground.height - runnerHeight) {
            vSpeed = jumppower;
        }
    }

    /**
     * get the rectangle of the runner.
     */
    Rect getBounds() {
        return new Rect(Runner.x, Runner.y, Runner.x + width, Runner.y + height);
    }
}


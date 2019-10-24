package com.example.survivalgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class runner {
    // the first and second coordinate of the runner.
    private static int x;
    private static int y;

    // the gravity.
    private static int gravity = 1;

    // the running speed.
    private static int vSpeed = 1;

    // the runner's height and width.
    private static int runnerHeight;
    private static int runnerWidth;

    // the height this runner will jump.
    private static int jumppower = -20;

    private Bitmap bmp;
    private RunningGameView view;

    // the width and height of the screen.
    private int width;
    private int height;

    private int columnHeight = 1;
    private int columnWidth = 1;

    /**
     * Build a runner.
     */
    runner(RunningGameView view, Bitmap bmp, int x, int y) {
        runner.x = x;
        runner.y = y;
        this.view = view;
        this.bmp = bmp;

        this.width = bmp.getWidth() / columnWidth;
        this.height = bmp.getHeight() / columnHeight;

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
        if (y < view.getHeight() - ground.height - runnerHeight) {
            vSpeed += gravity;
            if (y > view.getHeight() - ground.height - runnerHeight - vSpeed) {
                vSpeed = view.getHeight() - ground.height - runnerHeight;
            }
        } else if (vSpeed > 0) {
            vSpeed = 0;
            y = view.getHeight() - ground.height - runnerHeight;
        }
        y += vSpeed;
    }

    /**
     * make the runner jump when touching the screen.
     */
    void onTouch() {
        if (y >= view.getHeight() - ground.height - runnerHeight) {
            vSpeed = jumppower;
        }
    }

    /**
     * get the rectangle of the runner.
     */
    Rect getBounds() {
        return new Rect(runner.x, runner.y, runner.x + width, runner.y + height);
    }
}


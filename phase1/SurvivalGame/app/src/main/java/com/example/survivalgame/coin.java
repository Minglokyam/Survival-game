package com.example.survivalgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class coin {

    // the first and second coordinate of the coin.
    private int x;
    private int y;

    private int y2;

    private Bitmap bmp;
    private RunningGameView view;

    private int columnWidth = 4;
    private int columnHeight = 1;

    // the speed of the coin moves in x-coordinate.
    private int xspeed = -RunningGameView.globalxSpeed;

    private int currentFrame = 0;

    // the width and height of the screen.
    private int width;
    private int height;

    private Rect runner;
    private Rect coin;

    /**
     * Build a coin.
     */
    coin(RunningGameView view, Bitmap bmp, int x, int y) {
        this.x = x;
        this.y2 = y;

        this.view = view;
        this.bmp = bmp;
        this.width = bmp.getWidth() / columnWidth;
        this.height = bmp.getHeight() / columnHeight;
    }

    /**
     * update the coin's speed, and make the coin move
     */
    void update() {
        x += xspeed;
        y = view.getHeight() - y2 - ground.height - bmp.getHeight();

        if (currentFrame >= columnWidth - 1) {
            currentFrame = 0;
        } else {
            currentFrame += 1;
        }

        if (x < 0) {
            x = view.getWidth() + width;
        }
    }

    /**
     * get the rectangle of the coin.
     */
    Rect getBounds() {
        return new Rect(this.x, this.y, this.x + width, this.y + height);
    }

    /**
     * draw the coin.
     */
    void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        Rect src = new Rect(currentFrame * width, 0, srcX + width, 42);
        Rect dst = new Rect(x, y, x + width, y + 42);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    /**
     * check whether the runner touched the coin
     */
    boolean CheckCollision(Rect runner, Rect coin) {
        this.runner = runner;
        this.coin = coin;
        return Rect.intersects(runner, coin);
    }

    int getX() {
        return x;
    }
}

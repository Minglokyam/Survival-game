package com.example.survivalgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

class Coin {

    // the first and second coordinate of the coin.
    private int x;
    private int y;

    private int y2;

    private Bitmap bmp;
    private RunningGameView view;

    // the speed of the coin moves in x-coordinate.
    private int xspeed = -RunningGameView.movingSpeed;

    private int currentFrame = 0;

    // the width and height of the screen.
    private int width;
    private int height;

    /**
     * Build a coin.
     */
    Coin(RunningGameView view, Bitmap bmp, int x, int y) {
        this.x = x;
        this.y2 = y;

        this.view = view;
        this.bmp = bmp;
        this.width = bmp.getWidth() / 4;
        this.height = bmp.getHeight();
    }

    /**
     * update the coin's speed, and make the coin move
     */
    void update() {
        x += xspeed;
        y = view.getHeight() - y2 - Ground.height - bmp.getHeight();

        if (currentFrame >= 3) {
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
        return Rect.intersects(runner, coin);
    }

    int getX() {
        return x;
    }
}

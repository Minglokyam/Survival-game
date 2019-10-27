package com.example.survivalgame;

import android.graphics.Canvas;

public class PongGameManager {
    /**
     * The screen width
     */
    private int screenWidth;

    /**
     * The screen height
     */
    private int screenHeight;

    private Ball ball;

    private RectPaddle rectPaddle;

    private User user;

    public PongGameManager(int screenWidth, int screenHeight, User user) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        ball = new Ball(this, 10, screenWidth / 2, screenHeight / 2, screenWidth / 3, -screenHeight / 3, user);
        rectPaddle = new RectPaddle(this, screenWidth / 5, screenWidth / 4, screenHeight / 25, screenWidth / 6, screenHeight * 7 / 8);
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public int getScreenHeight() {
        return this.screenHeight;
    }

    public RectPaddle getRectPaddle() {
        return this.rectPaddle;
    }

    public void update(long fps) {
        ball.move(fps);
        rectPaddle.move(fps);
    }

    public void draw(Canvas canvas) {
        ball.draw(canvas);
        rectPaddle.draw(canvas);
    }

    public void paddleMoveLeft() {
        rectPaddle.moveLeft();
    }

    public void paddleMoveRight() {
        rectPaddle.moveRight();
    }

    public void paddleStop() {
        rectPaddle.stop();
    }
}

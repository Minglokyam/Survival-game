package com.example.survivalgame.ponggame;

import android.graphics.Canvas;

import com.example.survivalgame.User;

class PongGameManager {
  BallFactory ballFactory = new BallFactory();
  RectPaddleFactory rectPaddleFactory = new RectPaddleFactory();
  /** The screen width */
  private int screenWidth;

  /** The screen height */
  private int screenHeight;

  private Ball ball;

  private RectPaddle rectPaddle;

  public PongGameManager(int screenWidth, int screenHeight, User user) {
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    ball =
        ballFactory.createBall(
            this,
                10,
                screenWidth / 2,
                screenHeight / 2,
                screenWidth / 3,
                -screenHeight / 3, user);
    rectPaddle =
        rectPaddleFactory.createRectPaddle(
            this,
            screenWidth * 2 / 9,
            screenWidth / 4,
            screenHeight / 25,
            screenWidth / 6,
            screenHeight * 7 / 8);
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

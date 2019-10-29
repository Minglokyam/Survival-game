package com.example.survivalgame;

import android.graphics.Color;

public class Ball extends PongGameItemCircle {
  /** The horizontal velocity of this ball */
  private float xVelocity;

  /** The vertical velocity of this ball */
  private float yVelocity;

  /** The initial horizontal velocity of this ball */
  private float initialXVelocity;

  /** The initial vertical velocity of this ball */
  private float initialYVelocity;

  /** The initial x-coordinate of this ball */
  private float initialXCoordinate;

  /** The initial y-coordinate of this ball */
  private float initialYCoordinate;

  User user;

  /** A getter of xVelocity */
  public float getXVelocity() {
    return xVelocity;
  }

  /** A setter of xVelocity */
  public void setXVelocity(float newXVelocity) {
    this.xVelocity = newXVelocity;
  }

  /** A getter of yVelocity */
  public float getYVelocity() {
    return yVelocity;
  }

  /** A setter of yVelocity */
  public void setYVelocity(float newYVelocity) {
    this.yVelocity = newYVelocity;
  }

  /** build a ball. */
  Ball(
      PongGameManager pongGameManager,
      float radius,
      float xCoordinate,
      float yCoordinate,
      float xVelocity,
      float yVelocity,
      User user) {
    super(pongGameManager, radius, xCoordinate, yCoordinate);
    this.user = user;
    initialXVelocity = xVelocity;
    initialYVelocity = yVelocity;
    initialXCoordinate = xCoordinate;
    initialYCoordinate = yCoordinate;
    this.xVelocity = xVelocity;
    this.yVelocity = yVelocity;
    getPaint().setColor(Color.MAGENTA);
  }

  /** move the ball when it hit top, left, right, paddle or bottom. */
  void move(long fps) {
    hitTop();
    hitLeft();
    hitRight();
    hitPaddle();
    hitBottom();
    xMove(fps);
    yMove(fps);
  }

  /** move the ball in x direction. */
  private void xMove(long fps) {
    float newXCoordinate = getXCoordinate() + (xVelocity / fps);
    setXCoordinate(newXCoordinate);
  }

  /** move the ball in y direction. */
  private void yMove(long fps) {
    float newYCoordinate = getYCoordinate() + (yVelocity / fps);
    setYCoordinate(newYCoordinate);
  }

  /** move the ball up. */
  private void moveUp() {
    yVelocity = -Math.abs(yVelocity);
  }

  /** move the ball down. */
  private void moveDown() {
    yVelocity = Math.abs(yVelocity);
  }

  /** move the ball left. */
  private void moveLeft() {
    xVelocity = -Math.abs(xVelocity);
  }

  /** move the ball right. */
  private void moveRight() {
    xVelocity = Math.abs(xVelocity);
  }

  /** move down when the ball hits the top. */
  private void hitTop() {
    if (getYCoordinate() - getRadius() <= 0) {
      moveDown();
    }
  }

  /** move right when the ball hits the left. */
  private void hitLeft() {
    if (getXCoordinate() - getRadius() <= 0) {
      moveRight();
    }
  }

  /** move left when the ball hits the right. */
  private void hitRight() {
    if (this.getXCoordinate() + getRadius() >= getPongGameManager().getScreenWidth()) {
      moveLeft();
    }
  }

  /** move up when the ball hits the paddle. */
  private void hitPaddle() {
    if (checkHitPaddle()) {
      moveUp();
    }
  }

  /** reset the ball when it hits the bottom instead of hits the paddle. */
  private void hitBottom() {
    if (this.getYCoordinate() + getRadius() >= getPongGameManager().getScreenHeight()) {
      user.setLife(user.getLife() - 1);
      resetSpeed();
      resetCoordinate();
    }
  }

  /** check whether the ball hits the paddle. */
  private boolean checkHitPaddle() {
    RectPaddle rectPaddle = getPongGameManager().getRectPaddle();
    return getXCoordinate() >= rectPaddle.getXCoordinate()
        && getXCoordinate() <= rectPaddle.getXCoordinate() + rectPaddle.getWidth()
        && getYCoordinate() + getRadius() >= rectPaddle.getYCoordinate();
  }

  /** reset the speed of the ball. */
  private void resetSpeed() {
    xVelocity = initialXVelocity;
    yVelocity = initialYVelocity;
  }

  /** reset the coordinate of the ball. */
  private void resetCoordinate() {
    setXCoordinate(initialXCoordinate);
    setYCoordinate(initialYCoordinate);
  }
}

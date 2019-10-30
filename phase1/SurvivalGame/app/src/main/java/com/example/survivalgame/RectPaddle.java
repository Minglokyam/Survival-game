package com.example.survivalgame;

import android.graphics.Color;

/**
 * citation: http://gamecodeschool.com/android/programming-a-pong-game-for-android/
 */
class RectPaddle extends PongGameItemRect {
  private final int STOP = 0;
  private final int LEFT = 1;
  private final int RIGHT = 2;
  private int movingStatus = STOP;
  private float xSpeed;

  /** build the paddle. */
  RectPaddle(
      PongGameManager pongGameManager,
      float xSpeed,
      float width,
      float height,
      float xCoordinate,
      float yCoordinate) {
    super(pongGameManager, width, height, xCoordinate, yCoordinate);
    getPaint().setColor(Color.MAGENTA);
    this.xSpeed = xSpeed;
  }

  /** move the paddle considering the current moving direction . */
  void move(long fps) {
    if (!checkHitLeft() && movingStatus == LEFT) {
      xMoveLeft(fps);
    } else if (!checkHitRight() && movingStatus == RIGHT) {
      xMoveRight(fps);
    }
  }

  /** move the paddle to left. */
  private void xMoveLeft(long fps) {
    float newXCoordinate = getXCoordinate() - (xSpeed / fps);
    setXCoordinate(newXCoordinate);
  }

  /** move the paddle to the right. */
  private void xMoveRight(long fps) {
    float newXCoordinate = getXCoordinate() + (xSpeed / fps);
    setXCoordinate(newXCoordinate);
  }

  /** check whether the paddle hits the very left. */
  private boolean checkHitLeft() {
    return getXCoordinate() <= 0;
  }

  /** check whether the paddle hits the very right. */
  private boolean checkHitRight() {
    return getXCoordinate() + getWidth() >= getPongGameManager().getScreenWidth();
  }

  /** change the moving status to left. */
  void moveLeft() {
    movingStatus = LEFT;
  }

  /** change the moving status to right. */
  void moveRight() {
    movingStatus = RIGHT;
  }

  /** change the moving status to stop. */
  void stop() {
    movingStatus = STOP;
  }
}

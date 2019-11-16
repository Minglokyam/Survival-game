package com.example.survivalgame.dodgegame.model;

import android.graphics.RectF;

// The class for the plane that controls by the player.
public class Plane extends DodgeGameItem {
  public static int STOP = 0;
  public static int MOVE = 1;
  private int movingStatus;
  // the speed of xCoordinate
  private float xSpeed;
  // the speed of yCoordinate
  private float ySpeed;
  private float previousXCoordinate;
  private float previousYCoordinate;

  /**
   * constructor
   *
   * @param dodgeGameManager This is a dodge game manager
   * @param hp represent life
   * @param xCoordinate of the plane
   * @param yCoordinate of the plane
   */
  Plane(DodgeGameManager dodgeGameManager, HP hp, float xCoordinate, float yCoordinate) {
    super(dodgeGameManager, xCoordinate, yCoordinate);
    movingStatus = STOP;
    previousXCoordinate = getXCoordinate();
    previousYCoordinate = getYCoordinate();
  }

  public void setXSpeed(float xSpeed) {
    this.xSpeed = xSpeed;
  }

  public void setYSpeed(float ySpeed) {
    this.ySpeed = ySpeed;
  }

  /**
   * @return a rectangle the position of rectangle is based on the position of the plane. the method
   *     would be called, when we check if the plane is hit by shells or not.
   */
  RectF getRectF() {
    return new RectF(
        getXCoordinate() - 60, getYCoordinate(), getXCoordinate() + 60, getYCoordinate() + 100);
  }

  /** update the position of the plane. */
  void update() {
    if (inScreen() && movingStatus == MOVE) {
      previousXCoordinate = getXCoordinate();
      previousYCoordinate = getYCoordinate();
      setXCoordinate(getXCoordinate() + xSpeed);
      setYCoordinate(getYCoordinate() + ySpeed);

    } else {
      setXCoordinate(previousXCoordinate);
      setYCoordinate(previousYCoordinate);
    }
  }

  /** check whether the plane is still in the screen. */
  private boolean inScreen() {
    return getXCoordinate() >= 0
        && getXCoordinate() <= getDodgeGameManager().getScreenWidth()
        && getYCoordinate() >= 0
        && getYCoordinate() <= getDodgeGameManager().getScreenHeight();
  }

  public void move() {
    movingStatus = MOVE;
  }

  public void stop() {
    xSpeed = 0;
    ySpeed = 0;
    movingStatus = STOP;
  }
}

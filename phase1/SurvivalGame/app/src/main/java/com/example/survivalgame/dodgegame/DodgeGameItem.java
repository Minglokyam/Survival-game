package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;
import android.graphics.Paint;

abstract class DodgeGameItem {
  /** The x-coordinate of this PongGameItem */
  private float xCoordinate;
  /** The y-coordinate of this PongGameItem */
  private float yCoordinate;
  /** An instance of DodgeGameManager */
  private DodgeGameManager dodgeGameManager;

  private Paint paint;

  /** Create a PongGameItem. */
  DodgeGameItem(DodgeGameManager dodgeGameManager) {
    paint = new Paint();
    this.dodgeGameManager = dodgeGameManager;
  }

  /** A getter of dodgeGameManager */
  DodgeGameManager getDodgeGameManager() {
    return dodgeGameManager;
  }

  /** A getter of xCoordinate */
  public float getXCoordinate() {
    return xCoordinate;
  }

  /** A setter of xCoordinate */
  void setXCoordinate(float newXCoordinate) {
    this.xCoordinate = newXCoordinate;
  }

  /** A getter of yCoordinate */
  float getYCoordinate() {
    return yCoordinate;
  }

  /** A setter of yCoordinate */
  void setYCoordinate(float newYCoordinate) {
    this.yCoordinate = newYCoordinate;
  }

  Paint getPaint() {
    return paint;
  }

  public abstract void draw(Canvas canvas);
}

package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class DodgeGameItem {
  /** The x-coordinate of this PongGameItem */
  private float xCoordinate;
  /** The y-coordinate of this PongGameItem */
  private float yCoordinate;
  /** An instance of DodgeGameManager */
  private DodgeGameManager dodgeGameManager;

  private Paint paint;

  /** Create a PongGameItem. */
  public DodgeGameItem(DodgeGameManager dodgeGameManager) {
    paint = new Paint();
    this.dodgeGameManager = dodgeGameManager;
  }

  /** A getter of dodgeGameManager */
  public DodgeGameManager getDodgeGameManager() {
    return dodgeGameManager;
  }

  /** A getter of xCoordinate */
  public float getXCoordinate() {
    return xCoordinate;
  }

  /** A setter of xCoordinate */
  public void setXCoordinate(float newXCoordinate) {
    this.xCoordinate = newXCoordinate;
  }

  /** A getter of yCoordinate */
  public float getYCoordinate() {
    return yCoordinate;
  }

  /** A setter of yCoordinate */
  public void setYCoordinate(float newYCoordinate) {
    this.yCoordinate = newYCoordinate;
  }

  public Paint getPaint() {
    return paint;
  }

  public abstract void draw(Canvas canvas);
}

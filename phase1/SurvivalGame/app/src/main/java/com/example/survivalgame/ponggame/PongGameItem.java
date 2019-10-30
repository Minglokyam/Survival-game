package com.example.survivalgame.ponggame;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.survivalgame.ponggame.PongGameManager;

public abstract class PongGameItem {
  /** The x-coordinate of this PongGameItem */
  private float xCoordinate;
  /** The y-coordinate of this PongGameItem */
  private float yCoordinate;
  /** An instance of PongGameManager */
  private PongGameManager pongGameManager;

  private Paint paint;

  /**
   * Create a PongGameItem.
   *
   * @param xCoordinate the x-coordinate of this PongGameItem
   * @param yCoordinate the y-coordinate of this PongGameItem
   */
  public PongGameItem(PongGameManager pongGameManager, float xCoordinate, float yCoordinate) {
    paint = new Paint();
    this.pongGameManager = pongGameManager;
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;
  }

  /** A getter of pongGameManager */
  public PongGameManager getPongGameManager() {
    return pongGameManager;
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

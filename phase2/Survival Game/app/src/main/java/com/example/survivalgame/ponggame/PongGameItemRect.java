package com.example.survivalgame.ponggame;

import android.graphics.Canvas;

public abstract class PongGameItemRect extends PongGameItem {
  /** The width of this rectangle */
  private float width;
  /** The height of this rectangle */
  private float height;

  PongGameItemRect(
      PongGameManager pongGameManager,
      float width,
      float height,
      float xCoordinate,
      float yCoordinate) {
    super(pongGameManager, xCoordinate, yCoordinate);
    this.width = width;
    this.height = height;
  }

  /** A getter of width */
  public float getWidth() {
    return width;
  }

  /** A getter of height */
  private float getHeight() {
    return height;
  }

  @Override
  public void draw(Canvas canvas) {
    canvas.drawRect(
        getXCoordinate(),
        getYCoordinate(),
        getXCoordinate() + getWidth(),
        getYCoordinate() + getHeight(),
        getPaint());
  }
}

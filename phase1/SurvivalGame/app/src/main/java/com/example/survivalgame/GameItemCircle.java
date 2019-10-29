package com.example.survivalgame;

import android.graphics.Canvas;

public abstract class GameItemCircle extends GameItem {
  /** The radius of this rectangle */
  private float radius;

  GameItemCircle(
      PongGameManager pongGameManager, float radius, float xCoordinate, float yCoordinate) {
    super(pongGameManager, xCoordinate, yCoordinate);
    this.radius = radius;
  }

  /** A getter of radius */
  float getRadius() {
    return radius;
  }

  @Override
  public void draw(Canvas canvas) {
    canvas.drawCircle(getXCoordinate(), getYCoordinate(), getRadius(), getPaint());
  }
}

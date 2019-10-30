package com.example.survivalgame.ponggame;

import android.graphics.Canvas;

import com.example.survivalgame.ponggame.PongGameItem;
import com.example.survivalgame.ponggame.PongGameManager;

public abstract class PongGameItemCircle extends PongGameItem {
  /** The radius of this rectangle */
  private float radius;

  public PongGameItemCircle(
      PongGameManager pongGameManager, float radius, float xCoordinate, float yCoordinate) {
    super(pongGameManager, xCoordinate, yCoordinate);
    this.radius = radius;
  }

  /** A getter of radius */
  public float getRadius() {
    return radius;
  }

  @Override
  public void draw(Canvas canvas) {
    canvas.drawCircle(getXCoordinate(), getYCoordinate(), getRadius(), getPaint());
  }
}

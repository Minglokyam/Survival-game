package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;

public class Plane extends DodgeGameItem {
  private HP hp;
  private int xSpeed;
  private int ySpeed;
  private float previousXCoordinate;
  private float previousYCoordinate;

  Plane(DodgeGameManager dodgeGameManager, HP hp, float xCoordinate, float yCoordinate) {
    super(dodgeGameManager, xCoordinate, yCoordinate);
    getPaint().setColor(Color.BLACK);
    this.hp = hp;
  }

  void setXSpeed(int xSpeed) {
    this.xSpeed = xSpeed;
  }

  void setYSpeed(int ySpeed) {
    this.ySpeed = ySpeed;
  }

  RectF getRectF() {
    return new RectF(
        getXCoordinate() - 60, getYCoordinate(), getXCoordinate() + 60, getYCoordinate() + 200);
  }

  void draw(Canvas canvas) {
    if (hp.getHP() != 0) {
      drawPath(canvas);
    }
  }

  private void drawPath(Canvas canvas) {
    Path path = new Path();
    path.moveTo(getXCoordinate(), getYCoordinate());
    path.lineTo(getXCoordinate() - 40, getYCoordinate() + 100);
    path.lineTo(getXCoordinate(), getYCoordinate() + 60);
    path.lineTo(getXCoordinate() + 40, getYCoordinate() + 100);
    path.lineTo(getXCoordinate(), getYCoordinate());
    canvas.drawPath(path, getPaint());
  }

  void update() {
    if (inScreen()) {
      previousXCoordinate = getXCoordinate();
      previousYCoordinate = getYCoordinate();
      setXCoordinate(getXCoordinate() + xSpeed);
      setYCoordinate(getYCoordinate() + ySpeed);

    } else {
      setXCoordinate(previousXCoordinate);
      setYCoordinate(previousYCoordinate);
    }
  }

  private boolean inScreen() {
    return getXCoordinate() >= 0
        && getXCoordinate() <= getDodgeGameManager().getScreenWidth()
        && getYCoordinate() >= 0
        && getYCoordinate() <= getDodgeGameManager().getScreenHeight();
  }
}

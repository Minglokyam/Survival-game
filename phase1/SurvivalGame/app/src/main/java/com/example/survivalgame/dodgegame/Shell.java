package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class Shell extends DodgeGameItem {
  private float xSpeed;
  private float ySpeed;

  Shell(
      DodgeGameManager dodgeGameManager,
      float xCoordinate,
      float yCoordinate,
      float xSpeed,
      float ySpeed) {
    super(dodgeGameManager, xCoordinate, yCoordinate);
    getPaint().setColor(Color.BLUE);
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  void draw(Canvas c) {
    c.drawOval(
        getXCoordinate(),
        getYCoordinate(),
        getXCoordinate() + 50,
        getYCoordinate() + 50,
        getPaint());
    setXCoordinate(getXCoordinate() + xSpeed);
    setYCoordinate(getYCoordinate() + ySpeed);
  }

  void update() {
    if (getXCoordinate() < 0 || getXCoordinate() + 60 >= getDodgeGameManager().getScreenWidth()) {
      xSpeed *= -1;
    }
  }

  RectF getRectF() {
    return new RectF(
        getXCoordinate(), getYCoordinate(), getXCoordinate() + 50, getYCoordinate() + 50);
  }
}

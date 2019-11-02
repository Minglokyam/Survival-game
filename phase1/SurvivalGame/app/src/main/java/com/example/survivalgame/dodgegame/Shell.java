package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

class Shell extends DodgeGameItem {
  // the speed of the shell in x and y direction.
  private float xSpeed;
  private float ySpeed;

  /** build the shell. */
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

  /** draw the shell on canvas */
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

  /** update the shell to make it move. */
  void update() {
    if (getXCoordinate() < 0 || getXCoordinate() + 60 >= getDodgeGameManager().getScreenWidth()) {
      xSpeed *= -1;
    }
  }

  /** get the rect of the shell. */
  RectF getRectF() {
    return new RectF(
        getXCoordinate(), getYCoordinate(), getXCoordinate() + 50, getYCoordinate() + 50);
  }
}

package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class Shell extends DodgeGameItem {
  //the speed of xCoordinate
  private float xSpeed;
  //the speed of yCoordinate
  private float ySpeed;


  Shell(
      DodgeGameManager dodgeGameManager,
      float xCoordinate,
      float yCoordinate,
      float xSpeed,
      float ySpeed) {
    super(dodgeGameManager);
    getPaint().setColor(Color.BLUE);
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
    setXCoordinate(xCoordinate);
    setYCoordinate(yCoordinate);
  }

  public void draw(Canvas c) {
    c.drawOval(
        getXCoordinate(),
        getYCoordinate(),
        getXCoordinate() + 50,
        getYCoordinate() + 50,
        getPaint());
    setXCoordinate(getXCoordinate() + xSpeed);
    setYCoordinate(getYCoordinate() + ySpeed);
  }

  public void update() {
    if (getXCoordinate() < 0 || getXCoordinate() + 60 >= getDodgeGameManager().getScreenWidth()) {
      xSpeed *= -1;
    }
  }

  /**
   * @return a rectangle
   * the position of rectangle is based on the position of the shell.
   * the method would be called, when we check if the plane is hit by shells or not.
   */
  RectF getRectF() {
    return new RectF(
        getXCoordinate(), getYCoordinate(), getXCoordinate() + 50, getYCoordinate() + 50);
  }
}

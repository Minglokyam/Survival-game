package com.example.survivalgame.dodgegame.model;

import android.graphics.RectF;

public class Shell extends DodgeGameItem {
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
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  /** update the shell to make it move. */
  void update() {
    if (getXCoordinate() < 0 || getXCoordinate() + 60 >= getDodgeGameManager().getScreenWidth()) {
      xSpeed *= -1;
    }
    setXCoordinate(getXCoordinate() + xSpeed);
    setYCoordinate(getYCoordinate() + ySpeed);
  }

  /** get the rect of the shell. */
  RectF getRectF() {
    return new RectF(
        getXCoordinate(), getYCoordinate(), getXCoordinate() + 50, getYCoordinate() + 50);
  }
}

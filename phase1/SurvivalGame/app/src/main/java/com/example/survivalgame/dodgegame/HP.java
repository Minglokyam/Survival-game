package com.example.survivalgame.dodgegame;

import android.graphics.*;
// This Class is used to draw a life bar on the screen, when the hp comes to zero, the life counter
// will minus 1.
public class HP extends DodgeGameItem {
  // the width, length and value of hp.
  private int width;
  private int length;
  private int hpValue;

  /** build the hp presentation of the plane. */
  HP(
      DodgeGameManager dodgeGameManager,
      float xCoordinate,
      float yCoordinate,
      int hpValue,
      int width,
      int length) {
    super(dodgeGameManager, xCoordinate, yCoordinate);
    getPaint().setColor(Color.GREEN);
    this.width = width;
    this.length = length;
    this.hpValue = hpValue;
  }

  /** the getter of hpValue. */
  int getHP() {
    return this.hpValue;
  }

  /** the setter of hpValue. */
  void setHP(int hp) {
    this.hpValue = hp;
  }

  /** draw the hp column on canvas. */
  public void draw(Canvas canvas) {
    RectF hpBar =
        new RectF(
            getXCoordinate(),
            getYCoordinate(),
            getXCoordinate() + width,
            getYCoordinate() + length);
    // RectF hpBar = new RectF(10, 10, 110, 10 + this.length);
    canvas.drawRect(hpBar, getPaint());
  }

  /** update the hp value once the plane is hit by enemy. */
  public void update() {
    if (hpValue >= 0) {
      length = 6 * hpValue + 5;
    } else {
      length = 0;
    }
  }
}

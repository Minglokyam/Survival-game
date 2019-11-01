package com.example.survivalgame.dodgegame;

import android.graphics.*;

public class HP extends DodgeGameItem {
  private int width;
  private int length;
  private int hpValue;

  HP(
      DodgeGameManager dodgeGameManager,
      float xCoordinate,
      float yCoordinate,
      int hpValue,
      int width,
      int length) {
    super(dodgeGameManager);
    getPaint().setColor(Color.GREEN);
    this.width = width;
    this.length = length;
    this.hpValue = hpValue;
    setXCoordinate(xCoordinate);
    setYCoordinate(yCoordinate);
  }

  int getHP() {
    return this.hpValue;
  }

  void setHP(int hp) {
    this.hpValue = hp;
  }

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

  public void update() {
    if (hpValue >= 0) {
      length = 6 * hpValue + 5;
    } else {
      length = 0;
    }
  }
}

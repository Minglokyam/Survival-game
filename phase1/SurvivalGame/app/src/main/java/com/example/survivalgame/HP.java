package com.example.survivalgame;

import android.graphics.*;

public class HP extends DodgeGameItem {
  private int screenWidth;
  private int screenHeight;
  private int length;
  private int hp;

  HP(DodgeGameManager dodgeGameManager, int screenWidth, int screenHeight) {
    super(dodgeGameManager);
    getPaint().setColor(Color.GREEN);
    length = 600;
    hp = 100;
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }

  public int getHP() {
    return this.hp;
  }

  public void setHP(int hp) {
    this.hp = hp;
  }

  public int getX() {
    return 10;
  };

  public int getY() {
    return 10;
  };

  public void draw(Canvas canvas) {
    RectF hpBar =
        new RectF(
            screenWidth - 10, 10, screenWidth - 110, 10 + length);
    // RectF hpBar = new RectF(10, 10, 110, 10 + this.length);
    canvas.drawRect(hpBar, getPaint());
  }

  public void update() {
    if (hp >= 0) {
      length = 6 * hp + 5;
    } else {
      length = 0;
    }
  }
}

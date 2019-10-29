package com.example.survivalgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.*;

public class Shell implements instance {
  private Paint paint;
  private int x;
  private int y;
  private int xSpeed;
  private int ySpeed;
  private DodgeGameManager dodgeGameManager;

  public Shell(DodgeGameManager dodgeGameManager) {
    paint = new Paint();
    paint.setColor(Color.BLUE);
    this.dodgeGameManager = dodgeGameManager;
    double rand = Math.random();
    if (rand > 0.5) {
      this.xSpeed = 8;
    } else {
      this.xSpeed = -8;
    }
    this.ySpeed = (int) (5 * rand) + 5;
    this.x = (int) (Math.random() * DodgeGameActivity.WIDTH);
    this.y = -10;
  }

  public void draw(Canvas c) {
    c.drawOval(x, y, x + 50, y + 50, paint);
    x += xSpeed;
    y += ySpeed;
  }

  public void update() {
    if (x < 0 || x + 60 >= dodgeGameManager.getScreenWidth()) {
      xSpeed *= -1;
    }
  }

  public Rect getRect() {
    return new Rect(x, y, x + 50, y + 50);
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}

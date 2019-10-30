package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import java.util.Random;

public class Shell extends DodgeGameItem {
  private float xSpeed;
  private float ySpeed;

  Shell(DodgeGameManager dodgeGameManager, int screenWidth, int screenHeight) {
    super(dodgeGameManager);
    getPaint().setColor(Color.BLUE);
    Random random = new Random();
    double rand = Math.random();
    if (rand > 0.5) {
      this.xSpeed = 8;
    } else {
      this.xSpeed = -8;
    }
    this.ySpeed = (int) (5 * rand) + 5;
    setXCoordinate(random.nextFloat() * screenWidth);
    setYCoordinate(-10);
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

  RectF getRectF() {
    return new RectF(
        getXCoordinate(), getYCoordinate(), getXCoordinate() + 50, getYCoordinate() + 50);
  }
}

package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;

public class Plane extends DodgeGameItem {
  private HP hp;
  private int counter;
  private int xSpeed;
  private int ySpeed;

  Plane(DodgeGameManager dodgeGameManager, HP hp, float xCoordinate, float yCoordinate) {
    super(dodgeGameManager);
    getPaint().setColor(Color.BLACK);
    setXCoordinate(xCoordinate);
    setYCoordinate(yCoordinate);
    counter = 0;
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

  public void draw(Canvas canvas) {
    if (hp.getHP() != 0) {
      drawPath(canvas);
      setXCoordinate(getXCoordinate() + xSpeed);
      setYCoordinate(getYCoordinate() + ySpeed);

    } else if (counter % 20 <= 10) {
      counter++;
      getPaint().setColor(Color.YELLOW);
    } else if (counter % 20 > 10) {
      counter++;
      System.out.println(counter);
      getPaint().setColor(Color.RED);
    }
    drawPath(canvas);
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

  public void update() {
    xSpeed *= 0.5;
    ySpeed *= 0.5;
  }
}

package com.example.survivalgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;

public class Plane extends DodgeGameItem {
  private HP hp;
  private int counter;
  private int xSpeed;
  private int ySpeed;

  public Plane(DodgeGameManager dodgeGameManager, int screenWidth, int screenHeight, HP hp) {
    super(dodgeGameManager);
    getPaint().setColor(Color.BLACK);
    setXCoordinate(screenWidth / 2);
    setYCoordinate(screenHeight - 600);
    counter = 0;
    this.hp = hp;
  }

  public int getxSpeed() {
    return xSpeed;
  }

  public int getySpeed() {
    return ySpeed;
  }

  void setxSpeed(int xSpeed) {
    this.xSpeed = xSpeed;
  }

  void setySpeed(int ySpeed) {
    this.ySpeed = ySpeed;
  }

  public RectF getRectF(){
    return new RectF(getXCoordinate() - 60, getYCoordinate(), getXCoordinate() + 60, getYCoordinate() + 200);
  }

  public void draw(Canvas canvas) {

    if (hp.getHP() != 0) {
      Path p = new Path();
      p.moveTo(getXCoordinate(), getYCoordinate());
      p.lineTo(getXCoordinate() - 40, getYCoordinate() + 100);
      p.lineTo(getXCoordinate(), getYCoordinate() + 60);
      p.lineTo(getXCoordinate() + 40, getYCoordinate() + 100);
      p.lineTo(getXCoordinate(), getYCoordinate());
      canvas.drawPath(p, getPaint());
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
    Path p = new Path();
    p.moveTo(getXCoordinate(), getYCoordinate());
    p.lineTo(getXCoordinate() - 40, getYCoordinate() + 100);
    p.lineTo(getXCoordinate(), getYCoordinate() + 60);
    p.lineTo(getXCoordinate() + 40, getYCoordinate() + 100);
    p.lineTo(getXCoordinate(), getYCoordinate());
    canvas.drawPath(p, getPaint());
  }

  public void update() {
    xSpeed *= 0.5;
    ySpeed *= 0.5;
  }
}

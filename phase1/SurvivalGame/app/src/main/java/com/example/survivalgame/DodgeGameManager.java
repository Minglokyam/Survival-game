package com.example.survivalgame;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class DodgeGameManager {
  private int screenWidth;
  private int screenHeight;
  static List<instance> shells;
  private HP hp;
  Plane plane;
  private EnemyGenerator enemyGenerator;

  DodgeGameManager(int screenWidth, int screenHeight) {
    plane = new Plane();
    shells = new ArrayList<>();
    enemyGenerator = new EnemyGenerator(this);
    shells.add(plane);
    hp = new HP();
    shells.add(hp);
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }

  int getScreenWidth() {
    return screenWidth;
  }

  public void update() {
    enemyGenerator.Generate();
    for (int i = 0; i < shells.size(); i++) {
      if (shells.get(i).getY()
          > (DodgeGameActivity.HEIGHT
              + 100)) { // This statement removes the object outside the screen.
        shells.remove(i);
      }
      if (shells.get(i).getRect().intersect(plane.getRect())) { // COLLIDES, Player is destroyed
        if (shells.get(i) != plane && shells.get(i) != hp) {
          if (plane.getHp() > 0) {
            plane.setHp(plane.getHp() - 10);
            hp.setHp(plane.getHp() - 10);
            shells.remove(shells.get(i));
            // VIBRATION IMPLEMENTATION?
          }
        }
      }
      if (i < shells.size()) {
        shells.get(i).update();
      }
    }
  }

  public void draw(Canvas canvas) {
    canvas.drawColor(Color.rgb(255, 255, 255));
    for (int i = 0; i < shells.size(); i++) {
      shells.get(i).draw(canvas);
    }
  }
}

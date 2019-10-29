package com.example.survivalgame;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DodgeGameManager {
  private int screenWidth;
  private int screenHeight;
  private List<Shell> shells;
  private HP hp;
  Plane plane;
  private EnemyGenerator enemyGenerator;

  DodgeGameManager(int screenWidth, int screenHeight) {
    hp = new HP(this, screenWidth, screenHeight);
    plane = new Plane(this, screenWidth, screenHeight, hp);
    shells = new ArrayList<>();
    enemyGenerator = new EnemyGenerator(this);
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }

  int getScreenWidth() {
    return screenWidth;
  }

  int getScreenHeight() {
    return screenHeight;
  }

  public int getHP() {
    return hp.getHP();
  }

  public void setHP(int newHP) {
    hp.setHP(newHP);
  }

  public void update() {
    Shell shell;
    enemyGenerator.generateShells(shells);
    Iterator<Shell> shellsIterator = shells.iterator();
    while (shellsIterator.hasNext()) {
      shell = shellsIterator.next();
      if (shell.getYCoordinate()
          > (screenHeight + 100)) { // This statement removes the object outside the screen.
        shellsIterator.remove();
      } else if (shell.getRectF().intersect(plane.getRectF())) {
        if (hp.getHP() > 0) {
          hp.setHP(hp.getHP() - 20);
          shellsIterator.remove();
        }
      } else {
        shell.update();
      }
    }
    hp.update();
  }

  public void draw(Canvas canvas) {
    canvas.drawColor(Color.rgb(255, 255, 255));
    plane.draw(canvas);
    for (int i = 0; i < shells.size(); i++) {
      shells.get(i).draw(canvas);
    }
    hp.draw(canvas);
  }
}

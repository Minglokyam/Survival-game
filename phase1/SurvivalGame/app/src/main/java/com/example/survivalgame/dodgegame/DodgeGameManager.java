package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class DodgeGameManager {
  private int screenWidth;
  private int screenHeight;
  private List<Shell> shells;
  private HP hp;
  private Plane plane;
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

  int getHP() {
    return hp.getHP();
  }

  void setHP(int newHP) {
    hp.setHP(newHP);
  }

  Plane getPlane() {
    return plane;
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
    for (Shell shell : shells) {
      shell.draw(canvas);
    }
    hp.draw(canvas);
  }
}

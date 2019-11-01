package com.example.survivalgame.dodgegame;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * DodgeGameManager is used to combine all the items we are going to use for the game.
 */
class DodgeGameManager {
  private int screenWidth;
  private int screenHeight;
  private List<Shell> shells;
  private HP hp;
  private Plane plane;
  private HPFactory hpFactory;
  private PlaneFactory planeFactory;
  private EnemyGenerator enemyGenerator;

  DodgeGameManager(int screenWidth, int screenHeight) {
    hpFactory = new HPFactory();
    planeFactory = new PlaneFactory();
    hp = hpFactory.createHP(this, screenWidth, screenHeight);
    plane = planeFactory.createPlane(this, screenWidth, screenHeight, hp);
    shells = new ArrayList<>();
    enemyGenerator = new EnemyGenerator(this);
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
  }

  /**
   * those methods are the getter and setter for attributes.
   */
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
    plane.update();
  }

  /**
   * draw method would call other draws method from other classes
   *
   */
  public void draw(Canvas canvas) {
    canvas.drawColor(Color.rgb(255, 255, 255));
    plane.draw(canvas);
    for (Shell shell : shells) {
      shell.draw(canvas);
    }
    hp.draw(canvas);
  }
}

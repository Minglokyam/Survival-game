package com.example.survivalgame.dodgegame.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** DodgeGameManager is used to combine all the items we are going to use for the game. */
public class DodgeGameManager {
  private int screenWidth;
  private int screenHeight;
  private List<Shell> shells;
  private HP hp;
  private Plane plane;
  private HPFactory hpFactory;
  private PlaneFactory planeFactory;
  private EnemyGenerator enemyGenerator;

  public DodgeGameManager(int screenWidth, int screenHeight) {
    hpFactory = new HPFactory();
    planeFactory = new PlaneFactory();
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    hp = hpFactory.createHP(this, screenWidth);
    plane = planeFactory.createPlane(this, screenWidth, screenHeight, hp);
    shells = new ArrayList<>();
    enemyGenerator = new EnemyGenerator(this);
  }

  /** those methods are the getter and setter for attributes. */
  int getScreenWidth() {
    return screenWidth;
  }

  int getScreenHeight() {
    return screenHeight;
  }

  public Plane getPlane() {
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
        if (hp.getHPValue() > 0) {
          hp.setHPValue(hp.getHPValue() - 20);
          shellsIterator.remove();
        }
      } else {
        shell.update();
      }
    }
    hp.update();
    plane.update();
  }

  public List<Shell> getShells() {
    return shells;
  }

  public HP getHP() {
    return hp;
  }
}

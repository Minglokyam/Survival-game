package com.example.survivalgame.dodgegame;

import java.util.List;

class EnemyGenerator {
  private DodgeGameManager dodgeGameManager;
  private int counter;

  public EnemyGenerator(DodgeGameManager dodgeGameManager) {
    this.dodgeGameManager = dodgeGameManager;
    counter = 1;
  }

  public void generateShells(List<Shell> shells) {
    if (counter % 40 == 0) {
      int num = (int) (Math.random() * 0.5) + 1; // enemy generated this round
      for (int i = 0; i < num; i++) {
        shells.add(
            new Shell(
                dodgeGameManager,
                dodgeGameManager.getScreenWidth(),
                dodgeGameManager.getScreenHeight()));
        counter = 1;
      }
    } else {
      counter++;
    }
  }
}

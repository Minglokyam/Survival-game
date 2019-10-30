package com.example.survivalgame.dodgegame;

import java.util.List;

class EnemyGenerator {
  private ShellFactory shellFactory;
  private DodgeGameManager dodgeGameManager;
  private int counter;

  EnemyGenerator(DodgeGameManager dodgeGameManager) {
    shellFactory = new ShellFactory();
    this.dodgeGameManager = dodgeGameManager;
    counter = 1;
  }

  void generateShells(List<Shell> shells) {
    Shell shell;
    if (counter % 40 == 0) {
      int num = (int) (Math.random() * 0.5) + 1; // enemy generated this round
      for (int i = 0; i < num; i++) {
        shell =
            shellFactory.createShell(
                dodgeGameManager,
                dodgeGameManager.getScreenWidth(),
                dodgeGameManager.getScreenHeight());
        shells.add(shell);
        counter = 1;
      }
    } else {
      counter++;
    }
  }
}

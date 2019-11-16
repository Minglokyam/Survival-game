package com.example.survivalgame.dodgegame.model;

import java.util.List;
// The class that generates "shell"s at some random rate. If we want to change the amount or the
// rate the "shell" appears, we can change it here without changing any other code.
class EnemyGenerator {
  private ShellFactory shellFactory;
  private DodgeGameManager dodgeGameManager;
  private int counter;

  /** build the EnemyGenerator. */
  EnemyGenerator(DodgeGameManager dodgeGameManager) {
    shellFactory = new ShellFactory();
    this.dodgeGameManager = dodgeGameManager;
    counter = 1;
  }

  /** randomly generate the shells. */
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

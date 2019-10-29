package com.example.survivalgame;

class EnemyGenerator {
  private DodgeGameManager dodgeGameManager;
  private int counter;

  EnemyGenerator(DodgeGameManager dodgeGameManager) {
    this.dodgeGameManager = dodgeGameManager;
    counter = 1;
  }

  void Generate() {
    if (counter % 40 == 0) {
      int num = (int) (Math.random() * 0.5) + 1; // enemy generated this round
      for (int i = 0; i < num; i++) {
        dodgeGameManager.shells.add(new Shell(dodgeGameManager));
        counter = 1;
      }
    } else {
      counter++;
    }
  }
}

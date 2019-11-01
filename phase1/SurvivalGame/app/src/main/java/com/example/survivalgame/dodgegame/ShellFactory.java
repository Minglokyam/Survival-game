package com.example.survivalgame.dodgegame;

import java.util.Random;

class ShellFactory {
  Shell createShell(DodgeGameManager dodgeGameManager, int screenWidth, int screenHeight) {
    return new Shell(
        dodgeGameManager,
        assignXCoordinate(screenWidth),
        assignYCoordinate(),
        assignXSpeed(),
        assignYSpeed());
  }

  private float assignXCoordinate(int screenWidth) {
    Random random = new Random();
    return random.nextFloat() * screenWidth;
  }

  private float assignYCoordinate() {
    return -10;
  }

  private float assignXSpeed() {
    float xSpeed = 0;
    if (Math.random() < 0.5) {
      xSpeed = 8;
    } else {
      xSpeed = -8;
    }
    return xSpeed;
  }

  private float assignYSpeed() {
    Random random = new Random();
    return 5 * random.nextFloat() + 5;
  }
}

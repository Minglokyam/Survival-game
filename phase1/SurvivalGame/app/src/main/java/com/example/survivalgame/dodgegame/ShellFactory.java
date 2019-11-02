package com.example.survivalgame.dodgegame;

import java.util.Random;

// The factory for shell class, which produces shells that players are going to dodge.
class ShellFactory {
  Shell createShell(DodgeGameManager dodgeGameManager, int screenWidth, int screenHeight) {
    return new Shell(
        dodgeGameManager,
        assignXCoordinate(screenWidth),
        assignYCoordinate(),
        assignXSpeed(),
        assignYSpeed());
  }

  /** randomly assign the x coordinate. */
  private float assignXCoordinate(int screenWidth) {
    Random random = new Random();
    return random.nextFloat() * screenWidth;
  }

  /** randomly assign the y coordinate. */
  private float assignYCoordinate() {
    return -10;
  }

  /** randomly assign the moving speed in x direction. */
  private float assignXSpeed() {
    float xSpeed;
    if (Math.random() < 0.5) {
      xSpeed = 8;
    } else {
      xSpeed = -8;
    }
    return xSpeed;
  }

  /** randomly assign the moving speed in y direction. */
  private float assignYSpeed() {
    Random random = new Random();
    return 5 * random.nextFloat() + 5;
  }
}

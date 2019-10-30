package com.example.survivalgame.dodgegame;

class HPFactory {
  HP createHP(DodgeGameManager dodgeGameManager, int screenWidth, int screenHeight) {
    float xCoordinate = screenWidth - 110;
    float yCoordinate = 10;
    int width = 100;
    int length = 600;
    int hpValue = 100;
    return new HP(dodgeGameManager, xCoordinate, yCoordinate, hpValue, width, length);
  }
}

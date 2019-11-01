package com.example.survivalgame.dodgegame;

class PlaneFactory {
  Plane createPlane(DodgeGameManager dodgeGameManager, int screenWidth, int screenHeight, HP hp) {
    float xCoordinate = screenWidth / 2;
    float yCoordinate = screenHeight - 600;
    return new Plane(dodgeGameManager, hp, xCoordinate, yCoordinate);
  }
}

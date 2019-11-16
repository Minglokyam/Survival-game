package com.example.survivalgame.dodgegame.model;

// The class which produces the plane
class PlaneFactory {
  Plane createPlane(DodgeGameManager dodgeGameManager, int screenWidth, int screenHeight, HP hp) {
    float xCoordinate = screenWidth / 2;
    float yCoordinate = screenHeight / 2;
    return new Plane(dodgeGameManager, hp, xCoordinate, yCoordinate);
  }
}

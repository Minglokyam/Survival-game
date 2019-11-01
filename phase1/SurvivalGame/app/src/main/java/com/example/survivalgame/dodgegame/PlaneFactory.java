package com.example.survivalgame.dodgegame;
//The class which produces the plane
class PlaneFactory {
  Plane createPlane(DodgeGameManager dodgeGameManager, int screenWidth, int screenHeight, HP hp) {
    float xCoordinate = screenWidth / 2;
    float yCoordinate = screenHeight - 600;
    return new Plane(dodgeGameManager, hp, xCoordinate, yCoordinate);
  }
}

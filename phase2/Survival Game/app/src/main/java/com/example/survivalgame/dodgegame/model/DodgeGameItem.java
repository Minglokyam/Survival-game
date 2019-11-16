package com.example.survivalgame.dodgegame.model;

// The super class for all game objects in Dodge Game
abstract class DodgeGameItem {
  /** The x-coordinate of this DodgeGameItem */
  private float xCoordinate;
  /** The y-coordinate of this DodgeGameItem */
  private float yCoordinate;
  /** An instance of DodgeGameManager */
  private DodgeGameManager dodgeGameManager;

  /** Create a DodgeGameItem. */
  DodgeGameItem(DodgeGameManager dodgeGameManager, float xCoordinate, float yCoordinate) {
    this.xCoordinate = xCoordinate;
    this.dodgeGameManager = dodgeGameManager;
    this.yCoordinate = yCoordinate;
  }

  /** A getter of dodgeGameManager */
  DodgeGameManager getDodgeGameManager() {
    return dodgeGameManager;
  }

  /** A getter of xCoordinate */
  public float getXCoordinate() {
    return xCoordinate;
  }

  /** A setter of xCoordinate */
  public void setXCoordinate(float newXCoordinate) {
    this.xCoordinate = newXCoordinate;
  }

  /** A getter of yCoordinate */
  public float getYCoordinate() {
    return yCoordinate;
  }

  /** A setter of yCoordinate */
  public void setYCoordinate(float newYCoordinate) {
    this.yCoordinate = newYCoordinate;
  }
}

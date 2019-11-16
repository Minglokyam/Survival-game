package com.example.survivalgame.dodgegame.model;

// This Class is used to draw a life bar on the screen, when the hp comes to zero, the life counter
// will minus 1.
public class HP extends DodgeGameItem {
  // the width, length and value of hp.
  private float width;
  private float length;
  private int hpValue;

  /** build the hp presentation of the plane. */
  HP(
      DodgeGameManager dodgeGameManager,
      float xCoordinate,
      float yCoordinate,
      int hpValue,
      int width,
      int length) {
    super(dodgeGameManager, xCoordinate, yCoordinate);
    this.width = width;
    this.length = length;
    this.hpValue = hpValue;
  }

  /** the getter of hpValue. */
  public int getHPValue() {
    return this.hpValue;
  }

  /** the setter of hpValue. */
  public void setHPValue(int newHPValue) {
    this.hpValue = newHPValue;
  }

  public float getWidth() {
    return width;
  }

  public float getLength() {
    return length;
  }

  /** update the hp value once the plane is hit by enemy. */
  public void update() {
    if (hpValue >= 0) {
      length = 6 * hpValue + 5;
    } else {
      length = 0;
    }
  }
}

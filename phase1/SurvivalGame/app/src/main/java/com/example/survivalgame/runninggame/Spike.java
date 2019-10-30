package com.example.survivalgame.runninggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.survivalgame.runninggame.Ground;
import com.example.survivalgame.runninggame.RunningGameView;

public class Spike {

  private int x, y;
  private Bitmap bmp;
  private RunningGameView view;

  /** build a spike. */
  public Spike(RunningGameView view, Bitmap bmp, int x) {
    this.view = view;
    this.bmp = bmp;
    this.x = x;
    this.y = view.getHeight() - Ground.height - bmp.getHeight();
  }

  /** update the spike and make the spike move. */
  private void update() {
    // move the spikes with movingSpeed.
    x -= RunningGameView.movingSpeed;
  }

  /** draw the spike. */
  public void draw(Canvas canvas) {
    // first update the spike.
    update();

    // then draw the coin by the rect.
    Rect a = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
    Rect b = new Rect(x, y, x + bmp.getWidth(), y + bmp.getHeight());
    canvas.drawBitmap(bmp, a, b, null);
  }

  /** check whether the runner touched the spike. */
  public boolean checkCollision(Rect runner, Rect spikes) {
    return Rect.intersects(runner, spikes);
  }

  /** get the rectangle of the spike. */
  public Rect getRect() {
    return new Rect(this.x, this.y, this.x + bmp.getWidth(), this.y + bmp.getHeight());
  }

  /** the getter of the first coordinate of the spike. */
  public int getX() {
    return x;
  }
}

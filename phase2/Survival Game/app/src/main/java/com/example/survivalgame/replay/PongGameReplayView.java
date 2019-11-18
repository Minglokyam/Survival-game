package com.example.survivalgame.replay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.survivalgame.User;
import com.example.survivalgame.ponggame.view.PongActivityInterface;
import com.example.survivalgame.ponggame.view.PongView;

public class PongGameReplayView extends SurfaceView implements PongView {
  private PongActivityInterface pongActivityInterface;

  private Canvas canvas;

  private Paint paintShape;

  private Paint paintText;

  /** The replay presenter of this game */
  private PongGameReplayPresenter replayPresenter;

  public PongGameReplayView(
      Context context, PongActivityInterface pongActivityInterface, User user) {
    super(context);
    this.pongActivityInterface = pongActivityInterface;
    paintShape = new Paint();
    paintShape.setColor(Color.MAGENTA);
    paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);

    replayPresenter = new PongGameReplayPresenter(this, user);

    getHolder()
        .addCallback(
            new SurfaceHolder.Callback() {
              @Override
              public void surfaceCreated(SurfaceHolder holder) {
                replayPresenter.setRunning(true);
                replayPresenter.start();
              }

              @Override
              public void surfaceChanged(SurfaceHolder holder, int a, int b, int c) {}

              @Override
              public void surfaceDestroyed(SurfaceHolder holder) {}
            });
  }

  @Override
  public void lockCanvas() {
    canvas = getHolder().lockCanvas();
  }

  @Override
  public void unlockCanvasAndPost() {
    getHolder().unlockCanvasAndPost(canvas);
  }

  @Override
  public void toMain() {
    pongActivityInterface.toMain();
  }

  @Override
  public void toDodge() {
    pongActivityInterface.toDodge();
  }

  @Override
  public void setTouchReference(float newTouchReference) {}

  @Override
  public void drawCircle(float xCoordinate, float yCoordinate, float radius) {
    canvas.drawCircle(xCoordinate, yCoordinate, radius, paintShape);
  }

  @Override
  public void drawRect(float xCoordinate, float yCoordinate, float width, float height) {
    canvas.drawRect(
        xCoordinate, yCoordinate, xCoordinate + width, yCoordinate + height, paintShape);
  }

  @Override
  public void drawText(String string, float xCoordinate, float yCoordinate) {
    canvas.drawText(string, xCoordinate, yCoordinate, paintText);
  }

  @Override
  public void drawColor(int red, int green, int blue) {
    canvas.drawColor(Color.rgb(red, green, blue));
  }

  @Override
  public void clearCanvas() {
    canvas = null;
  }
}

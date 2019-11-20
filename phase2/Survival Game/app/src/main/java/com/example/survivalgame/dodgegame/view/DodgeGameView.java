package com.example.survivalgame.dodgegame.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.survivalgame.User;
import com.example.survivalgame.dodgegame.model.DodgeGameManager;
import com.example.survivalgame.dodgegame.presenter.DodgeGamePresenter;

// This is the class which is responsible for rendering the game objects.
public class DodgeGameView extends SurfaceView implements DodgeView {
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
  private DodgeActivityInterface dodgeActivityInterface;
  private DodgeGamePresenter dodgeGamePresenter;
  private Paint paintText;
  private Canvas canvas;

  // Dependency Injection
  public DodgeGameView(Context context, DodgeActivityInterface dodgeActivityInterface, User user) {
    super(context);
    this.dodgeActivityInterface = dodgeActivityInterface;
    dodgeGamePresenter =
        new DodgeGamePresenter(this, new DodgeGameManager(screenWidth, screenHeight), user);

    paintText = new Paint();
    paintText.setColor(Color.BLACK);
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);

    Paint paint = new Paint();
    paint.setColor(Color.BLUE);

    SurfaceHolder surfaceHolder = getHolder();
    surfaceHolder.addCallback(
        new SurfaceHolder.Callback() {

          @Override
          public void surfaceCreated(SurfaceHolder holder) {
            dodgeGamePresenter.setRunning(true);
            dodgeGamePresenter.start();
          }

          @Override
          public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

          @Override
          public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            dodgeGamePresenter.setRunning(false);
            while (retry) {
              try {
                dodgeGamePresenter.join();
                retry = false;
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            }
          }
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
  public void toScoreBoard() {
    dodgeActivityInterface.toScoreBoard();
  }

  @Override
  public void clearCanvas() {
    canvas = null;
  }

  // This is the event listener, which enables the player to control the plane.
  @Override
  public boolean onTouchEvent(MotionEvent motionEvent) {
    if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
      dodgeGamePresenter.setMove();
      dodgeGamePresenter.setXTouchReference(motionEvent.getX());
      dodgeGamePresenter.setYTouchReference(motionEvent.getY());
    } else {
      dodgeGamePresenter.setStop();
    }
    return true;
  }

  @Override
  public void drawColor(int red, int green, int blue) {
    canvas.drawColor(Color.rgb(red, green, blue));
  }

  @Override
  public void drawText(String string, float xCoordinate, float yCoordinate) {
    canvas.drawText(string, xCoordinate, yCoordinate, paintText);
  }

  @Override
  public void drawOval(float xCoordinate, float yCoordinate) {
    Paint paint = new Paint();
    paint.setColor(Color.BLUE);
    canvas.drawOval(xCoordinate, yCoordinate, xCoordinate + 50, yCoordinate + 50, paint);
  }

  @Override
  public void drawRectF(float xCoordinate, float yCoordinate, float width, float length) {
    RectF rectF = new RectF(xCoordinate, yCoordinate, xCoordinate + width, yCoordinate + length);
    Paint paint = new Paint();
    paint.setColor(Color.GREEN);
    canvas.drawRect(rectF, paint);
  }

  @Override
  public void drawPath(float xCoordinate, float yCoordinate) {
    Paint paint = new Paint();
    paint.setColor(Color.BLACK);
    Path path = new Path();
    path.moveTo(xCoordinate, yCoordinate);
    path.lineTo(xCoordinate - 40, yCoordinate + 100);
    path.lineTo(xCoordinate, yCoordinate + 60);
    path.lineTo(xCoordinate + 40, yCoordinate + 100);
    path.lineTo(xCoordinate, yCoordinate);
    canvas.drawPath(path, paint);
  }
}

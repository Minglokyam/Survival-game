package com.example.survivalgame.runninggame.view;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.content.Context;
import android.view.SurfaceView;
import android.graphics.Bitmap;
import android.view.SurfaceHolder.Callback;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.survivalgame.R;
import com.example.survivalgame.general.User;
import com.example.survivalgame.runninggame.model.RectFactory;
import com.example.survivalgame.runninggame.model.RunningGameManager;
import com.example.survivalgame.runninggame.presenter.RunningGamePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RunningGameView extends SurfaceView implements RunningView {
  private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
  private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

  private RunningGamePresenter runningGamePresenter;

  // the image holder of the runner.
  private Bitmap runnerBMP;
  // the image holder of the coin.
  private Bitmap coinBMP;
  // the image holder of the ground.
  private Bitmap groundBMP;
  // the image holder of the spike.
  private Bitmap spikeBMP;

  private Map<String, List<Integer>> bmpSizeMap;

  private RunningActivityInterface runningActivityInterface;

  private Paint paintText;

  private Canvas canvas;

  /** set up the GameView. */
  public RunningGameView(
      Context context, RunningActivityInterface runningActivityInterface, User user) {
    super(context);
    bmpSizeMap = new HashMap<>();
    // get the image of the objects.
    runnerBMP = BitmapFactory.decodeResource(getResources(), R.drawable.runner);
    addMap("runner", runnerBMP);
    coinBMP = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
    addMap("coin", coinBMP);
    groundBMP = BitmapFactory.decodeResource(getResources(), R.drawable.ground);
    addMap("ground", groundBMP);
    spikeBMP = BitmapFactory.decodeResource(getResources(), R.drawable.spikes);
    addMap("spike", spikeBMP);

    paintText = new Paint();
    paintText.setColor(Color.BLACK);
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);
    this.runningActivityInterface = runningActivityInterface;
    runningGamePresenter =
        new RunningGamePresenter(
            this,
            new RunningGameManager(screenWidth, screenHeight, bmpSizeMap),
            new RectFactory(),
            user);
    getHolder()
        .addCallback(
            new Callback() {
              @Override
              public void surfaceCreated(SurfaceHolder holder0) {
                runningGamePresenter.setRunning(true);
                runningGamePresenter.start();
              }

              @Override
              public void surfaceDestroyed(SurfaceHolder holder0) {}

              @Override
              public void surfaceChanged(SurfaceHolder holder0, int a, int b, int c) {}
            });
  }

  private void addMap(String bitmapName, Bitmap bitmap) {
    List<Integer> bmpSizeList = new ArrayList<>();
    bmpSizeList.add(bitmap.getWidth());
    bmpSizeList.add(bitmap.getHeight());
    bmpSizeMap.put(bitmapName, bmpSizeList);
  }

  /** make the runner jump and restart the game once touching on the screen. */
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    runningGamePresenter.onTouch();
    return false;
  }

  @Override
  public void clearCanvas() {
    canvas = null;
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
  public void toUpload() {
    runningActivityInterface.toUpload();
  }

  @Override
  public void toPong() {
    runningActivityInterface.toPong();
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
  public void drawBitmap(String bmpName, int xCoordinate, int yCoordinate) {
    switch (bmpName) {
      case "runner":
        canvas.drawBitmap(runnerBMP, xCoordinate, yCoordinate, null);
        break;
      case "ground":
        canvas.drawBitmap(groundBMP, xCoordinate, yCoordinate, null);
        break;
      default:
        break;
    }
  }

  @Override
  public void drawBitmap(String bmpName, Rect rectA, Rect rectB) {
    switch (bmpName) {
      case "spike":
        canvas.drawBitmap(spikeBMP, rectA, rectB, null);
        break;
      case "coin":
        canvas.drawBitmap(coinBMP, rectA, rectB, null);
        break;
      default:
        break;
    }
  }
}

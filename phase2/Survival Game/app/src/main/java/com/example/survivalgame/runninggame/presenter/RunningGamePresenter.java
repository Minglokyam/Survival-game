package com.example.survivalgame.runninggame.presenter;

// We understand that importing Rect in presenter will violate MVP rule. The presenter should not
// have any android class. We can solve it by creating our own Rect class. But this will be
// redundant and unnecessarily increase the number of classes.
import android.graphics.Rect;

import com.example.survivalgame.User;
import com.example.survivalgame.runninggame.model.RandomItem;
import com.example.survivalgame.runninggame.model.RectFactory;
import com.example.survivalgame.runninggame.model.RunningGameManager;
import com.example.survivalgame.runninggame.model.Spike;
import com.example.survivalgame.runninggame.view.RunningView;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class RunningGamePresenter extends Thread implements RunningPresenter {
  private RectFactory rectFactory;
  private RunningView runningView;
  private User user;
  private RunningGameManager runningGameManager;
  private long FPS = 30;
  // the duration time of the running game.
  private Duration runningDuration;

  // check whether the runner is running
  private boolean running;

  //We use dependency inversion principle to inject rect factory and running game manager
  public RunningGamePresenter(
      RunningView runningView,
      RunningGameManager runningGameManager,
      RectFactory rectFactory,
      User user) {
    this.rectFactory = rectFactory;
    this.runningGameManager = runningGameManager;
    runningGameManager.setRunningPresenter(this);
    this.runningView = runningView;
    this.user = user;
    runningDuration = Duration.ofSeconds(30);
  }

  public void setRunning(boolean newRunning) {
    running = newRunning;
  }

  @Override
  public void run() {
    long sleepTime;
    while (running) {
      long startTime = System.currentTimeMillis();
      runningView.clearCanvas();
      try {
        runningView.lockCanvas();
        synchronized (this) {
          runningGameManager.update();
          checkEndGame();
          runningView.drawColor(255, 255, 255);
          runningView.drawText("Life: " + user.getLife(), 0, 32);
          runningView.drawText("Total time: " + user.getTotalDuration().getSeconds(), 0, 64);
          runningView.drawText("Game time: " + runningDuration.getSeconds(), 0, 96);
          runningView.drawText("Score: " + user.getScore(), 0, 128);

          runningView.drawBitmap(
              "runner",
              runningGameManager.getRunner().getXCoordinate(),
              runningGameManager.getRunner().getYCoordinate());
          runningView.drawBitmap(
              "ground",
              runningGameManager.getGround().getXCoordinate(),
              runningGameManager.getGround().getNewYCoordinate());
          drawRandomItems();
        }
      } finally {
        runningView.unlockCanvasAndPost();
      }
      sleepTime = FPS - (System.currentTimeMillis() - startTime);
      try {
        if (sleepTime > 0) {
          sleep(sleepTime);
        } else {
          sleep(10);
        }
      } catch (Exception e) {
      }
      long timeInterval = System.currentTimeMillis() - startTime;
      user.setTotalDuration(user.getTotalDuration().plusMillis(timeInterval));
      runningDuration = runningDuration.minusMillis(timeInterval);
      if (timeInterval > 1) {
        FPS = 1000 / timeInterval;
      }
    }
  }

  private void checkEndGame() {
    user.setScore(user.getScore() + 1);
    // when the game time runs out, jump to next game.
    if (runningDuration.getSeconds() <= 0) {
      running = false;
      runningView.toPong();
    } else if (user.getLife() == 0) {
      running = false;
      runningView.toScoreBoard();
    }
  }

  public void onTouch() {
    runningGameManager.onTouch();
  }

  public void addScore() {
    user.setScore(user.getScore() + 100);
  }

  public void reduceLife() {
    user.setLife(user.getLife() - 1);
  }

  private void drawRandomItems() {
    List<RandomItem> randomItems = runningGameManager.getRandomItems();
    for (RandomItem randomItem : randomItems) {
      if (randomItem instanceof Spike) {
        Rect rectA =
            rectFactory.createRect(
                0, 0, randomItem.getBmpSizeList().get(0), randomItem.getBmpSizeList().get(1));

        Rect rectB =
            rectFactory.createRect(
                randomItem.getXCoordinate(),
                randomItem.getYCoordinate(),
                randomItem.getXCoordinate() + randomItem.getBmpSizeList().get(0),
                randomItem.getYCoordinate() + randomItem.getBmpSizeList().get(1));
        runningView.drawBitmap("spike", rectA, rectB);
      } else {
        Rect rectA =
            rectFactory.createRect(
                randomItem.getCurrentPosition() * randomItem.getBmpSizeList().get(0) / 4,
                0,
                randomItem.getCurrentPosition() * randomItem.getBmpSizeList().get(0) / 4
                    + randomItem.getBmpSizeList().get(0) / 4,
                42);
        Rect rectB =
            rectFactory.createRect(
                randomItem.getXCoordinate(),
                randomItem.getYCoordinate(),
                randomItem.getXCoordinate() + randomItem.getBmpSizeList().get(0) / 4,
                randomItem.getYCoordinate() + 42);
        runningView.drawBitmap("coin", rectA, rectB);
      }
    }
  }
}

package com.example.survivalgame.runninggame.model;

// We understand that importing Rect in model will violate MVP rule. The model should not have
// android class. We can solve it by creating our own Rect class. But this will be redundant and
// unnecessarily increase the number of classes.
import android.graphics.Rect;

import com.example.survivalgame.runninggame.presenter.RunningPresenter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RunningGameManager {
  private int screenWidth;

  private int screenHeight;

  private Map<String, List<Integer>> bmpSizeMap;

  // the moving speed of the objects in the game.
  private int movingSpeed = 10;

  private RunnerFactory runnerFactory;

  private RandomItemFactory randomItemFactory;

  private GroundFactory groundFactory;

  // the runner.
  private Runner runner;

  // ground.
  private Ground ground;

  // the timer of the coin.
  private int timerCoins = 0;

  // the timer of the spike.
  private int timerSpike = 0;

  // random timer of the spike.
  private int timerRandomSpikes = 0;

  // The height of ground
  private int groundHeight;

  private RunningPresenter runningPresenter;

  private List<RandomItem> randomItems = new ArrayList<>();

  public RunningGameManager(
      RunningPresenter runningPresenter,
      int screenWidth,
      int screenHeight,
      Map<String, List<Integer>> bmpSizeMap) {
    this.runningPresenter = runningPresenter;
    this.screenWidth = screenWidth;
    this.screenHeight = screenHeight;
    this.bmpSizeMap = bmpSizeMap;

    randomItemFactory = new RandomItemFactory();
    runnerFactory = new RunnerFactory();
    groundFactory = new GroundFactory();
    // add runner and ground to the game.
    ground = groundFactory.createGround(0, 0, movingSpeed, bmpSizeMap.get("ground"), screenHeight);
    groundHeight = bmpSizeMap.get("ground").get(1) + 200;
    runner =
        runnerFactory.createRunner(
            50, 50, movingSpeed, bmpSizeMap.get("runner"), groundHeight, screenHeight);
  }

  public Runner getRunner() {
    return runner;
  }

  public Ground getGround() {
    return ground;
  }

  public List<RandomItem> getRandomItems() {
    return randomItems;
  }

  /** update the coin when it moves out of the screen or the runner touches it. */
  private void updateItems() {

    Iterator<RandomItem> randomItemIterator = randomItems.iterator();

    while (randomItemIterator.hasNext()) {

      RandomItem randomItem = randomItemIterator.next();
      Rect runnerRect = runner.getRect();
      Rect rect = randomItem.getRect();

      if (randomItem.checkCollision(runnerRect, rect)) {
        collideAction(randomItem);
        // remove the random item after touching.
        randomItemIterator.remove();
        break;
      }
    }
  }

  private void collideAction(RandomItem randomItem) {
    if (randomItem instanceof Coin) {
      // add points to the score when the runner touches a coin.
      runningPresenter.addScore();
    } else {
      runningPresenter.reduceLife();
    }
  }

  /** update the coins and spikes. */
  public void update() {
    for (RandomItem randomItem : randomItems) {
      randomItem.update();
    }
    runner.update();
    updateTimer();
    randomGenerateItems();
    removeRandomItems();
    updateItems();
  }

  /** update the timers. */
  private void updateTimer() {
    timerCoins++;
    timerSpike++;
  }

  /** randomly generate the coins and spikes. */
  private void randomGenerateItems() {
    // randomly generate spikes.
    randomGenerateSpikes();
    // randomly generate coins.
    randomGenerateCoins();
  }

  /** remove random items that are not inside the screen */
  private void removeRandomItems() {
    Iterator<RandomItem> randomItemIterator = randomItems.iterator();
    while (randomItemIterator.hasNext()) {
      RandomItem randomItem = randomItemIterator.next();
      if (randomItem.outOfScreen()) {
        randomItemIterator.remove();
      }
    }
  }

  /**
   * randomly generate the spikes in the running game. citation:
   * https://www.youtube.com/watch?v=zyCZEaw3Gow&t=266s
   */
  private void randomGenerateSpikes() {
    switch (timerRandomSpikes) {
        // three different cases to generate spikes in different distances.
      case 0:
        if (timerSpike >= 100) {
          makeSpikes();
        }
        break;
      case 1:
        if (timerSpike >= 125) {
          makeSpikes();
        }
        break;
      case 2:
        if (timerSpike >= 150) {
          makeSpikes();
        }
        break;
    }
  }

  private void makeSpikes() {
    RandomItem spike =
        randomItemFactory.createRandomItem(
            screenWidth + 24,
            24,
            movingSpeed,
            bmpSizeMap.get("spike"),
            groundHeight,
            screenWidth,
            screenHeight,
            randomItemFactory.SPIKE);

    randomItems.add(spike);
    Random spikesRandom = new Random();
    timerRandomSpikes = spikesRandom.nextInt(3);
    timerSpike = 0;
  }

  /**
   * randomly generate the coins in the running game. citation:
   * https://www.youtube.com/watch?v=lmAmr8Efu34&t=492s
   */
  private void randomGenerateCoins() {
    if (timerCoins >= 100) {
      // randomly generate int 0 and 1 to decide which case the coins are generated.
      int random = new Random().nextInt(2);
      switch (random) {
        case 0:
          // construct five consecutive coins in same height.
          for (int i = 1; i < 6; i++) {
            makeCoins(i * 64, 130);
          }
          break;

        case 1:
          // construct three consecutive coins in different height.
          makeCoins(32, 150);
          makeCoins(96, 130);
          makeCoins(160, 150);
          break;
      }
      // reset the timer.
      timerCoins = 0;
    }
  }

  private void makeCoins(int xCoordinate, int yCoordinate) {
    RandomItem coin =
        randomItemFactory.createRandomItem(
            screenWidth + xCoordinate,
            yCoordinate,
            movingSpeed,
            bmpSizeMap.get("coin"),
            groundHeight,
            screenWidth,
            screenHeight,
            randomItemFactory.COIN);
    randomItems.add(coin);
  }

  public void onTouch() {
    runner.onTouch();
  }
}

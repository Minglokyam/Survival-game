package com.example.survivalgame.runninggame;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.survivalgame.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

class RunningGameManager {

  private RunningGameView runningGameView;

  private RunnerFactory runnerFactory;

  private SpikeFactory spikeFactory;

  private CoinFactory coinFactory;

  private GroundFactory groundFactory;

  // the runner.
  Runner runner;

  // a list of coin.
  private List<Coin> coins = new ArrayList<>();

  // ground.
  private Ground ground;

  // a list of spikes.
  private List<Spike> spikes = new ArrayList<>();

  // the timer of the coin.
  private int timerCoins = 0;

  // the timer of the spike.
  private int timerSpike = 0;

  // random timer of the spike.
  private int timerRandomSpikes = 0;

  // The height of ground
  private int groundHeight;

  RunningGameManager(RunningGameView runningGameView) {
    this.runningGameView = runningGameView;
    spikeFactory = new SpikeFactory();
    coinFactory = new CoinFactory();
    runnerFactory = new RunnerFactory();
    groundFactory = new GroundFactory();
    // add runner and ground to the game.
    ground = groundFactory.createGround(runningGameView, runningGameView.getGroundBMP(), 0, 0);
    groundHeight = ground.getHeight();
    runner =
        runnerFactory.createRunner(
            runningGameView, runningGameView.getRunnerBMP(), 50, 50, groundHeight);
  }

  /** update the coin when it moves out of the screen or the runner touches it. */
  private void updateCoin(User user) {
    boolean collide = false;
    Iterator<Coin> coinIterator = coins.iterator();
    while (coinIterator.hasNext() && !collide) {
      Coin coin = coinIterator.next();
      Rect runnerRect = runner.getRect();
      Rect coinRect = coin.getRect();
      if (coin.checkCollision(runnerRect, coinRect)) {
        // remove the coin once the runner touch this coin.
        coinIterator.remove();
        // add points to the score when the runner touches a coin.
        user.setScore(user.getScore() + 100);
        collide = true;
      }
    }
  }

  /** update the spike when it moves out of the screen or the runner touches it. */
  private void updateSpike(RunningGameActivity runningGameActivity, User user) {
    boolean collide = false;
    Iterator<Spike> spikeIterator = spikes.iterator();
    while (spikeIterator.hasNext() && !collide) {
      Spike spike = spikeIterator.next();
      Rect runnerRect = runner.getRect();
      Rect spikeRect = spike.getRect();
      if (spike.checkCollision(runnerRect, spikeRect)) {
        user.setLife(user.getLife() - 1);
        spikeIterator.remove();
      }
      if (user.getLife() == 0) {
        runningGameActivity.toMain();
        collide = true;
      }
    }
  }

  /** update the coins and spikes. */
  void update(RunningGameActivity runningGameActivity, User user) {
    updateTimer();
    randomGenerateItems();
    removeItems();
    updateCoin(user);
    updateSpike(runningGameActivity, user);
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

  /** remove coins and spikes. */
  private void removeItems() {
    removeCoins();
    removeSpikes();
  }

  /** remove coins that are not inside the screen */
  private void removeCoins() {
    for (int i = 0; i < coins.size(); i++) {
      if (coins.get(i).getXCoordinate() < -80) {
        coins.remove(i);
        i--;
      }
    }
  }

  /** remove spikes that are not inside the screen */
  private void removeSpikes() {
    for (int i = 0; i < spikes.size(); i++) {
      if (spikes.get(i).getXCoordinate() < -80) {
        spikes.remove(i);
        i--;
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
          generateSpikes();
        }
        break;
      case 1:
        if (timerSpike >= 125) {
          generateSpikes();
        }
        break;
      case 2:
        if (timerSpike >= 150) {
          generateSpikes();
        }
        break;
    }
  }

  private void generateSpikes() {
    Spike spike;
    spike =
        spikeFactory.createSpike(
            runningGameView,
            runningGameView.getSpikeBMP(),
            runningGameView.getWidth() + 24,
            groundHeight);
    spikes.add(spike);
    Random randomSpikes = new Random();
    timerRandomSpikes = randomSpikes.nextInt(3);
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
            makeCoin(i * 64, 130);
          }
          break;

        case 1:
          // construct three consecutive coins in different height.
          makeCoin(32, 150);
          makeCoin(96, 130);
          makeCoin(160, 150);
          break;
      }
      // reset the timer.
      timerCoins = 0;
    }
  }

  private void makeCoin(int xCoordinate, int yCoordinate) {
    Coin coin =
        coinFactory.createCoin(
            runningGameView,
            runningGameView.getCoinBMP(),
            runningGameView.getWidth() + xCoordinate,
            yCoordinate,
            groundHeight);
    coins.add(coin);
  }

  void draw(Canvas canvas) {
    runner.draw(canvas);
    for (Coin coin : coins) {
      coin.draw(canvas);
    }
    for (Spike spike : spikes) {
      spike.draw(canvas);
    }
    ground.draw(canvas);
  }
}

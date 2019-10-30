package com.example.survivalgame.runninggame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RunningGameManager {

  private com.example.survivalgame.runninggame.RunningGameView RunningGameView;

  // the runner.
  public Runner runner;

  // a list of coin.
  public List<Coin> coins = new ArrayList<>();

  // ground.
  public Ground ground;

  // a list of spikes.
  public List<Spike> spikes = new ArrayList<>();

  // the timer of the coin.
  private int timerCoins = 0;

  // the timer of the spike.
  private int timerSpike = 0;

  // random timer of the spike.
  private int timerRandomSpikes = 0;

  public RunningGameManager(RunningGameView view) {
    RunningGameView = view;

    // add runner and ground to the game.
    runner = new Runner(RunningGameView, RunningGameView.runnerBmp, 50, 50);
    ground = new Ground(RunningGameView, RunningGameView.groundBmp, 0, 0);
  }

  /** update the coins and spikes. */
  public void update() {
    updateTimer();
    for (int i = 0; i < coins.size(); i++) {
      if (coins.get(i).getX() < -80) {
        coins.remove(i);
        i--;
      }
    }
    for (int i = 0; i < spikes.size(); i++) {
      if (spikes.get(i).getX() < -80) {
        spikes.remove(i);
        i--;
      }
    }
  }

  /** update the timers to randomly generate the coins and spikes. */
  private void updateTimer() {
    timerCoins++;
    timerSpike++;

    // randomly generate spikes.
    randomGenerateSpikes();
    // randomly generate coins.
    randomGenerateCoins();
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
          spikes.add(
              new Spike(
                  RunningGameView, RunningGameView.spikesBmp, RunningGameView.getWidth() + 24));
          Random randomSpikes = new Random();
          timerRandomSpikes = randomSpikes.nextInt(3);
          timerSpike = 0;
        }
        break;

      case 1:
        if (timerSpike >= 125) {
          spikes.add(
              new Spike(
                  RunningGameView, RunningGameView.spikesBmp, RunningGameView.getWidth() + 24));
          Random randomSpikes = new Random();
          timerRandomSpikes = randomSpikes.nextInt(3);
          timerSpike = 0;
        }
        break;
      case 2:
        if (timerSpike >= 150) {
          spikes.add(
              new Spike(
                  RunningGameView, RunningGameView.spikesBmp, RunningGameView.getWidth() + 24));
          Random randomSpikes = new Random();
          timerRandomSpikes = randomSpikes.nextInt(3);
          timerSpike = 0;
        }
        break;
    }
  }

  /**
   * randomly generate the coins in the running game. citation:
   * https://www.youtube.com/watch?v=lmAmr8Efu34&t=492s
   */
  private void randomGenerateCoins() {
    if (timerCoins >= 100) {
      // randomly generate int 0 and 1 to decide which case the coins are generated.
      Random randomCoin = new Random();
      int random = randomCoin.nextInt(2);

      switch (random) {
        case 0:
          // construct five consecutive coins in same height.
          int currentCoin = 1;
          while (currentCoin <= 5) {
            coins.add(
                new Coin(
                    RunningGameView,
                    RunningGameView.coinBmp,
                    RunningGameView.getWidth() + (64 * currentCoin),
                    130));
            currentCoin++;
          }
          break;

        case 1:
          // construct three consecutive coins in different height.
          coins.add(
              new Coin(
                  RunningGameView, RunningGameView.coinBmp, RunningGameView.getWidth() + 32, 150));
          coins.add(
              new Coin(
                  RunningGameView, RunningGameView.coinBmp, RunningGameView.getWidth() + 96, 130));
          coins.add(
              new Coin(
                  RunningGameView, RunningGameView.coinBmp, RunningGameView.getWidth() + 160, 150));
          break;
      }
      // reset the timer.
      timerCoins = 0;
    }
  }
}

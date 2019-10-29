package com.example.survivalgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RunningGameManager {

  private RunningGameView RunningGameView;

  // the runner.
  Runner runner;

  // a list of coin.
  List<Coin> coin = new ArrayList<>();

  // ground.
  Ground ground;

  // a list of spikes.
  List<Spike> spikes = new ArrayList<>();

  // the timer of the coin.
  private int timerCoins = 0;

  // the timer of the spike.
  private int timerSpike = 0;

  // random timer of the spike.
  private int timerRandomSpikes = 0;

  RunningGameManager(RunningGameView view) {
    RunningGameView = view;

    // add runner and ground to the game.
    runner = new Runner(RunningGameView, RunningGameView.runnerBmp, 50, 50);
    ground = new Ground(RunningGameView, RunningGameView.groundBmp, 0, 0);
  }

  /** update the coins and spikes. */
  public void update() {
    updateTimer();
    for (int i = 0; i < coin.size(); i++) {
      if (coin.get(i).getX() < -80) {
        coin.remove(i);
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

  /** randomly generate the spikes in the running game. */
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

  /** randomly generate the coins in the running game. */
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
            coin.add(
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
          coin.add(
              new Coin(
                  RunningGameView, RunningGameView.coinBmp, RunningGameView.getWidth() + 32, 150));
          coin.add(
              new Coin(
                  RunningGameView, RunningGameView.coinBmp, RunningGameView.getWidth() + 96, 130));
          coin.add(
              new Coin(
                  RunningGameView, RunningGameView.coinBmp, RunningGameView.getWidth() + 160, 150));
          break;
      }
      // reset the timer.
      timerCoins = 0;
    }
  }
}

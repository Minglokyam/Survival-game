package com.example.survivalgame;

import java.time.Duration;
import java.io.*;

public class User implements Serializable {
  private String username;
  private String password;

  private int id;
  private int score;
  private int life;
  private Duration totalDuration;
  private int gameStage;

  public static final int RUNNING = 0;
  public static final int PONG = 1;
  public static final int DODGE = 2;

  User(String username, String password, int id) {
    this.username = username;
    this.password = password;
    score = 0;
    life = 3;
    gameStage = RUNNING;
    totalDuration = Duration.ofSeconds(0);
    this.id = id;
  }

  String getUsername() {
    return username;
  }

  String getPassword() {
    return password;
  }

  public int getID() {
    return id;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int newScore) {
    score = newScore;
  }

  public int getLife() {
    return life;
  }

  public void setLife(int newLife) {
    life = newLife;
  }

  public int getGameStage() {
    return gameStage;
  }

  public void setGameStage(int newGameStage) {
    gameStage = newGameStage;
  }

  public Duration getTotalDuration() {
    return totalDuration;
  }

  public void setTotalDuration(Duration newTotalDuration) {
    totalDuration = newTotalDuration;
  }

  @Override
  public String toString() {
    return username + gameStage;
  }
}

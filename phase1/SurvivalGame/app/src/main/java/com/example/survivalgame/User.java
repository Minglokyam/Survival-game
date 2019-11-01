package com.example.survivalgame;

import java.time.Duration;
import java.io.*;

public class User implements Serializable {

  /* user's username */
  private String username;

  /* user's password */
  private String password;

  /* user's id */
  private int id;

  /* user's current score */
  private int score;

  /* user's current life count */
  private int life;

  /* the duration this user has played this game */
  private Duration totalDuration;

  /* the game the user is playing */
  private int gameStage;

  /* First game: Running game */
  public static final int RUNNING = 0;
  /* Second game: Pong game */
  public static final int PONG = 1;
  /* Last game: Dodge game */
  public static final int DODGE = 2;

  /**
   * instantiate the user. Set initial score to 0 and life count to 3
   *
   * @param username user's username
   * @param password user's password
   * @param id user's id
   */
  User(String username, String password, int id) {
    this.username = username;
    this.password = password;
    score = 0;
    life = 3;
    gameStage = RUNNING;
    totalDuration = Duration.ofSeconds(0);
    this.id = id;
  }

  /** get the user's username */
  String getUsername() {
    return username;
  }

  /** get the user's password */
  String getPassword() {
    return password;
  }

  /** get the user's id */
  public int getID() {
    return id;
  }

  /** get the user's current score */
  public int getScore() {
    return score;
  }

  /**
   * update the user's score
   *
   * @param newScore the user's new score
   */
  public void setScore(int newScore) {
    score = newScore;
  }

  /** get the user's current life count */
  public int getLife() {
    return life;
  }

  /**
   * update the user's life count
   *
   * @param newLife user's new life count
   */
  public void setLife(int newLife) {
    life = newLife;
  }

  /** get the game the user's currently playing */
  public int getGameStage() {
    return gameStage;
  }

  /**
   * update the game the user's currently playing
   *
   * @param newGameStage the game the player is playing
   */
  public void setGameStage(int newGameStage) {
    gameStage = newGameStage;
  }

  /** get the duration of user's playing time */
  public Duration getTotalDuration() {
    return totalDuration;
  }

  /**
   * update the duration of user's playing time
   *
   * @param newTotalDuration the new duration to be set
   */
  public void setTotalDuration(Duration newTotalDuration) {
    totalDuration = newTotalDuration;
  }

  /** prints the username and the game the user's playing */
  @Override
  public String toString() {
    return username + gameStage;
  }
}

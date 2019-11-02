package com.example.survivalgame;

import android.content.Context;

import java.time.Duration;
import java.util.List;

public class UserUpdater {

  /* the main activity of the game */
  private static MainActivity mainActivity;

  /* the current list of users*/
  private static List<User> userList;

  /* the user that's playing*/
  private static User user;

  /** set the main activity */
  public static void setMainActivity(Context context) {
    mainActivity = (MainActivity) context;
  }

  /** update the user list */
  public static void setUserList() {
    userList = mainActivity.getUserManager().getUserList();
  }

  /**
   * Update user's information
   *
   * @param activityUser the user playing the game
   * @param gameStage the game the user's playing
   */
  public static void updateUser(User activityUser, int gameStage) {
    setUser(activityUser);
    user.setLife(activityUser.getLife());
    user.setGameStage(gameStage);
    user.setScore(activityUser.getScore());
    user.setTotalDuration(activityUser.getTotalDuration());
  }

  /**
   * reset the user's information to default.
   *
   * @param activityUser the user playing the game
   */
  public static void resetUser(User activityUser) {
    setUser(activityUser);
    user.setLife(3);
    user.setGameStage(User.RUNNING);
    user.setScore(0);
    user.setTotalDuration(Duration.ofSeconds(0));
  }

  /**
   * set the user that's playing the game
   *
   * @param newUser The user that we are going to set.
   */
  private static void setUser(User newUser) {
    user = userList.get(newUser.getID());
  }
}

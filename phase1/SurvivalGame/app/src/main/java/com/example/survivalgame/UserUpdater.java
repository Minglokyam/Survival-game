package com.example.survivalgame;

import android.content.Context;

import java.time.Duration;
import java.util.List;

public class UserUpdater {
  private static MainActivity mainActivity;
  private static List<User> userList;
  private static User user;

  public static void setMainActivity(Context context) {
    mainActivity = (MainActivity) context;
  }

  public static void setUserList() {
    userList = mainActivity.userManager.getUserList();
  }

  public static void updateUser(User activityUser, int gameStage) {
    setUser(activityUser);
    user.setLife(activityUser.getLife());
    user.setGameStage(gameStage);
    user.setScore(activityUser.getScore());
    user.setTotalDuration(activityUser.getTotalDuration());
  }

  public static void resetUser(User activityUser){
    setUser(activityUser);
    user.setLife(3);
    user.setGameStage(User.RUNNING);
    user.setScore(0);
    user.setTotalDuration(Duration.ofSeconds(0));
  }

  private static void setUser(User newUser) {
    user = userList.get(newUser.getID());
  }
}

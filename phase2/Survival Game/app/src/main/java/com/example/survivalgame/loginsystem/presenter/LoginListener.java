package com.example.survivalgame.loginsystem.presenter;

import com.example.survivalgame.general.User;

public interface LoginListener {
  void onRegisterSuccess();

  void onUserNotExists();

  void onUserAlreadyExists();

  void onCredentialEmpty();

  void launchRunningGame(String name, User user);

  void launchPongGame(String name, User user);

  void launchDodgeGame(String name, User user);

  void onWrongCredential();

  void onPWNotValid();

  void saveFile();

  void loadFile();
}

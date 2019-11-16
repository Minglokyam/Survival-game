package com.example.survivalgame;

public interface LoginListener {
  void onRegisterSuccess();

  void onUserNotExists();

  void onUserAlreadyExists();

  void onCredentialEmpty();

  void launchRunningGame(String name, User user);

  void launchPongGame(String name, User user);

  void launchDodgeGame(String name, User user);

  void onWrongCredential();
}

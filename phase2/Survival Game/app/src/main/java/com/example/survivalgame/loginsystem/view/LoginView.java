package com.example.survivalgame.loginsystem.view;

import com.example.survivalgame.general.User;

public interface LoginView {
  void launchRunningGame(String name, User user);

  void launchPongGame(String name, User user);

  void launchDodgeGame(String name, User user);

  void setEmptyInputError();

  void setUserNotExistError();

  void setUserAlreadyExistError();

  void setCredentialsError();

  void setRegisterSuccess();

  void onPWNotValid();

  void saveFile();

  void loadFile();
}

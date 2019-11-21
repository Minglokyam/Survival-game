package com.example.survivalgame.loginsystem.presenter;

import com.example.survivalgame.loginsystem.view.LoginView;
import com.example.survivalgame.general.User;
import com.example.survivalgame.loginsystem.model.LoginInteractor;

public class LoginPresenter implements LoginListener {
  private LoginView loginView;
  private LoginInteractor loginInteractor;

  public LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
    this.loginView = loginView;
    this.loginInteractor = loginInteractor;
  }

  @Override
  public void onRegisterSuccess() {
    if (loginView != null) {
      loginView.setRegisterSuccess();
    }
  }

  @Override
  public void onUserNotExists() {
    if (loginView != null) {
      loginView.setUserNotExistError();
    }
  }

  @Override
  public void onUserAlreadyExists() {
    if (loginView != null) {
      loginView.setUserAlreadyExistError();
    }
  }

  @Override
  public void onCredentialEmpty() {
    if (loginView != null) {
      loginView.setEmptyInputError();
    }
  }

  @Override
  public void onWrongCredential() {
    if (loginView != null) {
      loginView.setCredentialsError();
    }
  }

  @Override
  public void launchRunningGame(String name, User user) {
    loginView.launchRunningGame(name, user);
  }

  @Override
  public void launchPongGame(String name, User user) {
    loginView.launchPongGame(name, user);
  }

  @Override
  public void launchDodgeGame(String name, User user) {
    loginView.launchDodgeGame(name, user);
  }

  public void validateRegisterCredentials(String username, String password) {
    loginInteractor.register(username, password, this);
  }

  public void validateLoginCredentials(String username, String password) {
    loginInteractor.login(username, password, this);
  }

  @Override
  public void saveFile() {
    loginView.saveFile();
  }

  @Override
  public void loadFile() {
    loginView.loadFile();
  }

  @Override
  public void onPWNotValid() {
    loginView.onPWNotValid();
  }
}

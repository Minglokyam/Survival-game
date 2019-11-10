package com.example.survivalgame;

public class LoginInteractor {

  public void register(
      String username, String password, LoginPresenterInterface loginPresenterInterface) {
      if (!(username.trim().equals("") || password.trim().equals(""))) {
          if (!UserManager.userExists(username)) {
              User newUser = new User(username, password);
              UserManager.addUser(username, newUser);
              IOManager.saveFile();
              loginPresenterInterface.onRegisterSuccess();
          } else {
              loginPresenterInterface.onUserExists();
          }
      } else {
        loginPresenterInterface.onCredentialEmpty();
      }
  }
    }

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
        loginPresenterInterface.onUserNotExists();
      }
    } else {
      loginPresenterInterface.onCredentialEmpty();
    }
  }

  public void login(
      String username, String password, LoginPresenterInterface loginPresenterInterface) {
      if (!(username.trim().equals("") || password.trim().equals(""))) {
          System.out.println(UserManager.userExists(username));
          if (UserManager.userExists(username)) {
              User temp = UserManager.getUser(username);
              if (temp.getUsername().equals(username) && temp.getPassword().equals(password)) {
                  loginPresenterInterface.onLoginSuccess();
              } else {
                  loginPresenterInterface.onWrongCredential();
              }
          } else {
             loginPresenterInterface.onUserNotExists();
          }
      } else {
          loginPresenterInterface.onCredentialEmpty();
      }
  }
}

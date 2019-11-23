package com.example.survivalgame.loginsystem.model;

import com.example.survivalgame.general.UserManagerSingleton;
import com.example.survivalgame.loginsystem.presenter.LoginListener;
import com.example.survivalgame.general.User;

import org.mindrot.jbcrypt.BCrypt;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginInteractor {
  private static final String REGEX = "^(?=.*[A-Z])[\\w@$!%*?&]{6,}$";

  public void register(String username, String password, LoginListener loginListener) {
    loginListener.loadFile();
    UserManagerSingleton userManagerSingleton = UserManagerSingleton.getInstance();
    if (checkNotEmptyCredential(username, password)) {
      if (!userManagerSingleton.userExists(username)) {
        if (pwRequirement(password)) {
          String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
          User newUser = new User(username, hashed);
          userManagerSingleton.addUser(username, newUser);
          loginListener.saveFile();
          loginListener.onRegisterSuccess();
        } else {
          loginListener.onPWNotValid();
        }
      } else {
        loginListener.onUserAlreadyExists();
      }
    } else {
      loginListener.onCredentialEmpty();
    }
  }

  public void login(String username, String password, LoginListener loginListener) {
    loginListener.loadFile();
    UserManagerSingleton userManagerSingleton = UserManagerSingleton.getInstance();
    if (checkNotEmptyCredential(username, password)) {
      System.out.println(userManagerSingleton.userExists(username));
      if (userManagerSingleton.userExists(username)) {
        User temp = userManagerSingleton.getUser(username);
        if (checkPassword(password, temp.getPassword())) {
          onLoginSuccess(username, temp, loginListener);
        } else {
          loginListener.onWrongCredential();
        }
      } else {
        loginListener.onUserNotExists();
      }
    } else {
      loginListener.onCredentialEmpty();
    }
  }

  private void onLoginSuccess(String name, User user, LoginListener loginListener) {
    int gameStage = user.getGameStage();
    if (gameStage == User.RUNNING) {
      loginListener.launchRunningGame(name, user);
    } else if (gameStage == User.PONG) {
      loginListener.launchPongGame(name, user);
    } else if (gameStage == User.DODGE) {
      loginListener.launchDodgeGame(name, user);
    }
  }

  private boolean checkPassword(String password, String hashed) {
    return BCrypt.checkpw(password, hashed);
  }

  private boolean checkNotEmptyCredential(String username, String password) {
    return !username.trim().equals("") || password.trim().equals("");
  }

  private boolean pwRequirement(String password) {
    Pattern pattern = Pattern.compile(REGEX);
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }
}

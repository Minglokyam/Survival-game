package com.example.survivalgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class UserManager implements Serializable {

  private static List<User> userList;

  UserManager() {
    userList = new ArrayList<>();
  }

  /* username and password cannot be empty*/

  public int numUsers() {
    return userList.size();
  }

  public void addUser(User user) {
    userList.add(user);
  }

  public boolean userExists(String username) {
    for (User user : userList) {
      if (user.getUsername().equals(username)) return true;
    }
    return false;
  }

  public User getUser(String username) {
    for (User user : userList) {
      if (user.getUsername().equals(username)) return user;
    }
    return null;
  }

  public List<User> getUserList() {
    return userList;
  }

  public void setUserList(List<User> userList) {
    UserManager.userList = userList;
  }
}

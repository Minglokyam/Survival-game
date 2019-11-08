package com.example.survivalgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class UserManager implements Serializable {

  /* a list to store the user objects */
  private static List<User> userList;

  /* initialize userList */
  UserManager() {
    userList = new ArrayList<>();
  }

  /**
   * get the current number of users registered.
   *
   * @return the number of users
   */
  public int numUsers() {
    return userList.size();
  }

  /**
   * add a user to the list
   *
   * @param user the user object to be added to the list
   */
  public void addUser(User user) {
    userList.add(user);
  }

  /**
   * check if the user with the given username exists in the list.
   *
   * @param username username of user to be checked
   * @return whether it exists or not
   */
  public boolean userExists(String username) {
    for (User user : userList) {
      if (user.getUsername().equals(username)) return true;
    }
    return false;
  }

  /**
   * return the user object by username
   *
   * @param username the user's username
   * @return the user object
   */
  public User getUser(String username) {
    for (User user : userList) {
      if (user.getUsername().equals(username)) return user;
    }
    return null;
  }

  /**
   * return the list of user objects
   *
   * @return current list of users
   */
  public List<User> getUserList() {
    return userList;
  }

  /**
   * update the userList
   *
   * @param userList current list of users
   */
  public void setUserList(List<User> userList) {
    UserManager.userList = userList;
  }
}

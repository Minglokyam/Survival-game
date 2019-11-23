package com.example.survivalgame.general;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserManagerSingleton implements Serializable {
  private static UserManagerSingleton instance = new UserManagerSingleton();

  /* a list to store the user objects */
  private Map<String, User> userMap;

  private UserManagerSingleton() {
    userMap = new HashMap<>();
  }

  public static UserManagerSingleton getInstance() {
    return instance;
  }

  /**
   * add a user to the list
   *
   * @param user the user object to be added to the list
   */
  public void addUser(String username, User user) {
    userMap.put(username, user);
  }

  /**
   * check if the user with the given username exists in the list.
   *
   * @param username username of user to be checked
   * @return whether it exists or not
   */
  public boolean userExists(String username) {
    return userMap.containsKey(username);
  }

  /**
   * return the user object by username
   *
   * @param username the user's username
   * @return the user object
   */
  public User getUser(String username) {
    return userMap.get(username);
  }

  public Map<String, User> getUserMap() {
    return userMap;
  }

  void setUserMap(Map<String, User> newUserMap) {
    userMap = newUserMap;
  }
}

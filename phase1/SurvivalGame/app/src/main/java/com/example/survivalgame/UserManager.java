package com.example.survivalgame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserManager implements Serializable {
    List<User> userList;

    UserManager(){userList = new ArrayList<>();}

    /* username and password cannot be empty*/

    public int numUsers() {
        return userList.size();
    }

    public void addUser(User user) {
        userList.add(user);
    }

    public boolean userExists(String username) {
        try{
        for (User user : userList) {
            if (user.getUsername().equals(username)) return true;
        }
    } catch (Exception e) {
      return false;
        }
        return false;
    }

    public User getUser(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }
    public void update(UserManager newManager) {
        this.userList = newManager.userList;
    }
}
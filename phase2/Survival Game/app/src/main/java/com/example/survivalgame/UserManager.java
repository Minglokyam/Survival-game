package com.example.survivalgame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserManager implements Serializable {

    /* a list to store the user objects */
    private static Map<String, User> userMap = new HashMap<>();

    /**
     * add a user to the list
     *
     * @param user the user object to be added to the list
     */
    public static void addUser(String username, User user) {
        userMap.put(username, user);
    }

    /**
     * check if the user with the given username exists in the list.
     *
     * @param username username of user to be checked
     * @return whether it exists or not
     */
    public static boolean userExists(String username) {
        return userMap.containsKey(username);
    }

    /**
     * return the user object by username
     *
     * @param username the user's username
     * @return the user object
     */
    public static User getUser(String username) {
        return userMap.get(username);
    }

    public static Map getUserMap() {
        return userMap;
    }

    public static void setUserMap(Map<String, User> newUserMap) {
        userMap = newUserMap;
    }
}

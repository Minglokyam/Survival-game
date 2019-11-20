package com.example.survivalgame.scoreboard.model;

import com.example.survivalgame.User;
import com.example.survivalgame.UserManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RankingInteractor {
  public User[] toUserArray() {
    List listUser = new ArrayList<>();
    Iterator iterator = UserManager.getUserMap().keySet().iterator();
    while (iterator.hasNext()) {
      String key = iterator.next().toString();
      listUser.add(UserManager.getUserMap().get(key));
    }
    Collections.sort(listUser);
    User[] UserArray = (User[]) listUser.toArray(new User[listUser.size()]);
    // Arrays.sort(UserArray);
    return UserArray;
  }
}

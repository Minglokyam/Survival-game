package com.example.survivalgame.scoreboard.model;

import com.example.survivalgame.general.User;
import com.example.survivalgame.general.UserManagerSingleton;
import com.example.survivalgame.scoreboard.presenter.RankingPresenterInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RankingInteractor {

  public void generateRanking(RankingPresenterInterface presenter) {
    List<User> userList = new ArrayList<>();
    User user;
    UserManagerSingleton userManagerSingleton = UserManagerSingleton.getInstance();
    Iterator iterator = userManagerSingleton.getUserMap().keySet().iterator();
    while (iterator.hasNext()) {
      String key = iterator.next().toString();
      user = userManagerSingleton.getUserMap().get(key);
      if (user.isRegistered()) {
        userList.add(user);
      }
    }
    Collections.sort(userList);
    int size = userList.size();
    if (size > 3) {
      size = 3;
    }
    presenter.printRankingText(userList, size);
  }
}

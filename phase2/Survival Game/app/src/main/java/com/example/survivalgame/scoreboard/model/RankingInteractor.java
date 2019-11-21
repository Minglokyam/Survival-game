package com.example.survivalgame.scoreboard.model;

import com.example.survivalgame.User;
import com.example.survivalgame.UserManager;
import com.example.survivalgame.scoreboard.presenter.RankingPresenterInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RankingInteractor {
  public void generateRanking(RankingPresenterInterface presenter) {
    List<User> userList = new ArrayList<>();
    Iterator iterator = UserManager.getUserMap().keySet().iterator();
    while (iterator.hasNext()) {
      String key = iterator.next().toString();
      userList.add(UserManager.getUserMap().get(key));
    }
    Collections.sort(userList);
    int size = userList.size();
    if(size > 3){
      size = 3;
    }
    presenter.printRankingText(userList, size);
  }
}

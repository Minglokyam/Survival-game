package com.example.survivalgame.scoreboard.presenter;

import com.example.survivalgame.general.User;

import java.util.List;

public interface RankingPresenterInterface {
  void printRankingText(List<User> userList, int size);
}

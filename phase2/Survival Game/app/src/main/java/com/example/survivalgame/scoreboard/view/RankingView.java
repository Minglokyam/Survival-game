package com.example.survivalgame.scoreboard.view;

import java.util.List;

public interface RankingView {
  void printRankingText(List<String> userStatementList, int size);

  void setUserText(int userText);
}

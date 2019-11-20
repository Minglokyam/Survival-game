package com.example.survivalgame.scoreboard.view;

import java.util.List;

public interface RankingView {
  void printRankingText(List<String> userStatementList);

  void setUserText(int userText);
}

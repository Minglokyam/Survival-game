package com.example.survivalgame.scoreboard.presenter;

import com.example.survivalgame.User;
import com.example.survivalgame.scoreboard.model.RankingInteractor;
import com.example.survivalgame.scoreboard.view.RankingView;

import java.util.ArrayList;
import java.util.List;

public class RankingPresenter implements RankingPresenterInterface {
  private RankingView rankingView;

  public RankingPresenter(RankingView rankingView, User user, RankingInteractor rankingInteractor) {
    this.rankingView = rankingView;
    updateUserScore(user);
    rankingInteractor.generateRanking(this);
    rankingView.setUserText(user.getScore());
  }

  private void updateUserScore(User user) {
    user.updateHighestScore(user.getScore());
  }

  @Override
  public void printRankingText(List<User> userList) {
    List<String> userStatementList = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      userStatementList.add(userList.get(i).toString());
    }
    rankingView.printRankingText(userStatementList);
  }
}

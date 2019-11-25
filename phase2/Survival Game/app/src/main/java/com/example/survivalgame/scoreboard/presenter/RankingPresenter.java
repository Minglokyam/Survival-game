package com.example.survivalgame.scoreboard.presenter;

import com.example.survivalgame.general.User;
import com.example.survivalgame.scoreboard.model.RankingInteractor;
import com.example.survivalgame.scoreboard.view.RankingView;

import java.util.ArrayList;
import java.util.List;

public class RankingPresenter implements RankingPresenterInterface {
  private RankingInteractor rankingInteractor;
  private RankingView rankingView;

  public RankingPresenter(RankingView rankingView, User user, RankingInteractor rankingInteractor) {
    this.rankingView = rankingView;
    this.rankingInteractor = rankingInteractor;
    generateRanking(user);
  }

  public void update(String nickname, User user) {
    user.setNickname(nickname);
    generateRanking(user);
  }

  private void generateRanking(User user) {
    rankingInteractor.generateRanking(this);
    rankingView.setUserText(user.getScore());
  }

  @Override
  public void printRankingText(List<User> userList, int size) {
    List<String> userStatementList = new ArrayList<>();
    String userStatement;
    for (int i = 0; i < size; i++) {
      userStatement = userList.get(i).toString();
      userStatementList.add(userStatement);
    }
    rankingView.printRankingText(userStatementList, size);
  }
}

package com.example.survivalgame.scoreboard.presenter;

import com.example.survivalgame.User;
import com.example.survivalgame.scoreboard.model.RankingInteractor;

public class RankingPresenter {
  // private RankingActivity rankView;
  private RankingInteractor rankingInteractor;

  public RankingPresenter(User user, RankingInteractor rankingInteractor) {
    this.rankingInteractor = rankingInteractor;
    updateUserScore(user);
  }

  public User[] toUserArray() {
    return rankingInteractor.toUserArray();
  }

  private void updateUserScore(User user) {
    user.updateHighestScore(user.getScore());
  }
}

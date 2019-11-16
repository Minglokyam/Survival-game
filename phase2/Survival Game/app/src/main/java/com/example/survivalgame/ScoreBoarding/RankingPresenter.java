package com.example.survivalgame.ScoreBoarding;

import com.example.survivalgame.ScoreBoarding.RankingActivity;
import com.example.survivalgame.User;

public class RankingPresenter {
  private RankingActivity rankView;
  private RankingInteractor ri;

  public RankingPresenter(RankingInteractor ri) {
    this.ri = ri;
  }

  public User[] toUserArray() {
    return ri.toUserArray();
  }
}

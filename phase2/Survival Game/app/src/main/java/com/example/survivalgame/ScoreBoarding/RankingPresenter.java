package com.example.survivalgame.ScoreBoarding;

import android.widget.TextView;

import com.example.survivalgame.ScoreBoarding.RankingActivity;
import com.example.survivalgame.User;

public class RankingPresenter {
  //private RankingActivity rankView;
  private RankingInteractor ri;

  public RankingPresenter(RankingInteractor ri) {
    this.ri = ri;
  }

  public User[] toUserArray() {
    return ri.toUserArray();
  }

  public int checknumofUser(User[] users){
      return users.length;

  }
  public void setTexts(TextView[] textViews,User[] users){
      ri.setTexts(textViews,users);

  }

  public void setOneText(TextView t,User u){
      ri.setOneText(t,u);
  }
}

package com.example.survivalgame.ScoreBoarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.survivalgame.R;

import com.example.survivalgame.User;
import com.example.survivalgame.UserManager;

import java.util.*;

public class RankingActivity extends AppCompatActivity {
  RankingPresenter rankingPresenter;
  private User user;
  private String name;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    name = intent.getStringExtra("user");
    user = UserManager.getUser(name);

    setContentView(R.layout.activity_ranking);
    rankingPresenter = new RankingPresenter(new RankingInteractor());

    TextView num1 = (TextView) findViewById(R.id.num1);
    TextView num2 = (TextView) findViewById(R.id.num2);
    TextView num3 = (TextView) findViewById(R.id.num3);
    TextView yourScore = (TextView) findViewById(R.id.yourScore);

    TextView[] textViews ={num1,num2,num3};
    User[] users = rankingPresenter.toUserArray();
    rankingPresenter.setOneText(yourScore,user);
    rankingPresenter.setTexts(textViews,users);

  }
}

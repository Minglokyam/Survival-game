package com.example.survivalgame.scoreboard.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.survivalgame.IOManager;
import com.example.survivalgame.MainActivity;
import com.example.survivalgame.R;

import com.example.survivalgame.User;
import com.example.survivalgame.UserManager;
import com.example.survivalgame.replay.view.PongGameReplayActivity;
import com.example.survivalgame.scoreboard.model.RankingInteractor;
import com.example.survivalgame.scoreboard.presenter.RankingPresenter;

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
    rankingPresenter = new RankingPresenter(user, new RankingInteractor());

    TextView num1 = findViewById(R.id.num1);
    TextView num2 = findViewById(R.id.num2);
    TextView num3 = findViewById(R.id.num3);
    TextView yourScore = findViewById(R.id.yourScore);

    Button replayButton = findViewById(R.id.replay);
    Button toMainButton = findViewById(R.id.toMain);
    replayButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            toReplay();
          }
        });
    toMainButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            toMain();
          }
        });

    TextView[] textViews = {num1, num2, num3};
    User[] users = rankingPresenter.toUserArray();
    setOneText(yourScore, user);
    setTexts(textViews, users);
  }

  public void toReplay() {
    Intent intent = new Intent(this, PongGameReplayActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }

  public void toMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    user.reset();
    IOManager.saveFile(this);
    startActivity(intent);
    finish();
  }

  public void setTexts(TextView[] textViews, User[] users) {
    for (int i = 0; i < textViews.length; i++) {
      if (i < users.length) {
        textViews[i].setText(users[i].toString());
      }
    }
  }

  public void setOneText(TextView textView, User user) {
    textView.setText("You received " + user.getScore() + " scores");
  }
}

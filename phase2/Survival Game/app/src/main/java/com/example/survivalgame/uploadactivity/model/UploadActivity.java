package com.example.survivalgame.uploadactivity.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.survivalgame.R;
import com.example.survivalgame.general.User;
import com.example.survivalgame.general.UserManagerSingleton;
import com.example.survivalgame.loginsystem.view.MainActivity;
import com.example.survivalgame.scoreboard.view.RankingActivity;
import com.example.survivalgame.uploadactivity.presenter.UploadPresenter;

public class UploadActivity extends AppCompatActivity {
  private String name;
  private User user;
  private UserManagerSingleton userManagerSingleton;
  private UploadPresenter uploadPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    name = intent.getStringExtra("user");
    userManagerSingleton = UserManagerSingleton.getInstance();
    user = userManagerSingleton.getUser(name);
    uploadPresenter = new UploadPresenter();

    setContentView(R.layout.activity_upload);

    Button toMain = findViewById(R.id.toMain);
    Button toScoreBoard = findViewById(R.id.toSc);

    toMain.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            toMain();
          }
        });

    toScoreBoard.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            uploadPresenter.updateUser(user);
            toScoreBoard();
          }
        });
  }

  public void toMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.putExtra("user", name);
    user.clearReplay();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }

  private void toScoreBoard() {
    Intent intent = new Intent(this, RankingActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }
}

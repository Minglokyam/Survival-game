package com.example.survivalgame.beforereplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.survivalgame.R;
import com.example.survivalgame.general.User;
import com.example.survivalgame.general.UserManager;
import com.example.survivalgame.dodgegame.view.DodgeGameActivity;
import com.example.survivalgame.replay.view.PongGameReplayActivity;

public class BeforeReplayActivity extends AppCompatActivity {
  private String name;
  private User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    name = intent.getStringExtra("user");
    user = UserManager.getUser(name);

    setContentView(R.layout.activity_before_replay);

    Button nextButton = findViewById(R.id.nextbutton);
    Button replayButton = findViewById(R.id.replaybutton);

    nextButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            toDodge();
          }
        });

    replayButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            toReplay();
          }
        });
  }

  public void toDodge() {
    Intent intent = new Intent(this, DodgeGameActivity.class);
    intent.putExtra("user", name);
    user.clearReplay();
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }

  private void toReplay() {
    Intent intent = new Intent(this, PongGameReplayActivity.class);
    intent.putExtra("user", name);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
    finish();
  }
}

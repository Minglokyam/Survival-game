package com.example.survivalgame.beforereplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.survivalgame.R;
import com.example.survivalgame.beforedodge.BeforeDodgeActivity;
import com.example.survivalgame.general.User;
import com.example.survivalgame.general.UserManagerSingleton;
import com.example.survivalgame.replay.view.PongGameReplayActivity;

public class BeforeReplayActivity extends AppCompatActivity {
  private String name;
  private User user;
  private UserManagerSingleton userManagerSingleton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    name = intent.getStringExtra("user");
    userManagerSingleton = UserManagerSingleton.getInstance();
    user = userManagerSingleton.getUser(name);

    setContentView(R.layout.activity_before_replay);

    Button nextButton = findViewById(R.id.nextbutton);
    Button replayButton = findViewById(R.id.replaybutton);

    nextButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            toBeforeDodge();
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

  public void toBeforeDodge() {
    Intent intent = new Intent(this, BeforeDodgeActivity.class);
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

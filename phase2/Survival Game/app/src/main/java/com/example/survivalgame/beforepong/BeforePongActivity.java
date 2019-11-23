package com.example.survivalgame.beforepong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.survivalgame.R;
import com.example.survivalgame.general.User;
import com.example.survivalgame.general.UserManagerSingleton;
import com.example.survivalgame.ponggame.view.PongGameActivity;
import com.example.survivalgame.runninggame.view.RunningGameActivity;

public class BeforePongActivity extends AppCompatActivity {
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

        setContentView(R.layout.activity_before_pong);

        Button nextButton = findViewById(R.id.topongbutton);

        nextButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toPong();
                    }
                });

    }

    public void toPong() {
        Intent intent = new Intent(this, PongGameActivity.class);
        intent.putExtra("user", name);
        user.clearReplay();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}

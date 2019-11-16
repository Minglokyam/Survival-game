package com.example.survivalgame.ScoreBoarding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.survivalgame.R;

import com.example.survivalgame.User;
import com.example.survivalgame.UserManager;

import java.util.*;

public class RankingActivity extends AppCompatActivity {
    RankingPresenter rankingPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        rankingPresenter = new RankingPresenter(new RankingInteractor());

        TextView num1 =(TextView)findViewById(R.id.num1);
        TextView num2 =(TextView)findViewById(R.id.num2);
        TextView num3 =(TextView)findViewById(R.id.num3);

        User[] users = rankingPresenter.toUserArray();
        num1.setText(users[0].toString());
        num2.setText(users[1].toString());
        num2.setText(users[2].toString());






    }



}

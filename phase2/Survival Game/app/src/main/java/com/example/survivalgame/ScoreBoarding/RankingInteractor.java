package com.example.survivalgame.ScoreBoarding;

import android.widget.TextView;

import com.example.survivalgame.User;
import com.example.survivalgame.UserManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RankingInteractor {
  public User[] toUserArray() {
    List listUser = new ArrayList<>();
    Iterator it = UserManager.getUserMap().keySet().iterator();
    while (it.hasNext()) {
      String key = it.next().toString();
      listUser.add(UserManager.getUserMap().get(key));
    }
    Collections.sort(listUser);
    User[] UserArray = (User[]) listUser.toArray(new User[listUser.size()]);
    // Arrays.sort(UserArray);
    return UserArray;
  }

  public void setOneText(TextView t, User user) {
    t.setText("You received " + user.getScore() + " scores");
  }

  public void setTexts(TextView[] textviews, User[] users) {
    for (int i = 0; i < textviews.length; i++) {
      if (i < users.length) {
        textviews[i].setText(users[i].toString());
      }
    }
  }
}

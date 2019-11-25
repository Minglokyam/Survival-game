package com.example.survivalgame.uploadactivity.presenter;

import com.example.survivalgame.general.User;

public class UploadPresenter {
  public void updateUser(User user) {
    if (!user.isRegistered()) {
      user.setRegistered();
    }
    user.updateHighestScore(user.getScore());
  }
}

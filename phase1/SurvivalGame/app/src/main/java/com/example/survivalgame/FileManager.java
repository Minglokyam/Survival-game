package com.example.survivalgame;

import android.content.Context;

public class FileManager {
    private static MainActivity mainActivity;
    private static final String USER_FILE = "user_file.ser";

    public static void setMainActivity(Context context){
        mainActivity = (MainActivity)context;
    }

    public static void saveFile(){
        mainActivity.saveFile(USER_FILE);
    }

    public static void setGameStage(User user, int gameStage){
        mainActivity.userManager.userList.get(user.getID()).setGameStage(gameStage);
    }
}

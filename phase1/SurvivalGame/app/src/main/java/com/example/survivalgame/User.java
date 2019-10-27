package com.example.survivalgame;

import java.io.Serializable;
import java.time.Duration;

class User implements Serializable {

    private String username;
    private String password;

    private static int score;
    private static int life;
    private static Duration totalDuration;
    private static int gameStage;

    private final int RUNNING = 0;
    private final int DODGE = 1;
    private final int PONG = 2;

    User(String username, String password){
        this.username = username;
        this.password = password;
        score = 0;
        life = 3;
        gameStage = 0;
        totalDuration = Duration.ofSeconds(0);
    }

    String getUsername(){return username;}

    String getPassword(){return password;}

    public static int getScore(){return score;}

    public static void setScore(int newScore){score = newScore;}

    public static int getLife(){return life;}

    public static void setLife(int newLife){life = newLife;}

    public static int getGameStage(){return gameStage;}

    public static void setGameStage(int newGameStage){gameStage = newGameStage;}

    public static Duration getTotalDuration(){return totalDuration;}

    public static void setTotalDuration(Duration newTotalDuration){totalDuration = newTotalDuration;}

}


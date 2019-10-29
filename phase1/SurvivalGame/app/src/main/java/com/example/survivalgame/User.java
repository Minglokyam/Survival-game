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

    public static final int RUNNING = 0;
    public static final int PONG = 1;
    public static final int DODGE = 2;


    User(String username, String password){
        this.username = username;
        this.password = password;
        score = 0;
        life = 3;
        gameStage = RUNNING;
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

    public boolean compare(User user){
        if((this.username).equals(user.getUsername())&&(this.password).equals(user.getPassword())){
            return true;
        }
        return false;
    }
}


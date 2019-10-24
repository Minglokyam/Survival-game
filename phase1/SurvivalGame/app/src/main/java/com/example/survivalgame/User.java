package com.example.survivalgame;

import java.sql.Time;

public class User {
    private static String name;
    private static int id;
    private static int score;
    private static int life = 3;
    private static Time time;
    private static int gameStage;
    private final int JUMP = 0;
    private final int DODGE = 1;
    private final int PONG = 2;
    public static int getScore(){
        return score;
    }
    public static void setScore(int newScore){
        score = newScore;
    }
    public static int getLife(){
        return life;
    }
    public static void setLife(int newLife){
        life = newLife;
    }
}

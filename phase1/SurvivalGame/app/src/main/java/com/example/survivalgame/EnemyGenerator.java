package com.example.survivalgame;

public class EnemyGenerator {
    DodgeGameManager dodgeGameManager;
    int counter;

    public EnemyGenerator(DodgeGameManager dodgeGameManager){
        this.dodgeGameManager = dodgeGameManager;
        counter = 1;
    }

    public void Generate(){
        if(counter%40 == 0){
            int num = (int)(Math.random()* 0.5) + 1; //enemy generated this round
            for(int i = 0; i < num; i++) {
                dodgeGameManager.shells.add(new Shell(dodgeGameManager));
                counter = 1;
            }
        }else{
            counter++;
        }
    }
}

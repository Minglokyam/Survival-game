package com.example.survivalgame;

public class GenerateEnemy {
    int counter;
    GenerateEnemy(){ counter = 1; }
    public void Generate(){
        if(counter%40 == 0){
            int num = (int)(Math.random()* 0.5) + 1; //enemy generated this round
            for(int i = 0; i < num; i++) {
                DodgeGameView.shells.add(new Shell());
                counter = 1;
            }
        }else{
            counter++;
        }
    }
}

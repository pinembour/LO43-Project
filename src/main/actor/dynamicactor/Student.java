package main.actor.dynamicactor;

import main.actor.staticactor.Chair;
import main.math.Vector2f;
import main.graphics.Color;

import java.util.Random;

public class Student extends Character{

    int time = 0 ;
    int max = 800, min = 400;
    int timeMax = 	new Random().nextInt((max - min) + 1) + min;

    private boolean isRegistred ;

    // le student a un objectif des son apparition

    Chair chair;

    public Student(int x , int y, int id , Chair chair){
        super(x,y,id);
        hasAGoal = true;
        goalPoint = new Vector2f(chair.getX(),chair.getY());
        this.chair = chair;
        chair.setFree(false);
        chair.setReserved(true);
    }


    public void update(){

        // est inscrit et devant la sortie
         if (isRegistred && this.x == goalPoint.getX() && this.y == goalPoint.getY()){
             removed = true;
         }

        goalManagement();

         //timer inscription
        if (!isRegistred){time++;}

        if (!hasAGoal && ! isRegistred ){
            isSit = true;
            chair.setReserved(false);
            chair.setOccupied(true);
        }

        // a fini son inscription ?
        if (time>=timeMax && !isRegistred && !hasAGoal){
            isRegistred = true;
            isSit = false;
            chair.setFree(true);
            chair.setOccupied(false);
            goalPoint = new Vector2f(80,10);
            hasAGoal= true;
            System.out.println(isRegistred);
        }
    }

    public void render(){
        renderCharacter(Color.GREEN);
    }




}

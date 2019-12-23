package main.actor.dynamicactor;

import main.graphics.Color;
import main.graphics.Renderer;

public class Student extends Character{

    int time = 0 ;

    private boolean isRegistred ;

    // le student a un obj des son apparition
    public Student(int x , int y, int id , int goalX, int goalY ){
        super(x,y,id);
        hasAGoal = true;
        //TODO goalPoint = new Vector2f(goalX,goalY);
    }


    public void update(){


        //TODO  if (isRegistred && this.x == goalPoint.x && this.y == goalPoint.y){
        //TODO      removed = true;
        //TODO  }


        //TODO goalManagement();

        if (!isRegistred){time++;}

        if (time>=500 && !isRegistred && !hasAGoal){
            isRegistred = true;
            //TODO  goalPoint = new Vector2f(80,10);
            hasAGoal= true;
            System.out.println(isRegistred);
        }
    }

    public void render(){
        //TODO   renderCharacter(Color.GREEN);
    }




}

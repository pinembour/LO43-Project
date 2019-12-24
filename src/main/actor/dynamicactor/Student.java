package main.actor.dynamicactor;

import main.Vector2f;
import main.graphics.Color;
import main.graphics.Renderer;

public class Student extends Character{

    int time = 0 ;

    private boolean isRegistred ;

    // le student a un obj des son apparition
    public Student(int x , int y, int id , int goalX, int goalY ){
        super(x,y,id);
        hasAGoal = true;
        goalPoint = new Vector2f(goalX,goalY);
    }


    public void update(){


         if (isRegistred && this.x == goalPoint.getX() && this.y == goalPoint.getY()){
             removed = true;
         }


        goalManagement();

        if (!isRegistred){time++;}

        if (time>=500 && !isRegistred && !hasAGoal){
            isRegistred = true;
            goalPoint = new Vector2f(80,10);
            hasAGoal= true;
            System.out.println(isRegistred);
        }
    }

    public void render(){
        renderCharacter(Color.GREEN);
    }




}

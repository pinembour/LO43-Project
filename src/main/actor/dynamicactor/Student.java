package main.actor.dynamicactor;

import main.graphics.Color;
import main.graphics.Renderer;
import org.lwjgl.Sys;
import org.lwjgl.util.vector.Vector2f;

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


        if (isRegistred && this.x == goalPoint.x && this.y == goalPoint.y){
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

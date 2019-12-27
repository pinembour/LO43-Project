package main.actor.dynamicactor;

import main.actor.staticactor.Chair;
import main.actor.staticactor.Computer;
import main.math.Vector2f;
import main.graphics.Color;

import java.util.Random;

public class Student extends Character{
    
    private boolean isRegistred ;

    // le student a un objectif des son apparition

    public Student(int x , int y, Computer computer){
        super(x,y);
        hasAGoal = true;
        this.chair = computer.getStudentChair();
        goalPoint = new Vector2f(chair.getX(),chair.getY());
        chair.setChairState(Chair.ChairState.RESERVED);
        computer.setStudent(this);
    }


    public void update(){

        // est inscrit et devant la sortie
         if (isRegistred && hasAGoal &&this.x == goalPoint.getX() && this.y == goalPoint.getY()){
             removed = true;
         }

        goalManagement();

        if (!hasAGoal && ! isRegistred ){
            isSit = true;
            chair.setChairState(Chair.ChairState.OCCUPIED);
        }

        // a fini son inscription ?
        if (isRegistred && !hasAGoal){
            isSit = false;
            goalPoint = new Vector2f(80,10);
            hasAGoal= true;
            //System.out.println(isRegistred);
        }
    }

    public void render(){
        renderCharacter(Color.GREEN);
    }


    public boolean isRegistred() {
        return isRegistred;
    }

    public void setRegistred(boolean registred) {
        isRegistred = registred;
    }
}

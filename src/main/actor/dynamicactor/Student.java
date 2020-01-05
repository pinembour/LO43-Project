package main.actor.dynamicactor;

import main.actor.staticactor.Chair;
import main.actor.staticactor.Computer;
import main.maps.TiledMap;
import main.math.Vector2;
import main.graphics.Color;
import main.utiles.Constants;

public class Student extends Character{
    
    private boolean isRegistred ;

    // le student a un objectif des son apparition

    public Student(int x , int y, Computer computer, TiledMap map){
        super(x,y,map);
        hasAGoal = true;
        this.chair = computer.getStudentChair();
        goalPoint = new Vector2<Integer>(chair.getCurrentTile().getX(),chair.getCurrentTile().getY());
        chair.setChairState(Chair.ChairState.RESERVED);
        computer.setStudent(this);
    }


    public void update(){

        // est inscrit et devant la sortie
         if (isRegistred &&
                 this.currentTile.getY()+1  == Constants.STUDENT_DESPAWN_Y ) {
             removed = true;
             System.out.println("Rip");
         }

        goalManagement();

        if (!hasAGoal && ! isRegistred ){
            isSit = true;
            chair.setChairState(Chair.ChairState.OCCUPIED);
        }

        // a fini son inscription ?
        if (isRegistred && !hasAGoal){
            isSit = false;
            goalPoint = new Vector2<Integer>(Constants.STUDENT_DESPAWN_X, Constants.STUDENT_DESPAWN_Y);
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

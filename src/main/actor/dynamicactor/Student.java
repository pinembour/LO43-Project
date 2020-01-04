package main.actor.dynamicactor;

import main.actor.staticactor.Chair;
import main.actor.staticactor.Computer;
import main.maps.TiledMap;
import main.math.Vector2;
import main.graphics.Color;

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
         if (isRegistred && hasAGoal && this.currentTile.getX() == goalPoint.getX() && this.currentTile.getY()  == goalPoint.getY()){
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
            goalPoint = new Vector2<Integer>(8,10);
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

package main.actor.dynamicactor;

import main.Component;
import main.actor.staticactor.Chair;
import main.math.Vector2f;
import main.game.Game;
import main.graphics.Color;

public class Teacher extends Character {


    protected float tired = 100;
    protected float comfort = 100;

    protected boolean isSelected = false;

    public Teacher(int x , int y){
        super(x,y);
    }


    public void update(){

        //keyManagement();        // touche de clavier

        goalManagement();


        if (chair != null){ // si une chaise lui est désigné
            if (!hasAGoal){ // et qu'il n'a pas d'objectif
                isSit = true;
            }

            if (isSit){
                chair.setChairState(Chair.ChairState.OCCUPIED);
            }else {
                chair.setChairState(Chair.ChairState.RESERVED);
            }
        }

    }

    public void render(){
        if (isSelected){
            renderCharacter(Color.RED);
        }else {
            renderCharacter(Color.WHITE);
        }
    }

    //---------------------------------------

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

package main.actor.dynamicactor;

import main.Component;
import main.actor.staticactor.Chair;
import main.graphics.Renderer;
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

        if (tired > 0 ){
            tired -= 0.02f;
        }

        if (isSit && comfort>0){
            comfort -= 0.02f;
        }

    }

    public void render(){
        if (isSelected){
            renderCharacter(Color.RED);
        }else {
            renderCharacter(Color.WHITE);
        }

        int widthbar = 8;
        int heightBar = 1 ;
        int yBar = 5 ;


        Renderer.renderLoadingBar(x-widthbar/2, y - yBar , widthbar , heightBar , Color.BLUE,tired);
        Renderer.renderLoadingBar(x-widthbar/2, y - yBar - heightBar , widthbar , heightBar , Color.RED,comfort);
    }

    //---------------------------------------

    public void backToSpawn(){
        hasAGoal = true;
        isSelected = false;
        isSit = false;
        chair = null;
        computer = null;
        goalPoint = new Vector2f( (int) (100 + (Math.random() * (210 - 100))), 145);

    }

    //---------------------------------------

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

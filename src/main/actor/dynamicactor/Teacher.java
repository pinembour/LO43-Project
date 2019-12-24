package main.actor.dynamicactor;

import main.Component;
import main.Vector2f;
import main.actor.Actor;
import main.game.Game;
import main.graphics.Color;
import main.graphics.Renderer;


import java.awt.*;

public class Teacher extends Character {


    protected float tired = 100;
    protected float comfort = 100;

    protected boolean isSelected = false;
    protected boolean mouseButton1 = false;     // click gauche


    public Teacher(int x , int y, int id ){
        super(x,y,id);
    }


    public void update(){

        keyManagement();

        clickManagement();

        goalManagement();


    }

    public void render(){
        if (isSelected){
            renderCharacter(Color.RED);
        }else {
            renderCharacter(Color.WHITE);
        }
    }


    //---

    protected void clickManagement(){
        if (Component.input.isMouseButtonPressed(0)){
            if (isSelected){                // = click la ou on veut que le perso aille
                hasAGoal = true;            // le perso a un objectif
                isSelected = false;         // il n'est plus en attente
                goalPoint = new Vector2f((float)Game.getMouseX(),(float) Game.getMouseY());   // on fixe son obj
            }else{
                if (getDistanceFromMouse() < 8 ){   // = si on click sur le perso
                    isSelected = true;              // le perso attend un obj
                    hasAGoal = false;
                    animation.pause();
                }
            }
        }
    }


}

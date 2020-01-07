package main.actor.staticactor;

import main.game.level.GameTimer;
import main.game.level.Level;
import main.graphics.Color;
import main.graphics.Renderer;
import main.utiles.Constants;

import java.lang.constant.Constable;

public class CoffeeMachine extends Object{

    GameTimer coffeeTimer;
    boolean available = true;

    public CoffeeMachine(int x, int y,int level,int tilePosition){
        super(x,y,level,tilePosition);
        levelMax = 1;
    }

    public void levelUp() {
        if (level < levelMax) {
            if (Level.player.getGold() >= (10)) {
                Level.player.removeGold(10);
                level++;
            } else {
                System.out.println("Pas assez de gold");
            }
        }
    }

    public  void update(){
        if (coffeeTimer!=null){
            if (coffeeTimer.isOver() && !available){
                available = true;
                coffeeTimer.startPause();
                coffeeTimer = null;
            }
        }

    }

    public void render(){
        //Renderer.renderRectangle(position.getX() , position.getY(), 10 , 10 , Color.BLACK);
        if (coffeeTimer!=null){
            Renderer.drawText(" " + (Constants.COFFEE_TIME_TO_AVAILABLE - coffeeTimer.toSeconds()), (int)(float) position.getX(),(int)(float)  position.getY(),
                Constants.HUD_FONT_SIZE, Color.BLACK);
        }
    }


    public GameTimer getCoffeeTimer() {
        return coffeeTimer;
    }

    public void setCoffeeTimer(GameTimer coffeeTimer) {
        this.coffeeTimer = coffeeTimer;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

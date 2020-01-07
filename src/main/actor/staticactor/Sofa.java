package main.actor.staticactor;

import main.game.level.Level;
import main.graphics.Color;
import main.graphics.Renderer;

public class Sofa extends Object{

    public Sofa(int x, int y,int level, int tilePosition) {
        super(x, y, level, tilePosition);
        levelMax = 3;
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

    public void render(){
        Renderer.renderRectangle(position.getX() , position.getY(), 10 , 10 , Color.BLACK);
    }
}

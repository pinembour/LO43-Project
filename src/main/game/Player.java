package main.game;

import main.Component;
import main.graphics.Color;
import main.graphics.Renderer;
import main.utiles.Constants;

public class Player {

    int gold;

    public Player(){
        gold =0;
    }

    public void update(){
    }

    public void render(){
        Renderer.drawText( gold +"g",
                Constants.HUD_X , Constants.HUD_GOLD_Y ,
                Constants.HUD_FONT_SIZE, Color.YELLOW);
    }

    public void addGold(int gold){
        this.gold += gold;
    }

    public void removeGold(int gold){
        this.gold -= gold;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return "Player{" +
                "gold=" + gold +
                '}';
    }
}

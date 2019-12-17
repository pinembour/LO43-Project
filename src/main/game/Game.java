package main.game;

import main.Component;
import main.game.level.Level;
import main.graphics.Renderer;

public class Game {

    Level level ;


    public Game(){
        level = new Level(Component.width/16,Component.height/16);
    }

    public void init(){
        level.init();
    }

    public void update(){
        level.update();
    }

    public void render(){

        level.render();
        /*
        float[] color = new float[]{0.5f , 0.8f, 0.1f , 1.0f };
        Renderer.renderQuad(50,50,20,20, color);
        Renderer.renderLine(100,100,200,100);*/
    }
}

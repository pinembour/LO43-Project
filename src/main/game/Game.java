package main.game;

import main.graphics.Renderer;

public class Game {
    public Game(){

    }

    public void init(){

    }

    public void update(){

    }

    public void render(){
        float[] color = new float[]{0.5f , 0.8f, 0.1f , 1.0f };
        Renderer.renderQuad(50,50,20,20, color);
        Renderer.renderLine(100,100,200,100);
    }
}

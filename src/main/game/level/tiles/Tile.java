package main.game.level.tiles;

import main.Component;
import main.game.Game;
import main.graphics.Renderer;

import java.util.Random;

public class Tile {

    public int x,y;
    public int size = 16;
    public float[] color;

    Random random = new Random();

    public Tile(int x, int y){
        this.x=x;
        this.y=y;

        color = new float[]{random.nextFloat(),random.nextFloat(),random.nextFloat() , 1};

    }

    public void render(){
        // Gestion disparition des Tiles si elles ne sont pas devant l'écran
        float x0 = x + Game.xScroll / size;
        float y0 = y + Game.yScroll / size;

        float x1 = x + 1 + Game.xScroll / size;
        float y1 = y + 1 + Game.yScroll / size;

        if (x1 < 0 || y1 < 0 || x0 > Component.width / size || y0 > Component.height / size) return;
        Renderer.renderQuad(x*size,y*size,size,size,color);
    }
}

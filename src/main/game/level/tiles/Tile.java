package main.game.level.tiles;

import main.Component;
import main.game.Game;
import main.graphics.Renderer;

import java.util.Random;

public class Tile {

    public int x,y;
    public int size = 16;
    public float[] color;
    TilesType tileType;


    public enum TilesType{
        GRASS, ROCK, WATER
    }

    public Tile(int x, int y, TilesType tilesType){
        this.x=x;
        this.y=y;
        this.tileType = tilesType;

        if (tilesType == TilesType.GRASS) color = new float[]{ 0.1f, 0.5f, 0 , 1};
        if (tilesType == TilesType.ROCK) color = new float[]{  0.5f, 0.5f, 0.5f , 1};
        if (tilesType == TilesType.WATER) color = new float[]{ 0.5f, 0.5f, 0.9f , 1};



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

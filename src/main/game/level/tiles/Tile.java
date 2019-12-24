package main.game.level.tiles;

import main.Component;
import main.game.Game;
import main.graphics.Renderer;
import main.graphics.Texture;

import java.util.Random;

public class Tile {


    public int x,y;
     int xo = 0,yo =0 ;       // se repérer dans le spriteSheet
    public int size =32;     // taille tile à l'écran
    public int halfSize = size /2 ;     // taille de la texture à l'écran

    public int[] tileSprite ;//= new int[]{1, 1, 1, 1, 1, 1, 1, 1}

    public float[] color;
    TilesType tileType;


    public enum TilesType{
        GRASS, ROCK, WATER
    }

    public Tile(int x, int y, TilesType tilesType){
        this.x=x;
        this.y=y;
        this.tileType = tilesType;

        tileSprite = new int[8];


        color = new float[]{ 1, 1, 1 , 1};

    }

    public void render(){
        // Gestion disparition des Tiles si elles ne sont pas devant l'écran
//        float x0 = x + Game.xScroll / size;
//        float y0 = y + Game.yScroll / size;
//
//        float x1 = x + 1 + Game.xScroll / size;
//        float y1 = y + 1 + Game.yScroll / size;
//
//        if (x1 < 0 || y1 < 0 || x0 > Component.width / size || y0 > Component.height / size) return;

        Texture.tiles.bind();
        Renderer.renderQuad(x*size ,y*size , size , size,color, xo , yo  );
        Texture.tiles.unbind();

    }
}

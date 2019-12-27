package main.game.level.tiles;

import main.Component;
import main.game.Game;
import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;

public class Tile {

    public int x,y;             // position d'une tile
     int xo = 0,yo =0 ;       // se repérer dans le spriteSheet
    public int size =16;     // taille tile à l'écran

    TilesType tileType;


    public enum TilesType{
        GRASS, ROCK, WATER
    }

    public Tile(int x, int y, TilesType tilesType){
        this.x=x;
        this.y=y;
        this.tileType = tilesType;
    }

    public void render(){
        //Gestion disparition des Tiles si elles ne sont pas devant l'écran
        float x0 = x + Game.xScroll / size;
        float y0 = y + Game.yScroll / size;

        float x1 = x + 1 + Game.xScroll / size;
        float y1 = y + 1 + Game.yScroll / size;

        if (x1 < 0 || y1 < 0 || x0 > Component.width / size || y0 > Component.height / size) return;

        //--

        Texture.tiles.bind();
        Renderer.renderQuad(x*size ,y*size , size , size, Color.WHITE, xo , yo  );
        Texture.tiles.unbind();

    }
}

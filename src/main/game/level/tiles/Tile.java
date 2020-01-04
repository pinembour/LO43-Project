package main.game.level.tiles;

import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;
import main.math.Vector2;
import main.utiles.Constants;

public class Tile {

    public int x,y;             // position d'une tile
     int xo = 0,yo =0 ;       // se repérer dans le spriteSheet
    public int size = Constants.TILE_SIZE;     // taille tile à l'écran
    private String tileSet;
    private Texture texture;

    TilesType tileType;


    public enum TilesType{
        VISIBLE, INVISIBLE
    }

    public Tile(int x, int y, TilesType tilesType){
        this.x=x;
        this.y=y;
        this.tileType = tilesType;
        //texture=Texture.tiles;
    }

    public Tile(int x , int y , String tileSet, Vector2 tileSetPosition){
        this.x = x;
        this.y = y;
        this.tileSet = tileSet;
        this.xo = (int)tileSetPosition.getX();
        this.yo = (int)tileSetPosition.getY();

        if (tileSet.equals("pcTable.png")){
            texture = Texture.pcTable;
        }else if (tileSet.equals("floor.png")){
            texture = Texture.floor;
        }else if (tileSet.equals("wallsCarpet.png")){
            texture = Texture.wallsCarpet;
        }else if (tileSet.equals("furniture.png")){
            texture= Texture.furniture;
        }

        tileType = TilesType.VISIBLE;

    }

    public void render(){
        //Gestion disparition des Tiles si elles ne sont pas devant l'écran
//        float x0 = x + Game.xScroll / size;
//        float y0 = y + Game.yScroll / size;
//
//        float x1 = x + 1 + Game.xScroll / size;
//        float y1 = y + 1 + Game.yScroll / size;
//
//        if (x1 < 0 || y1 < 0 || x0 > Component.width / size || y0 > Component.height / size) return;

        //--

        if (tileType != TilesType.INVISIBLE) {
            texture.bind();
            Renderer.renderQuad(x * size, y * size, size, size, Color.WHITE, xo, yo);
            texture.unbind();
        }
    }

    public TilesType getTileType() {
        return tileType;
    }
}

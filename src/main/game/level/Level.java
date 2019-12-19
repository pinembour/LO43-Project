package main.game.level;

import main.game.level.tiles.Tile;
import main.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Level {

    public int width, height;

    List<Tile> tiles = new ArrayList<Tile>();
    Tile[][] tilesArrays;

    public Level(int width, int height){
        this.height = height;
        this.width = width;
        tilesArrays = new Tile[width][height];
        setTiles();
    }

    public void setTiles(){
        for (int x = 0 ; x <width; x++){
            for(int y = 0 ; y <height; y++){
                tilesArrays[x][y] =  new Tile(x,y, Tile.TilesType.GRASS);

            }
        }
        for (int x = 0 ; x <width; x++){
            for (int y = 0 ; y < height; y++){
                //tiles.add(new Tile(x,y, Tile.TilesType.ROCK));
                tiles.add(tilesArrays[x][y]);
            }
        }
    }

    public void addTiles(int x, int y){

    }

    public void init(){

    }

    public void update(){

    }

    public void render(){

        // On affiche toutes les Tiles
        for (Tile tile : tiles){
            tile.render();
        }
    }
}

package main.game.level;

import main.game.level.tiles.Tile;
import main.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Level {

    public int width, height;

    List<Tile> tiles = new ArrayList<Tile>();

    public Level(int width, int height){
        this.height = height;
        this.width = width;

        generate();
    }

    public void generate(){
        for (int x = 0 ; x <width; x++){
            for (int y = 0 ; y < height; y++){
                tiles.add((new Tile(x,y)));
            }
        }
    }

    public void init(){

    }

    public void update(){

    }

    public void render(){

        for (Tile tile : tiles){
            tile.render();
        }
    }
}

package main.game.level;

import main.actor.Actor;
import main.actor.dynamicactor.Student;
import main.game.level.tiles.Tile;
import main.graphics.Renderer;

import java.util.ArrayList;
import java.util.List;

public class Level {

    public int width, height;

    List<Tile> tiles = new ArrayList<Tile>();
    Tile[][] tilesArrays;

    List<Actor> actors = new ArrayList<Actor>();


    public Level(int width, int height){
        this.height = height;
        this.width = width;
        tilesArrays = new Tile[width][height];
        setTiles();
        spawner();
    }

    public void spawner(){
        addActor(new Student(5,5,1));
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

    public void addActor(Actor a){ actors.add(a); }
    public void removeActor(Actor a){ actors.remove(a); }


    public void update(){
        for (int i = 0 ; i <actors.size(); i++){
            Actor a = actors.get(i);
            if (a.getRemoved()) actors.remove(i);
            a.render();
        }
    }

    public void render(){

        // On affiche toutes les Tiles
        for (Tile tile : tiles){
            tile.render();
        }

        for (int i = 0 ; i <actors.size(); i++){
            Actor a = actors.get(i);
            a.render();
        }
    }
}

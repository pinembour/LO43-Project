package main.game.level;

import main.Component;
import main.actor.Actor;
import main.actor.dynamicactor.Student;
import main.actor.dynamicactor.Teacher;
import main.game.Game;
import main.game.level.tiles.Tile;
import main.graphics.Color;
import main.graphics.Renderer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.util.ArrayList;
import java.util.List;

public class Level {

    public int width, height;

    List<Tile> tiles = new ArrayList<Tile>();
    Tile[][] tilesArrays;

    List<Actor> actors = new ArrayList<Actor>();

    private boolean isOnPause = false;
    private boolean keyDownEscape;


    public Level(int width, int height){
        this.height = height;
        this.width = width;
        tilesArrays = new Tile[width][height];
        setTiles();
        spawnTeacher();
        spawnStudent();
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

    //---

    public void addActor(Actor a){ actors.add(a); }
    public void removeActor(Actor a){ actors.remove(a); }
    public void spawnTeacher(){
        addActor(new Teacher(10,10,1));
        addActor(new Teacher(100,10,1));
        addActor(new Teacher(100,100,1));
    }
    public void spawnStudent(){
        addActor(new Student(100,100,3,200,50));
    }


    //---

    public void update(){
        if (!isOnPause){
            for (int i = 0 ; i <actors.size(); i++){
                Actor a = actors.get(i);
                if (a.getRemoved()) actors.remove(i);
                a.update();
            }
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE ) && !keyDownEscape){
            isOnPause = !isOnPause;
        }
        keyDownEscape = Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);

    }

    public void render(){

        // On affiche toutes les Tiles
        for (Tile tile : tiles){
            tile.render();
        }

        // On affiche tout les actors
        for (int i = 0 ; i <actors.size(); i++){
            Actor a = actors.get(i);
            a.render();
        }

        if (isOnPause){
            int w = 10;
            int h = 50;
            float x = Component.width /2 - w/2;
            float y = Component.height/2 - h /2 ;
            Renderer.renderRectangle(x + 20, y, w, h, Color.BLACK);
            Renderer.renderRectangle(x - 20, y , w, h, Color.BLACK);

        }
    }

}

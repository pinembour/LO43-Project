package main.game.level;

import main.Component;
import main.actor.Actor;
import main.actor.dynamicactor.Student;
import main.actor.dynamicactor.Teacher;
import main.actor.staticactor.Chair;
import main.game.Game;
import main.game.level.tiles.Tile;
import main.graphics.Color;
import main.graphics.Renderer;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;


public class Level {

    public int width, height;

    List<Tile> tiles = new ArrayList<Tile>();
    Tile[][] tilesArrays;

    List<Actor> actors = new ArrayList<Actor>();

    List<Chair> chairs = new ArrayList<Chair>();

    private boolean isOnPause = false;


    private int studentWaiting = 10;

    public Level(int width, int height){
        this.height = height;
        this.width = width;
        tilesArrays = new Tile[width][height];
        setTiles();
        spawnChair();
        spawnTeacher();
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

    public void addChair(Chair chair){chairs.add(chair);}
    public void removeChair(Chair chair){ chairs.remove(chair); }


    public void spawnTeacher(){
        addActor(new Teacher(10,10,1));
        addActor(new Teacher(100,10,1));
        addActor(new Teacher(100,100,1));
    }
    public void spawnStudent(Chair chair){
        addActor(new Student(100,100,3,chair));
        studentWaiting--;
        System.out.println("Il reste " + studentWaiting + " à inscrire");

    }

    public void spawnChair(){
        int xFirstChair = 60;
        int yFirstChair = 20;
        int nbChaire = 6;

        for (int i = 0 ; i< nbChaire; i++){
            addChair(new Chair(xFirstChair ,yFirstChair+ i *20 ));
        }

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


        if (Component.input.isKeyPressed(GLFW_KEY_ESCAPE)){
            isOnPause = !isOnPause;
            System.out.println("Pause : " + isOnPause);
        }


        if (studentWaiting > 0 ){
            for (Chair chair :chairs){
                if (chair.isFree() ){
                    spawnStudent(chair);
                }
            }
        }

    }



    public void render(){

        // On affiche toutes les Tiles
        for (Tile tile : tiles){
            tile.render();
        }

//        for (int i = 0 ; i<chairs.size(); i++){
//            chairs.get(i).render();
//        }

        for (Chair chair :chairs){
            chair.render();
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

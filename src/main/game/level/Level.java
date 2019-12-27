package main.game.level;

import main.Component;
import main.actor.Actor;
import main.actor.dynamicactor.Student;
import main.actor.dynamicactor.Teacher;
import main.actor.staticactor.Chair;
import main.actor.staticactor.CoffeeMachine;
import main.actor.staticactor.Computer;
import main.game.Game;
import main.game.level.tiles.Tile;
import main.graphics.Color;
import main.graphics.Renderer;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;


public class Level {

    public int width, height;   // taille du niveau

    List<Tile> tiles = new ArrayList<Tile>();      // liste pour affichage des tiles
    Tile[][] tilesArrays;                       // tableau pour fabriquer le niveau nous meme

    List<Actor> actors = new ArrayList<Actor>();
    List<Computer> computers = new ArrayList<Computer>();

    private boolean isOnPause = false;  // a-t-on mis le jeu en pause


    private int studentWaiting = 10;

    public Level(int width, int height){
        this.height = height;       // on defini la hauteur du niveau
        this.width = width;         // on defini la largeur du niveau
        tilesArrays = new Tile[width][height];      // un tableau de tiles pour se repérer
        setTiles();                 // on charge ensuite les tiles qu'on mettre dans une liste pour affichage
        spawnComputer();            // on affiche les Ordi + chaise
        spawnTeacher();             // on affiche les profs

    }

    public void setTiles(){
        for (int x = 0 ; x <width; x++){
            for(int y = 0 ; y <height; y++){
                tilesArrays[x][y] =  new Tile(x,y, Tile.TilesType.GRASS); // on defini les tiles pour chaque case du tableau
            }
        }
        for (int x = 0 ; x <width; x++){
            for (int y = 0 ; y < height; y++){
                //tiles.add(new Tile(x,y, Tile.TilesType.ROCK));
                tiles.add(tilesArrays[x][y]);   // on met les tiles du tableau une a une dans la list pour affichage
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

    public void addComputer(Computer computer){computers.add(computer);}
    public void removeComputer(Computer computer){computers.remove(computer);}


    public void spawnTeacher(){
        addActor(new Teacher(10,10));
        addActor(new Teacher(100,10));
        addActor(new Teacher(100,100));
    }
    public void spawnStudent(Chair chair){
        addActor(new Student(10,140,chair));
        studentWaiting--;
        System.out.println("Il reste " + studentWaiting + " dehors ");

    }

    public void spawnComputer(){
        int xFirstComputer = 60;
        int yFirstComputer = 50;
        int distanceComputer = 30;
        int nbComputer = 3;
        for (int i = 0 ; i< nbComputer; i++){
            addComputer(new Computer(xFirstComputer,yFirstComputer + i * distanceComputer));
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


            if (studentWaiting > 0 ){
                for (Computer computer : computers){
                    if (computer.getStudentChair().isFree()){
                        spawnStudent(computer.getStudentChair());
                    }
                }
            }
        }


        if (Component.input.isKeyPressed(GLFW_KEY_ESCAPE)){
            isOnPause = !isOnPause;
            System.out.println("Pause : " + isOnPause);
        }
    }



    public void render(){

        // On affiche toutes les Tiles
        for (Tile tile : tiles){
            tile.render();
        }

        for (Computer computer: computers){
            computer.render();
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

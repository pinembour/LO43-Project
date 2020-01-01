package main.game.level;

import main.Component;
import main.actor.Actor;
import main.actor.dynamicactor.Student;
import main.actor.dynamicactor.Teacher;
import main.actor.staticactor.Chair;
import main.actor.staticactor.CoffeeMachine;
import main.actor.staticactor.Computer;
import main.game.Game;
import main.game.Player;
import main.game.level.tiles.Tile;
import main.graphics.Color;
import main.graphics.Renderer;
import main.math.Vector2f;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

import static main.actor.staticactor.Chair.ChairState.FREE;
import static org.lwjgl.glfw.GLFW.*;


public class Level {

    public int width, height;   // taille du niveau

    List<Tile> tiles = new ArrayList<Tile>();      // liste pour affichage des tiles
    Tile[][] tilesArrays;                       // tableau pour fabriquer le niveau nous meme

    List<Actor> actors = new ArrayList<Actor>();
    List<Teacher> teachers = new ArrayList<Teacher>();
    List<Computer> computers = new ArrayList<Computer>();

    private boolean isOnPause = false;  // a-t-on mis le jeu en pause

    //---

    Teacher teacherSelected = null;
    Computer computerSelected = null;

    //---

    public static Player player;


    private int studentToRegister = 20;
    private int studentWaiting;

    public Level(int width, int height){
        player = new Player();
        studentWaiting = studentToRegister;
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
                if (x < width/2) {
                    tilesArrays[x][y] = new Tile(x, y, Tile.TilesType.GRASS); // on defini les tiles pour chaque case du tableau
                }else {
                    tilesArrays[x][y] = new Tile(x, y, Tile.TilesType.ROCK); // on defini les tiles pour chaque case du tableau
                }

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

    public void addTeacher(Teacher a){ teachers.add(a); }
    public void removeTeacher(Teacher a){ teachers.remove(a); }

    public void addComputer(Computer computer){computers.add(computer);}
    public void removeComputer(Computer computer){computers.remove(computer);}


    public void spawnTeacher(){
        addTeacher(new Teacher(10,10));
        addTeacher(new Teacher(104,100));
        addTeacher(new Teacher(100,100));
    }
    public void spawnStudent(Computer computer){
        addActor(new Student(10,140,computer));
        studentWaiting--;
        System.out.println("Il reste " + studentWaiting + " dehors ");

    }

    public void spawnComputer(){
        int xFirstComputer = 60;
        int yFirstComputer = 50;
        int distanceComputer = 30;
        int nbComputer = 3;
        for (int i = 0 ; i< nbComputer; i++){
            if (i ==0){
                addComputer(new Computer(xFirstComputer,yFirstComputer + i * distanceComputer , 1));

            }else {
                addComputer(new Computer(xFirstComputer,yFirstComputer + i * distanceComputer , 0));

            }
        }
        for (int i = 0 ; i< nbComputer; i++){
            addComputer(new Computer(xFirstComputer + 100,yFirstComputer + i * distanceComputer, 0));
        }
    }


    //---

    public void update(){
        player.update();

        if (!isOnPause){
            for (int i = 0 ; i <actors.size(); i++){
                Actor a = actors.get(i);
                if (a.getRemoved()) actors.remove(i);

                a.update();
            }

            for (Teacher teacher :teachers){
                teacher.update();
            }

            for (Computer computer:computers){
                computer.update();
            }

            // gestion spawn des etudiants
            if (studentWaiting > 0 ){
                for (Computer computer : computers){
                    if (computer.getStudentChair().getChairState().equals(FREE) && computer.getLevel()>0){
                        spawnStudent(computer);
                    }
                }
            }


            // gestion du click gauche
            if (Component.input.isMouseButtonPressed(0)){   // si le joueur clique
                Vector2f mouseClickPosition = new Vector2f((int)Game.getMouseX(), (int)Game.getMouseY());
                System.out.println(mouseClickPosition.toString());

                if (teacherSelected == null){ // si aucun prof n'est séléctionné
                    teacherSelection(mouseClickPosition);
                }else {
                    System.out.println("un prof est séléctionné");

                    computerSelection(mouseClickPosition);

                }
            }
            if (Component.input.isMouseButtonPressed(GLFW_MOUSE_BUTTON_2)) {   // si le joueur clique
                Vector2f mouseClickPosition = new Vector2f((int)Game.getMouseX(), (int)Game.getMouseY());
                System.out.println("bla");
                computerLevelUp(mouseClickPosition);
            }


        }


        if (Component.input.isKeyPressed(GLFW_KEY_SPACE)){
            isOnPause = !isOnPause;
            System.out.println("Pause : " + isOnPause);
        }

        if (Component.input.isKeyPressed(GLFW_KEY_ESCAPE)){
            if (teacherSelected != null){
                teacherSelected.setSelected(false);
                teacherSelected = null;
            }
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

        for (Teacher teacher : teachers){
            teacher.render();
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

        player.render();
        Renderer.drawText("Student:" + studentWaiting + "/" + studentToRegister,
                0 , Component.height-10,8,Color.BLACK);



    }


    public void teacherSelection(Vector2f mouseClickPosition){
        // pour tout les profs
        for (Teacher teacher : teachers ){
            // si on click sur un prof
            if (Game.getDistanceBetween(mouseClickPosition,teacher.getPosition()) < 5 ){
                if (teacherSelected != null){ // si un prof a deja été selectionné
                    // on regarde lequel des deux est le plus proche de la souris
                    if (Game.getDistanceBetween(mouseClickPosition, teacher.getPosition() ) <
                            Game.getDistanceBetween(mouseClickPosition, teacherSelected.getPosition())){

                        teacherSelected = teacher;
                    }
                }else {
                    teacherSelected = teacher;
                }
            }
        }
        if (teacherSelected != null){
            teacherSelected.setSelected(true);
            teacherSelected.setHasAGoal(false);
        }

    }

    public void computerSelection(Vector2f mouseClickPosition){
        // pour tout les profs
        for (Computer computer : computers ){
            // si on click sur un pc
            if (Game.getDistanceBetween(mouseClickPosition,computer.getPosition()) < 30
                    && computer.getTeacherChair().getChairState().equals(FREE) && computer.getLevel()>0){
                if (computerSelected != null){ // si un pc a deja été selectionné
                    // on regarde lequel des deux est le plus proche de la souris
                    if (Game.getDistanceBetween(mouseClickPosition, computer.getPosition() ) <
                            Game.getDistanceBetween(mouseClickPosition, computerSelected.getPosition())){

                        computerSelected = computer;
                    }
                }else {
                    computerSelected = computer;
                }
            }
        }
        if (computerSelected != null){  // si on a selectionné un ordi
            teacherSelected.setHasAGoal(true);
            teacherSelected.setSelected(false);
            teacherSelected.setGoalPoint(computerSelected.getTeacherChair().getPosition());

            if (teacherSelected.getChair() != null){
                teacherSelected.getChair().setChairState(FREE);

                if (teacherSelected.getComputer().getRegistration() != null){
                    teacherSelected.getComputer().getRegistration().pause();
                }
                teacherSelected.setSit(false);
            }

            teacherSelected.setComputer(computerSelected);
            teacherSelected.setChair(computerSelected.getTeacherChair());
            computerSelected.setTeacher(teacherSelected);
            teacherSelected = null;
            computerSelected = null;
        }
    }

    public void computerLevelUp(Vector2f mouseClickPosition){
        for (Computer computer : computers ){
            // si on click sur un pc
            if (Game.getDistanceBetween(mouseClickPosition,computer.getPosition()) < 20 ){
                if (computerSelected != null){ // si un pc a deja été selectionné
                    // on regarde lequel des deux est le plus proche de la souris
                    if (Game.getDistanceBetween(mouseClickPosition, computer.getPosition() ) <
                            Game.getDistanceBetween(mouseClickPosition, computerSelected.getPosition())){

                        computerSelected = computer;
                    }
                }else {
                    computerSelected = computer;
                }
            }
            if (computerSelected != null){  // si on a selectionné un ordi
                computerSelected.levelUp();
                computerSelected=null;
            }
        }
    }
}

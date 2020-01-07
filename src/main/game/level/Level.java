package main.game.level;

import main.Component;
import main.actor.dynamicactor.Character;
import main.actor.dynamicactor.Student;
import main.actor.dynamicactor.Teacher;
import main.actor.staticactor.CoffeeMachine;
import main.actor.staticactor.Computer;
import main.actor.staticactor.Sofa;
import main.game.Game;
import main.game.Player;
import main.game.level.tiles.Tile;
import main.graphics.Color;
import main.graphics.Renderer;
import main.maps.Layer;
import main.maps.TileSet;
import main.maps.TiledMap;
import main.maps.TiledMapLoader;
import main.math.Vector2;
import main.utiles.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static main.actor.staticactor.Chair.ChairState.FREE;
import static org.lwjgl.glfw.GLFW.*;


public class Level {

    //-----------------------------------LOAD MAP --------------------------------------
    TiledMapLoader tiledMapLoader ;
    TiledMap map ;




    //---------------------------------------------------------------------------------------

    List<Tile> listTile = new ArrayList<Tile>();      // liste pour affichage des tiles

    List<Student> students = new ArrayList<Student>();
    List<Teacher> teachers = new ArrayList<Teacher>();
    List<Computer> computers = new ArrayList<Computer>();
    List<CoffeeMachine> coffeeMachines = new ArrayList<>();
    List<Sofa> sofas = new ArrayList<>();

    private boolean isOnPause = false;  // a-t-on mis le jeu en pause
    private boolean isOver = false;     //is the game over
    private boolean isLevelWon = false;      //Has the player won the level
    private boolean isGameWon = false;       // Has the player won the game

    //---

    Teacher teacherSelected = null;
    Computer computerSelected = null;
    CoffeeMachine coffeeMachineSelected = null;
    Sofa sofaSelected = null;
    //---

    public static Player player;

    //---

    private EventTimer eventTimer;
    private Random rand;
    private GameTimer gameTimer;
    private int studentToRegister = Constants.STUDENTS_LVL.get(0);
    private int studentLeftToRegister = studentToRegister;
    private int studentWaiting;
    private int level=0;

    public Level(){
        player = new Player();
        studentWaiting = studentToRegister;
        tiledMapLoader = new TiledMapLoader("res/tileset/map.tmx");
        map = tiledMapLoader.load();
        rand = new Random();
        eventTimer = new EventTimer();
        gameTimer = new GameTimer();
        spawnTeacher();             // on affiche les profs

    }

    public int getLevel() {
        return level;
    }

    public void init(){

        for (int i = 0 ; i< map.getLayersSize(); i++){
            chargeLayer(i);
        }
    }

    public void chargeLayer(int i ){
        boolean computerCreated = false;
        boolean coffeeMachineCreated = false;

        Layer layer = map.getLayer(i);
        List <Integer> listTileInt = layer.getGids();
        int x = 0, y = 0;
        for (Integer tileInt : listTileInt){

            TileSet tileSet = map.getGidsSet(tileInt);

            if (tileInt == 0) { // tile invisible
                this.listTile.add(new Tile(x, y, Tile.TilesType.INVISIBLE) );

            }else{ // tile avec texture
                 System.out.println("layer : " + i + " , gid : "+tileInt);


                this.listTile.add(new Tile(x, y, tileSet.getImage().getSource(), tileSet.getPosition(tileInt)));

                if (i == Constants.LAYER_LV1_BOTTOM || i == Constants.LAYER_LV1_TOP) {

                    //--------------COMPUTER
                    if (tileInt == Constants.TILE_INT_LV1_COMPUTER_2) {    // nouveau pc
                        if (computerCreated) {
                            addComputer(new Computer(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE,
                                    0, listTile.size()));

                        } else {
                            addComputer(new Computer(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE,
                                    1, listTile.size()));
                            computerCreated = true;

                        }
                    }
                    //----------coffeeMachine-------------
                    if (tileInt == Constants.TILE_INT_LV1_COFFEE_MACHINE_2) {
                        if (coffeeMachineCreated) {
                            //System.out.println("Nv machine à café");
                            addCoffeMachine(new CoffeeMachine(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE,
                                    0, listTile.size()));
                        } else {
                            //System.out.println("Nv machine à café");
                            addCoffeMachine(new CoffeeMachine(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE,
                                    1, listTile.size()));
                            coffeeMachineCreated = true;
                        }

                    }
                    //----------sofa-------------
                    if (i == 4){
                        if (tileInt == Constants.TILE_INT_LV1_SOFA_1) {
                            System.out.println("Nv sofa");
                            addSofa(new Sofa(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE,
                                    1, listTile.size()));

                        }
                    }
                }
            }

            x++;
            if (x > 23){
                x =0;
                y++;
            }
        }
    }

    //---

    public void addStudent(Student a){ students.add(a); }
    public void removeStudent(Student a){ students.remove(a); }

    public void addTeacher(Teacher a){ teachers.add(a); }
    public void removeTeacher(Teacher a){ teachers.remove(a); }

    public void addComputer(Computer computer){computers.add(computer);}
    public void removeComputer(Computer computer){computers.remove(computer);}

    public void addCoffeMachine(CoffeeMachine coffeeMachine){coffeeMachines.add(coffeeMachine);}
    public void removeCoffeMachine(CoffeeMachine coffeeMachine){coffeeMachines.remove(coffeeMachine);}

    public void addSofa(Sofa sofa){sofas.add(sofa);}


    public void spawnTeacher(){
            addTeacher(new Teacher(Constants.TEACHER_SPAWN_X+ 0 * Constants.TILE_SIZE, Constants.TEACHER_SPAWN_Y,map, Character.Fise.TC ));
            addTeacher(new Teacher(Constants.TEACHER_SPAWN_X+ 1 * Constants.TILE_SIZE, Constants.TEACHER_SPAWN_Y,map, Character.Fise.TC));
            addTeacher(new Teacher(Constants.TEACHER_SPAWN_X+ 2 * Constants.TILE_SIZE, Constants.TEACHER_SPAWN_Y,map, Character.Fise.TC));
            addTeacher(new Teacher(Constants.TEACHER_SPAWN_X+ 3 * Constants.TILE_SIZE, Constants.TEACHER_SPAWN_Y,map, Character.Fise.EDIM));
            addTeacher(new Teacher(Constants.TEACHER_SPAWN_X+ 4 * Constants.TILE_SIZE, Constants.TEACHER_SPAWN_Y,map, Character.Fise.ENERGIE));
            addTeacher(new Teacher(Constants.TEACHER_SPAWN_X+ 5 * Constants.TILE_SIZE, Constants.TEACHER_SPAWN_Y,map, Character.Fise.GMC));
            addTeacher(new Teacher(Constants.TEACHER_SPAWN_X+ 6 * Constants.TILE_SIZE, Constants.TEACHER_SPAWN_Y,map, Character.Fise.IMSI));
            addTeacher(new Teacher(Constants.TEACHER_SPAWN_X+ 7 * Constants.TILE_SIZE, Constants.TEACHER_SPAWN_Y,map, Character.Fise.INFO));
    }
    public void spawnStudent(Computer computer){

        Character.Fise[] arr = Character.Fise.values();
        int SIZE = arr.length;
        int fise = rand.nextInt(SIZE);
        addStudent(new Student(Constants.STUDENT_SPAWN_X,Constants.STUDENT_SPAWN_Y,computer, map, arr[fise] ));
        studentWaiting--;
    }




    //---

    public void update(){

            player.update();

            //Doit-on déclencher un évènement aléatoire
            if (eventTimer.IsEventNow()) {
                this.randomEvent();
            }

            if (!isOnPause) {
                for (int i = 0; i < students.size(); i++) {
                    Student a = students.get(i);
                    if (a.getRemoved()) students.remove(i);

                    a.update();
                }

                for (Teacher teacher : teachers) {
                    teacher.update();
                }

                for (Computer computer : computers) {
                    computer.update();
                }

                for (Student student : students){
                    if (!student.isCounted()){
                        if (student.isRegistered()) {
                            studentLeftToRegister--;
                            student.setCounted(true);
                        }
                    }
                }

                for (CoffeeMachine coffeeMachine : coffeeMachines) {
                    coffeeMachine.update();
                }

                for (Sofa sofa:sofas){sofa.render();}

                // gestion spawn des etudiants
                if (studentWaiting > 0) {
                    for (Computer computer : computers) {
                        if (computer.getStudentChair().getChairState().equals(FREE) && computer.getLevel() > 0) {
                            spawnStudent(computer);
                        }
                    }
                }


                // gestion du click gauche
                if (Component.input.isMouseButtonPressed(0)) {   // si le joueur clique
                    Vector2<Float> mouseClickPosition = new Vector2<Float>((float) Game.getMouseX(), (float) Game.getMouseY());
                    System.out.println(mouseClickPosition.toString());

                    if (teacherSelected == null) { // si aucun prof n'est séléctionné
                        teacherSelection(mouseClickPosition);
                    } else {
                        System.out.println("un prof est séléctionné");

                        computerSelection(mouseClickPosition);
                        coffeeMachineSelection(mouseClickPosition);
                        SofaSelection(mouseClickPosition);

                    }
                }
                if (Component.input.isMouseButtonPressed(GLFW_MOUSE_BUTTON_2)) {   // si le joueur clique
                    Vector2<Float> mouseClickPosition = new Vector2<Float>((float) Game.getMouseX(), (float) Game.getMouseY());
                    computerLevelUp(mouseClickPosition);
                    coffeeMachineLevelUp(mouseClickPosition);
                    sofaLevelUp(mouseClickPosition);

                }
            }

            if (Component.input.isKeyPressed(GLFW_KEY_SPACE)) {
                isOnPause = !isOnPause;
                if (isOnPause) {            //Décompte du temps en pause pour les évènements aléatoires et le timer de la partie.
                    eventTimer.startPause();
                    gameTimer.startPause();
                } else {
                    eventTimer.stopPause();
                    gameTimer.stopPause();
                }
                System.out.println("Pause : " + isOnPause);

            }

            if (Component.input.isKeyPressed(GLFW_KEY_ESCAPE)) {
                if (teacherSelected != null) {
                    teacherSelected.setSelected(false);
                    if (!teacherSelected.isSit() && teacherSelected.getChair() != null) {
                        teacherSelected.getChair().setChairState(FREE);
                        teacherSelected.setChair(null);
                    }
                    teacherSelected = null;
                }
            }
        }



    public void render(){

        renderLayer(Constants.LAYER_FLOOR);
        renderLayer(Constants.LAYER_DECOR_BOTTOM);
        renderLayer(Constants.LAYER_DECOR_MIDDLE);
        renderLayer(Constants.LAYER_WALL_BOTTOM);
        renderLayer(Constants.LAYER_LV1_BOTTOM);

        for (Teacher teacher : teachers){
            teacher.render();
        }

        // On affiche tout les actors
        for (Student a : students) {
            a.render();
        }
        renderLayer(Constants.LAYER_DECOR_TOP);
        renderLayer(Constants.LAYER_LV1_TOP);
        renderLayer(Constants.LAYER_WALL_TOP);

        for (Computer computer: computers){
            computer.render();
        }

        for (CoffeeMachine coffeeMachine:coffeeMachines)coffeeMachine.render();

        for (Sofa sofa:sofas)sofa.render();


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
                Constants.HUD_X , Constants.HUD_STUDENT_Y,
                Constants.HUD_FONT_SIZE,Color.WHITE);

        gameTimer.render();


    }

    public void renderLayer(int layer ){
        //pour chaque tile,
        for (int i = layer * Constants.TIlE_PER_LAYER  ; i< (layer+1)*Constants.TIlE_PER_LAYER ; i++) {

            if (listTile.get(i).getTileType() == Tile.TilesType.VISIBLE) {
                int gid = map.getLayer(layer).getGid(i-layer * Constants.TIlE_PER_LAYER);
               if ( gid == Constants.TILE_INT_LV1_COMPUTER_1 ||
                       gid == Constants.TILE_INT_LV1_COMPUTER_2 ||
                       gid == Constants.TILE_INT_LV1_COMPUTER_3 ){

                   renderTileComputer(layer, i );

               }else if (gid == Constants.TILE_INT_LV1_COFFEE_MACHINE_1 ||
                       gid == Constants.TILE_INT_LV1_COFFEE_MACHINE_2 ||
                       gid == Constants.TILE_INT_LV1_COFFEE_MACHINE_3 ){
                   renderTileCoffeeMachine(layer,i);
               }else if(gid == Constants.TILE_INT_LV1_SOFA_VIDE||
                       gid == Constants.TILE_INT_LV1_SOFA_1 ||
                       gid == Constants.TILE_INT_LV2_SOFA_1_1||
                       gid == Constants.TILE_INT_LV2_SOFA_2_1 ||
                       gid == Constants.TILE_INT_LV2_SOFA_1_2 ||
                       gid == Constants.TILE_INT_LV2_SOFA_2_2 ||
                       gid == Constants.TILE_INT_LV3_SOFA_1_1 ||
                       gid == Constants.TILE_INT_LV3_SOFA_2_1 ||
                       gid == Constants.TILE_INT_LV3_SOFA_3_1 ||
                       gid == Constants.TILE_INT_LV3_SOFA_1_2   ) {
                    renderTileSofa(layer, i);

                }else if (gid != 0 ){
                   listTile.get(i).render();
               }
                //---------------
               if (gid !=0 && (layer ==4 ||layer==8)){
                   //System.out.println("layer : " + layer + " , gid : "+gid);
               }
            }
        }
    }

    public void renderTileComputer(int layer , int i){
        int lvlPc = -1;
        // si on travail sur un layer pouvant avoir un upgrade
        if (layer == Constants.LAYER_LV1_BOTTOM || layer == Constants.LAYER_LV1_TOP) {
            // on regarde si c'est une tile d'un pc
            for (Computer computer : computers) {

                int test = i%Constants.TIlE_PER_LAYER;

                int tileTop = (computer.getTilePosition() - 1 - Constants.HORIZONTAL_TILES ) %Constants.TIlE_PER_LAYER ;
                int tileMiddle = (computer.getTilePosition() - 1) % Constants.TIlE_PER_LAYER;
                int tileBottom = (computer.getTilePosition() - 1 + Constants.HORIZONTAL_TILES )%Constants.TIlE_PER_LAYER  ;



                if (  test==  tileMiddle||
                        test ==  tileBottom||
                        test ==   tileTop) {
                    lvlPc = computer.getLevel();
                }
            }
        }
        if (lvlPc == 1 ){listTile.get(i).render();}
        if (lvlPc == 2 ) {
            listTile.get(i + Constants.TIlE_PER_LAYER).render();
        }
        if (lvlPc == 3 ) {listTile.get(i + 2* (Constants.TIlE_PER_LAYER)).render();}

    }

    public void renderTileCoffeeMachine(int layer , int i){
        int lvlCoffeeMachine = -1;
        // si on travail sur un layer pouvant avoir un upgrade
        if (layer == Constants.LAYER_LV1_BOTTOM || layer == Constants.LAYER_LV1_TOP) {
            // on regarde si c'est une tile d'un pc
            for (CoffeeMachine coffeeMachine : coffeeMachines) {

                int test = i%Constants.TIlE_PER_LAYER;

                int tileTop = (coffeeMachine.getTilePosition() - 1 - Constants.HORIZONTAL_TILES ) %Constants.TIlE_PER_LAYER ;
                int tileMiddle = (coffeeMachine.getTilePosition() - 1) % Constants.TIlE_PER_LAYER;
                int tileBottom = (coffeeMachine.getTilePosition() - 1 + Constants.HORIZONTAL_TILES )%Constants.TIlE_PER_LAYER  ;

                if (  test==  tileMiddle||
                        test ==  tileBottom||
                        test ==   tileTop) {
                    lvlCoffeeMachine = coffeeMachine.getLevel();
                }
            }
        }
        if (lvlCoffeeMachine == 0 ){listTile.get(i).render();}
        if (lvlCoffeeMachine == 1 ) {
            listTile.get(i + Constants.TIlE_PER_LAYER).render();
        }
    }


    public void renderTileSofa(int layer , int i){
        int lvlSofa = -1;
        // si on travail sur un layer pouvant avoir un upgrade
        if (layer == Constants.LAYER_LV1_BOTTOM || layer == Constants.LAYER_LV1_TOP) {
            // on regarde si c'est une tile d'un pc
            for (Sofa sofa : sofas) {

                int test = i%Constants.TIlE_PER_LAYER;

                int tile_1_1 =(sofa.getTilePosition() - 1) % Constants.TIlE_PER_LAYER;
                int tile_2_1 = tile_1_1 + 1;
                int tile_3_1 = tile_2_1 + 1;
                int tile_1_2 =(sofa.getTilePosition() - 1 - Constants.HORIZONTAL_TILES ) %Constants.TIlE_PER_LAYER;
                int tile_2_2 = tile_1_2 + 1;
                int tile_3_2 = tile_2_2 + 1;

                if (  test==  tile_1_1||
                            test==  tile_2_1||
                            test==  tile_3_1||
                            test==  tile_1_2||
                            test==  tile_2_2||
                            test==  tile_3_2  ){
                    lvlSofa= sofa.getLevel();
                }
            }
        }

        if (lvlSofa == 1 ){listTile.get(i).render();}
        if (lvlSofa == 2 ) {
            listTile.get(i + Constants.TIlE_PER_LAYER).render();
        }
        if (lvlSofa == 3 ) {
            listTile.get(i + 2* Constants.TIlE_PER_LAYER).render();
        }
    }




    //----------------- selection --------------
    public void teacherSelection(Vector2<Float> mouseClickPosition){
        // pour tout les profs
        for (Teacher teacher : teachers ){
            // si on click sur un prof
            if (Game.getDistanceBetween(mouseClickPosition,teacher.getHitBox()) < Constants.CLICK_DISTANCE_FROM_TEACHER ){
                if (teacherSelected != null){ // si un prof a deja été selectionné
                    // on regarde lequel des deux est le plus proche de la souris
                    if (Game.getDistanceBetween(mouseClickPosition, teacher.getHitBox() ) <
                            Game.getDistanceBetween(mouseClickPosition, teacherSelected.getHitBox())){

                        teacherSelected = teacher;
                    }
                }else {
                    teacherSelected = teacher;
                }
            }
        }
        if (teacherSelected != null){
            teacherSelected.setSelected(true);
            teacherSelected.resetMoveTo();
            teacherSelected.setHasAGoal(false);

        }

    }



    public void computerSelection(Vector2<Float> mouseClickPosition){
        // pour tout les pc
        for (Computer computer : computers ){
            // si on click sur un pc
            if (Game.getDistanceBetween(mouseClickPosition,computer.getPosition()) < Constants.CLICK_DISTANCE_FROM_COMPUTER
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
            teacherSelected.setGoalPoint(new Vector2<Integer>(computerSelected.getTeacherChair().getCurrentTile().getX()-1,computerSelected.getTeacherChair().getCurrentTile().getY()));

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
            if (computerSelected.getRegistration()!=null){
                computerSelected.getRegistration().setTeacher(teacherSelected);
            }
            teacherSelected = null;
            computerSelected = null;
        }
    }

    public void computerLevelUp(Vector2<Float> mouseClickPosition){
        for (Computer computer : computers ){
            // si on click sur un pc
            if (Game.getDistanceBetween(mouseClickPosition,computer.getPosition()) < Constants.CLICK_DISTANCE_FROM_COMPUTER ){
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



    public void coffeeMachineSelection(Vector2<Float> mouseClickPosition) {

        for (CoffeeMachine coffeeMachine : coffeeMachines) {
            if (Game.getDistanceBetween(mouseClickPosition, coffeeMachine.getHitBox() ) < Constants.CLICK_DISTANCE_FROM_COFFEE_MACHINE
                    && coffeeMachine.getLevel() > 0 && coffeeMachine.isAvailable()) {
                if (coffeeMachineSelected != null) { // si un pc a deja été selectionné
                    // on regarde lequel des deux est le plus proche de la souris
                    if (Game.getDistanceBetween(mouseClickPosition, coffeeMachine.getHitBox()) <
                            Game.getDistanceBetween(mouseClickPosition, coffeeMachineSelected.getHitBox())) {

                        coffeeMachineSelected = coffeeMachine;
                    }
                } else {
                    coffeeMachineSelected = coffeeMachine;
                }
            }
        }

        if (coffeeMachineSelected != null) {  // si on a selectionné un ordi
            teacherSelected.setHasAGoal(true);
            teacherSelected.setSelected(false);
            teacherSelected.setGoalPoint(new Vector2<Integer>(coffeeMachineSelected.getCurrentTile().getX(), coffeeMachineSelected.getCurrentTile().getY() + 2));
            teacherSelected.setMoveToCoffee(true);

            coffeeMachineSelected.setAvailable(false);
            teacherSelected.setCoffeeMachine(coffeeMachineSelected);

            teacherSelected = null;
            coffeeMachineSelected = null;
        }
    }

    public void coffeeMachineLevelUp(Vector2<Float> mouseClickPosition){
        for (CoffeeMachine coffeeMachine : coffeeMachines ){
            // si on click sur un pc
            if (Game.getDistanceBetween(mouseClickPosition,coffeeMachine.getHitBox()) < Constants.CLICK_DISTANCE_FROM_COFFEE_MACHINE ){
                if (coffeeMachineSelected != null){ // si un pc a deja été selectionné
                    // on regarde lequel des deux est le plus proche de la souris
                    if (Game.getDistanceBetween(mouseClickPosition, coffeeMachine.getHitBox() ) <
                            Game.getDistanceBetween(mouseClickPosition, coffeeMachineSelected.getHitBox())){
                        coffeeMachineSelected = coffeeMachine;
                    }
                }else {
                    coffeeMachineSelected = coffeeMachine;
                }
            }
        }
        if (coffeeMachineSelected != null){  // si on a selectionné un ordi
            coffeeMachineSelected.levelUp();
            coffeeMachineSelected=null;
        }
    }



    //-----------------------------Sofa----------------------------------------

    public void SofaSelection(Vector2<Float> mouseClickPosition) {

        for (Sofa sofa : sofas) {
            if (Game.getDistanceBetween(mouseClickPosition, sofa.getHitBox() ) < Constants.CLICK_DISTANCE_FROM_COFFEE_MACHINE
                     && sofa.hasFreePlace()) {
                if (sofaSelected != null) { // si un pc a deja été selectionné
                    // on regarde lequel des deux est le plus proche de la souris
                    if (Game.getDistanceBetween(mouseClickPosition, sofa.getHitBox()) <
                            Game.getDistanceBetween(mouseClickPosition, sofaSelected.getHitBox())) {

                        sofaSelected = sofa;
                    }
                } else {
                    sofaSelected = sofa;
                }
            }
        }

        if (sofaSelected != null) {  // si on a selectionné un ordi
            teacherSelected.setHasAGoal(true);
            teacherSelected.setSelected(false);
            teacherSelected.setGoalPoint(sofaSelected.getPositionFirtFreePlace());
            //teacherSelected.setGoalPoint(new Vector2<Integer>(sofaSelected.getCurrentTile().getX(), sofaSelected.getCurrentTile().getY() + 1));
            teacherSelected.setMoveToSofa(true);
            teacherSelected.setPositionOnSofa(sofaSelected.getFirstFreePlace());

            sofaSelected.addTeacherOnSofa();
            teacherSelected.setSofa(sofaSelected);
            sofaSelected=null;
            teacherSelected = null;
        }
    }

    public void sofaLevelUp(Vector2<Float> mouseClickPosition){
        for (Sofa sofa : sofas ){
            // si on click sur un pc
            if (Game.getDistanceBetween(mouseClickPosition,sofa.getHitBox()) < Constants.CLICK_DISTANCE_FROM_COFFEE_MACHINE ){
                if (sofaSelected != null){ // si un pc a deja été selectionné
                    // on regarde lequel des deux est le plus proche de la souris
                    if (Game.getDistanceBetween(mouseClickPosition, sofa.getHitBox() ) <
                            Game.getDistanceBetween(mouseClickPosition, coffeeMachineSelected.getHitBox())){
                        sofaSelected = sofa;
                    }
                }else {
                    sofaSelected = sofa;
                }
            }
        }
        if (sofaSelected != null){  // si on a selectionné un ordi
            sofaSelected.levelUp();
            sofaSelected=null;
        }
    }



    public boolean isOver(){
        return isOver = isOver || gameTimer.isOver() || (studentLeftToRegister==0);
    }

    public boolean isLevelWon() {
        return isLevelWon = isLevelWon || (isOver() & (studentLeftToRegister==0));
    }

    public boolean isGameWon(){
        return isGameWon = isLevelWon() & (Constants.STUDENTS_LVL.size()<=(level+1));
    }
    public void lvlUp(){
        isOver=false;
        isLevelWon=false;
        studentToRegister = Constants.STUDENTS_LVL.get(level+1);
        studentWaiting = studentToRegister;
        studentLeftToRegister = studentToRegister;
        level++;
        gameTimer = new GameTimer();
    }

    //-------------Evènements Aléatoires-----------
    public void restartRegistration(){          //0 : Registration repart à 0
        for(Computer computer : computers){
            if (computer.getRegistration() != null) computer.restartRegistration();
        }
    }
    public void computerLvlDown(){//1 : Le lvl d'un pc diminue de 1
        List<Computer> list = new ArrayList<>(computers);
        while (true){
            int random = rand.nextInt((list.size()-1));
            if (list.get(random).getLevel()>1){
                list.get(random).levelDown();
                break;
            } else {
                list.remove(random);
                if ((list.size()-2)==0) break;
            }
        }
    }

    public void randomEvent(){                  //Choix de l'évènement
        int a = rand.nextInt(2);
        switch (a){
            case 0 : computerLvlDown();
            break;
            case 1 : restartRegistration();
            break;
            default: break;
        }

    }
}

package main.actor.dynamicactor;

import main.actor.staticactor.Chair;
import main.actor.staticactor.Computer;
import main.maps.TiledMap;
import main.math.Vector2;
import main.actor.Actor;
import main.graphics.Renderer;
import main.graphics.Texture;
import main.utiles.Animation;
import main.utiles.Constants;

// les personnages du jeux ( etudiant + prof )
public abstract class Character extends Actor {

    protected float speed = Constants.CHARACTER_SPEED;       // le vitesse de deplacement

    protected TiledMap map;
    protected int characterSize = Constants.CHARACTER_SIZE;   // taille d'un personnage

    protected Vector2<Integer> goalPoint;       // position ou il doit se rendre
    protected boolean hasAGoal = false; // Si il doit aller queqlue part
    protected boolean isSit = false;    // Si il est assis
    protected boolean isOnTile = true; // Si il est pas entre deux tiles


    int dir = 0 ;               // La ou il regarde ( devant, derière, gauche ou droite)
    Animation animation;        // Animation de marche

    Computer computer = null;
    Chair chair = null;        // chaise ou il va s'asseoir

    //----------------------------------

    boolean canMoveLeft;
    boolean canMoveRight;
    boolean canMoveDown;
    boolean canMoveUp;

    boolean needToMoveToX = true;



    public Character(int x , int y, TiledMap map){
        super(x,y);
        this.map = map;
        texture = Texture.character;
        animation = new Animation(4 , 5 , true);
    }

    public void moveUp(){
        this.position.setY(this.position.getY()-speed);
    }
    public void moveDown(){
        this.position.setY(this.position.getY()+speed);
    }
    public void moveLeft(){
        this.position.setX(this.position.getX()-speed);
    }
    public void moveRight(){
        this.position.setX(this.position.getX()+speed);
    }
    public void moveTileUp(){
        if (this.position.getY()==((this.currentTile.getY()-speed)*Constants.TILE_SIZE)){
            this.currentTile.setY(this.currentTile.getY()-1);
            this.isOnTile=true;
        } else {
            this.isOnTile=false;
            moveUp();
        }
    }
    public void moveTileDown(){
        if (this.position.getY()==((this.currentTile.getY()+speed)*Constants.TILE_SIZE)){
            this.currentTile.setY(this.currentTile.getY()+1);
            this.isOnTile=true;
        } else {
            this.isOnTile=false;
            moveDown();
        }
    }
    public void moveTileLeft(){
        if (this.position.getX()==((this.currentTile.getX()-speed)*Constants.TILE_SIZE)) {
            this.currentTile.setX(this.currentTile.getX() - 1);
            this.isOnTile=true;
        } else {
            this.isOnTile=false;
            moveLeft();
        }
    }
    public void moveTileRight(){
        if (this.position.getX()==((this.currentTile.getX()+speed)*Constants.TILE_SIZE)){
            this.currentTile.setX(this.currentTile.getX()+1);
            this.isOnTile=true;
        } else {
            this.isOnTile=false;
            moveRight();
        }
    }




    // Bouger sur l'axe X
    public void moveToX(int x, int y ){
        /*
        if(this.position.getX() < x ) {
            if (map.getLayer(6).getGid((this.currentTile.getX() + 1) * this.currentTile.getY()) == 0) {
                dir = 2;
                moveTileRight();
            } else {
                moveTileUp();
            }
        }
        if( x <  this.position.getX()) {

            canMoveLeft =map.getLayer(6).getGid((this.currentTile.getX() - 2) + (this.currentTile.getY()-1)*Constants.HORIZONTAL_TILES ) == 0;
            if (canMoveLeft) {
                dir = 1;
                moveTileLeft();
            } else {
                dir = 3;
                moveTileUp();
            }

        }
*/


        x = x*Constants.TILE_SIZE;
        y = y*Constants.TILE_SIZE;

        canMoveLeft =map.getLayer(6).getGid((this.currentTile.getX() - 2) + (this.currentTile.getY()-1)*Constants.HORIZONTAL_TILES ) == 0;
        canMoveRight= map.getLayer(6).getGid((this.currentTile.getX()) + (this.currentTile.getY()-1)*Constants.HORIZONTAL_TILES ) == 0;;
        canMoveDown= map.getLayer(6).getGid((this.currentTile.getX() - 1) + (this.currentTile.getY())*Constants.HORIZONTAL_TILES ) == 0;;
        canMoveUp= map.getLayer(6).getGid((this.currentTile.getX() - 1) + (this.currentTile.getY()-2)*Constants.HORIZONTAL_TILES ) == 0; ;

        if(this.position.getX() < x ){
            if(canMoveRight){
                dir = 2;
                moveTileRight();
            } else {
                dir =3;
                moveTileUp();
            }
//            else if (this.position.getY() < y ){
//                if(canMoveDown){
//                    dir = 0;
//                    moveTileDown();
//                } else if(canMoveUp){
//                    dir =3;
//                    moveTileUp();
//                }
//            } else if(canMoveUp){
//                dir =3;
//                moveTileUp();
//            }
        }else if ( x <this.position.getX()){
            if(canMoveLeft){
                dir = 1;
                moveTileLeft();
            }else {
                dir =3;
                moveTileUp();
            }

            /*else if (this.position.getY() < y ){
                if(canMoveDown){
                    dir =0;
                    moveTileDown();
                } else if(canMoveUp){
                    dir =3;
                    moveTileUp();
                }
            } else if(canMoveUp){
                dir =3;
                moveTileUp();
            }*/
        }
    }

    // Bouger sur l'axe Y
    public void moveToY(int y, int x ){
        canMoveLeft =map.getLayer(6).getGid((this.currentTile.getX() - 2) + (this.currentTile.getY()-1)*Constants.HORIZONTAL_TILES ) == 0;
        canMoveRight= map.getLayer(6).getGid((this.currentTile.getX()) + (this.currentTile.getY()-1)*Constants.HORIZONTAL_TILES ) == 0;;
        canMoveDown= map.getLayer(6).getGid((this.currentTile.getX() - 1) + (this.currentTile.getY())*Constants.HORIZONTAL_TILES ) == 0;;
        canMoveUp= map.getLayer(6).getGid((this.currentTile.getX() - 1) + (this.currentTile.getY()-2)*Constants.HORIZONTAL_TILES ) == 0; ;

        x = x*Constants.TILE_SIZE;
        y = y*Constants.TILE_SIZE;
        if(this.position.getY() < y ){
            if(canMoveDown){
                dir = 0;
                moveTileDown();

            } else if (this.position.getX() < x ){
                if(canMoveRight){
                    dir = 2;
                    moveTileRight();
                } else if(canMoveLeft){
                    dir =1;
                    moveTileLeft();
                }
            } else if(canMoveLeft){
                dir =1;
                moveTileLeft();
            }
        }else if (this.position.getY() > y){
            if(canMoveUp){
                dir = 3;
                moveTileUp();
            } else if (this.position.getX() < x ){
                if(canMoveRight){
                    dir =2;
                    moveTileRight();
                } else if(canMoveLeft){
                    dir =1;
                    moveTileLeft();
                }
            } else if(canMoveLeft){
                dir =1;
                moveTileLeft();
            }
        }
    }


    public void moveToGoalXY(){
        animation.update();
        animation.play();
        //System.out.println("Je suis le personnage " + id + " ( " + this.x + ", " + this.y + ")");
        //System.out.println("Je dois aller à "+ " ( " + goalPoint.getX() + ", " + goalPoint.getY() + ")");

        // On va d'abord en x


        moveToX(goalPoint.getX(),goalPoint.getY());

        if (this.position.getX() == goalPoint.getX()*Constants.TILE_SIZE){
            //hasAGoal=false;
            // puis en  y
            moveToY(goalPoint.getY(),goalPoint.getX());

            if (this.position.getY() == goalPoint.getY()*Constants.TILE_SIZE){
                hasAGoal = false;        // il est arrivé
                System.out.println("Arrivé");
                animation.pause();
            }
        }



//        if (needToMoveToX){
//            moveToX(goalPoint.getX(),goalPoint.getY());
//            if (this.position.getX() == goalPoint.getX()*Constants.TILE_SIZE) {
//                needToMoveToX = !needToMoveToX;
//            }
//
//        }else {
//            moveToY(goalPoint.getY(), goalPoint.getX());
//            if (this.position.getY() == goalPoint.getY() * Constants.TILE_SIZE) {
//                needToMoveToX = !needToMoveToX;
//            }
//
//        }
//
//        if ( this.position.getX() == goalPoint.getX()*Constants.TILE_SIZE &&
//                this.position.getY() == goalPoint.getY() * Constants.TILE_SIZE){
//            hasAGoal = false;
//        }
    }


    //------------------ Protected Methodes ------------------

    protected void goalManagement(){
        if (hasAGoal){
            moveToGoalXY();
        }
    }


    protected void renderCharacter(float[] color){
        texture.bind();
        Renderer.renderActor(this.position.getX() - characterSize /2,this.position.getY()- characterSize /2, characterSize, characterSize, color, 4.0f , animation.getCurrentFrame(), dir);
        texture.unbind();
    }

//    protected void keyManagement(){
//        if (Component.input.isKeyDown(GLFW_KEY_Z ) || Component.input.isKeyDown(GLFW_KEY_W) ){
//            dir = 3 ;
//            y--;
//            //System.out.println("Il avance on dirait");
//            animation.play();
//            animation.update();
//        }if (Component.input.isKeyDown(GLFW_KEY_S )){
//            dir = 0 ;
//            y++;
//            animation.play();
//            animation.update();
//
//        }if (Component.input.isKeyDown(GLFW_KEY_Q ) || Component.input.isKeyDown(GLFW_KEY_A) ){
//            dir = 1;
//            x--;
//            animation.play();
//            animation.update();
//
//        }if (Component.input.isKeyDown(GLFW_KEY_D )){
//            dir = 2;
//            x++;
//            animation.play();
//            animation.update();
//
//        }
//    }

    //-------------------------


    public Vector2<Integer> getGoalPoint() {
        return goalPoint;
    }

    public void setGoalPoint(Vector2<Integer> goalPoint) {
        this.goalPoint = goalPoint;
    }

    public boolean isHasAGoal() {
        return hasAGoal;
    }

    public void setHasAGoal(boolean hasAGoal) {
        this.hasAGoal = hasAGoal;
    }

    public Chair getChair() {
        return chair;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }

    public boolean isSit() {
        return isSit;
    }

    public void setSit(boolean sit) {
        isSit = sit;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}

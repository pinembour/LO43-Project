package main.actor.dynamicactor;

import main.Component;
import main.actor.staticactor.Chair;
import main.actor.staticactor.Computer;
import main.math.Vector2f;
import main.actor.Actor;
import main.game.Game;
import main.graphics.Renderer;
import main.graphics.Texture;
import main.utiles.Animation;

import static org.lwjgl.glfw.GLFW.*;

// les personnages du jeux ( etudiant + prof )
public abstract class Character  extends Actor {

    protected float speed = 0.5f;       // le vitesse de deplacement

    protected int characterSize = 16;   // taille d'un personnage

    protected Vector2f goalPoint;       // position ou il doit se rendre
    protected boolean hasAGoal = false; // Si il doit aller queqlue part
    protected boolean isSit = false;    // Si il est assis


    int dir = 0 ;               // La ou il regarde ( devant, derière, gauche ou droite)
    Animation animation;        // Animation de marche

    Computer computer = null;
    Chair chair = null;        // chaise ou il va s'asseoir

    public Character(int x , int y ){
        super(x,y);
        texture = Texture.character;
        animation = new Animation(4 , 5 , true);
    }

    // Bouger sur l'axe X
    public void moveToX(float x ){
        if(this.x < x ){
            dir = 2;
            this.x+=speed;
        }else if (this.x > x){
            dir = 1;
            this.x-=speed;
        }
    }

    // Bouger sur l'axe Y
    public void moveToY(float y ){
        if(this.y < y ){
            dir = 0;
            this.y+=speed;
        }else if (this.y > y){
            dir = 3;
            this.y-=speed;
        }
    }


    public void moveToGoalXY(){
        animation.update();
        animation.play();
        //System.out.println("Je suis le personnage " + id + " ( " + this.x + ", " + this.y + ")");
        //System.out.println("Je dois aller à "+ " ( " + goalPoint.getX() + ", " + goalPoint.getY() + ")");

        // On va d'abord en x
        moveToX(goalPoint.getX());

        if ( this.x == goalPoint.getX()){
            // puis en  y
            moveToY(goalPoint.getY());

            if ( this.y == goalPoint.getY()){
                hasAGoal =false;        // il est arrivé
                System.out.println("Arrivé");
                animation.pause();
            }
        }
    }


    //------------------ Protected Methodes ------------------

    protected void goalManagement(){
        if (hasAGoal){
            moveToGoalXY();
        }
    }


    protected void renderCharacter(float[] color){
        texture.bind();
        Renderer.renderActor(x - characterSize /2,y- characterSize /2, characterSize, characterSize, color, 4.0f , animation.getCurrentFrame(), dir);
        texture.unbind();
    }

    protected void keyManagement(){
        if (Component.input.isKeyDown(GLFW_KEY_Z ) || Component.input.isKeyDown(GLFW_KEY_W) ){
            dir = 3 ;
            y--;
            //System.out.println("Il avance on dirait");
            animation.play();
            animation.update();
        }if (Component.input.isKeyDown(GLFW_KEY_S )){
            dir = 0 ;
            y++;
            animation.play();
            animation.update();

        }if (Component.input.isKeyDown(GLFW_KEY_Q ) || Component.input.isKeyDown(GLFW_KEY_A) ){
            dir = 1;
            x--;
            animation.play();
            animation.update();

        }if (Component.input.isKeyDown(GLFW_KEY_D )){
            dir = 2;
            x++;
            animation.play();
            animation.update();

        }
    }

    //-------------------------


    public Vector2f getGoalPoint() {
        return goalPoint;
    }

    public void setGoalPoint(Vector2f goalPoint) {
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

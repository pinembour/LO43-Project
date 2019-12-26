package main.actor.dynamicactor;

import main.Component;
import main.math.Vector2f;
import main.actor.Actor;
import main.game.Game;
import main.graphics.Renderer;
import main.graphics.Texture;
import main.utiles.Animation;

import static org.lwjgl.glfw.GLFW.*;




public abstract class Character  extends Actor {

    protected int id;
    protected float speed = 0.5f;

    protected int characterSize = 16;

    protected Vector2f goalPoint;
    protected boolean hasAGoal = false;
    protected boolean isSit = false;


    int dir = 0 ;               // la ou il va
    Animation animation;

    public Character(int x , int y ){
        super(x,y);
        texture = Texture.character;
        animation = new Animation(4 , 5 , true);
    }




    public void moveToX(float x ){
        if(this.x < x ){
            dir = 2;
            this.x+=speed;
        }else if (this.x > x){
            dir = 1;
            this.x-=speed;
        }
    }

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
                hasAGoal =false;
                System.out.println("Arrivé");
                animation.pause();
            }
        }
    }


    public float getDistanceFromMouse(){
        return (float) Math.sqrt(Math.pow(Game.getMouseX() - this.x , 2) + Math.pow(Game.getMouseY() - this.y , 2));
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
}

package main.actor.dynamicactor;

import main.actor.Actor;
import main.game.Game;
import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;
import main.utiles.Animation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

public abstract class Character  extends Actor {

    protected int id;

    protected Vector2f goalPoint;
    protected Vector2f mouseDirection;
    protected boolean isSelected = false;
    protected boolean hasAGoal = false;

    protected boolean mouseButton1 = false;



    int dir = 0 ;
    Animation animation;

    public Character(int x , int y , int id){
        super(x,y);
        this.id = id;
        texture = Texture.character;
        animation = new Animation(4 , 5 , true);
    }



    public void update(){
        animation.update();
        //animation.pause();
        if (Keyboard.isKeyDown(Keyboard.KEY_Z)){
            dir = 3 ;
            y--;
            System.out.println("Il avance");
            animation.play();
        }if (Keyboard.isKeyDown(Keyboard.KEY_S)){
            dir = 0 ;
            y++;
            animation.play();
        }if (Keyboard.isKeyDown(Keyboard.KEY_Q)){
            dir = 1;
            x--;
            animation.play();
        }if (Keyboard.isKeyDown(Keyboard.KEY_D)){
            dir = 2;
            x++;
            animation.play();
        }


        // gestion du click

        if (Mouse.isButtonDown(0) && !mouseButton1){
            if (isSelected){
                hasAGoal = true;
                isSelected = false;
                goalPoint = new Vector2f(Game.getMouseX(), Game.getMouseY());
            }else{
                if (getDistanceFromMouse() < 5 ){
                    isSelected = true;
                }
            }
        }
        mouseButton1 = Mouse.isButtonDown(0);

        //--

        if (hasAGoal){
            moveToGoalXY();
        }

    }

    public void render(){
        if (isSelected){
            texture.bind();
            Renderer.renderActor(x - 8,y-8,16,16, Color.RED, 4.0f , animation.getCurrentFrame(), dir);
            texture.unbind();
        }else {
            texture.bind();
            Renderer.renderActor(x - 8,y-8,16,16, Color.WHITE, 4.0f , animation.getCurrentFrame(), dir);
            texture.unbind();
        }



    }

    public void moveToX(float x ){
        if(this.x < x ){
            dir = 2;
            this.x++;
        }else if (this.x > x){
            dir = 1;
            this.x--;
        }
    }

    public void moveToY(float y ){
        if(this.y < y ){
            dir = 0;
            this.y++;
        }else if (this.y > y){
            dir = 3;
            this.y--;
        }
    }

    public void moveToGoalXY(){
        animation.update();
        animation.play();
        System.out.println("Je suis le personnage " + id + " ( " + this.x + ", " + this.y + ")");
        System.out.println("Je dois aller à "+ " ( " + goalPoint.x + ", " + goalPoint.y + ")");
        //this.x = x;
        //this.y = y;

        // On va d'abord en x
        moveToX(goalPoint.x);

        if ( this.x == goalPoint.x){
            // puis en  y
            moveToY(goalPoint.y);

            if ( this.y == goalPoint.y){
                hasAGoal =false;
                isSelected = false;
                System.out.println("Arrivé");
                animation.pause();

            }
        }
    }


    public float getDistanceFromMouse(){
        return (float) Math.sqrt(Math.pow(Game.getMouseX() - this.x , 2) + Math.pow(Game.getMouseY() - this.y , 2));
    }


}

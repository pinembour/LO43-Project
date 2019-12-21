package main.actor.dynamicactor;

import main.actor.Actor;
import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;
import main.utiles.Animation;
import org.lwjgl.input.Keyboard;

public abstract class Character  extends Actor {
    protected int id;

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

    }

    public void render(){
        texture.bind();
        Renderer.renderActor(x,y,16,16, Color.RED, 4.0f , animation.getCurrentFrame(), dir);
        texture.unbind();


    }
}

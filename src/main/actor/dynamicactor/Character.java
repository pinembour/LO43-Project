package main.actor.dynamicactor;

import main.actor.Actor;
import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;
import org.lwjgl.input.Keyboard;

public abstract class Character  extends Actor {
    protected int id;

    public Character(int x , int y , int id){
        super(x,y);
        this.id = id;
        texture = Texture.character;
    }



    public void update(){
        if (Keyboard.isKeyDown(Keyboard.KEY_Z)){
            y--;
            System.out.println("Il avance");
        }if (Keyboard.isKeyDown(Keyboard.KEY_S)){
            y++;
        }if (Keyboard.isKeyDown(Keyboard.KEY_Q)){
            x--;
        }if (Keyboard.isKeyDown(Keyboard.KEY_D)){
            x++;
        }
    }

    public void render(){
        texture.bind();
        Renderer.renderActor(x,y,16,16, Color.RED, 4.0f , 0 , 0);
        texture.unbind();


    }
}

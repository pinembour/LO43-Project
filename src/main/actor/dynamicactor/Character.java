package main.actor.dynamicactor;

import main.actor.Actor;
import main.graphics.Renderer;
import main.graphics.Texture;

public abstract class Character  extends Actor {
    protected int id;

    public Character(int x , int y , int id){
        super(x,y);
        this.id = id;
        texture = Texture.character;
    }



    public void update(){

    }

    public void render(){
        texture.bind();
        Renderer.renderActor(x,y,16,16, new float[]{1,1,1,1}, 4.0f , 0 , 0);
        texture.unbind();


    }
}

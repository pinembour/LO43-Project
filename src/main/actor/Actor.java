package main.actor;

import main.graphics.Texture;
import main.math.Vector2f;

public abstract class Actor {
    protected float x,y;                    //position dans l'espace
    protected boolean removed = false;      // si il est retiré du jeu
    protected Texture texture;              // la texture de l'acteur


    public Actor(int x , int y){
        this.x = x;
        this.y = y;
    }

    public abstract void update();
    public abstract void render();

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public Vector2f getPosition(){
        return new Vector2f(x,y);
    }

    public boolean getRemoved(){
        return removed;
    }

}

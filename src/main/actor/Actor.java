package main.actor;

import main.graphics.Texture;

public abstract class Actor {
    protected float x,y;
    protected boolean removed = false;
    protected Texture texture;


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

    public boolean getRemoved(){
        return removed;
    }

}

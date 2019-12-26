package main.actor.staticactor;

import main.actor.Actor;

public abstract class Object extends Actor {

    protected int level = 0;
    protected int size;

    public Object(int x , int y){
        super(x,y);
    }

    public void update() {

    }

    public void render() {

    }


    public abstract void levelUp();

}

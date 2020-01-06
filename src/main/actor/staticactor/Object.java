package main.actor.staticactor;

import main.actor.Actor;
import main.utiles.Constants;

public abstract class Object extends Actor {

    protected int level = 0;
    protected int levelMax;
    protected int size;

    public Object(int x , int y , int level){
        super(x,y);
        this.level=level;
    }

    public void update() {

    }

    public void render() {

    }


    public abstract void levelUp();


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

package main.actor.staticactor;

import main.actor.Actor;

public abstract class Object extends Actor {

    protected int level = 0;
    protected int levelMax;
    protected int size;
    protected int tilePosition;

    public Object(int x , int y , int level, int tilePosition){
        super(x,y);
        this.level=level;
        this.tilePosition =  tilePosition;
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

    public int getTilePosition() {
        return tilePosition;
    }

}

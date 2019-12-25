package main.actor.staticactor;

import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;

public class Chair extends Object{

    private int size = 24;
    private boolean isFree = true;
    private boolean isReserved = false;
    private boolean isOccupied = false;
    private float[] color;

    public Chair(int x , int y){

        super(x,y);
        texture = Texture.chair;
    }

    public void levelUp() {

    }

    public void render(){
        if (isFree){color = Color.GREEN;}
        if (isReserved){color = Color.ORANGE;}
        if (isOccupied){color = Color.BLACK;}

        texture.bind();
        Renderer.renderActor(x -size/2 , y -size/2, size, size , color , 4 , 3 ,0);
        texture.unbind();
    }


    public void setFree(boolean free) {
        isFree = free;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isFree() {
        return isFree;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}

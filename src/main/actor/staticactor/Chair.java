package main.actor.staticactor;

import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;

public class Chair extends Object{

    private ChairState chairState = ChairState.FREE;
    private float[] color;
    private Computer computer;

    public Chair(int x , int y , Computer computer){

        super(x,y);
        this.computer = computer;
        texture = Texture.chair;
        size = 10;
    }

    public enum ChairState{
        FREE, RESERVED, OCCUPIED
    }

    public void levelUp() {

    }

    public void render(){
        if (chairState.equals(ChairState.FREE)){color = Color.GREEN;}
        if (chairState.equals(ChairState.RESERVED)){color = Color.ORANGE;}
        if (chairState.equals(ChairState.OCCUPIED)){color = Color.BLACK;}

        texture.bind();
        Renderer.renderActor(x -size/2 , y -size/2, size, size , color , 4 , 3 ,0);
        texture.unbind();
    }

    public ChairState getChairState() {
        return chairState;
    }

    public void setChairState(ChairState chairState) {
        this.chairState = chairState;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}

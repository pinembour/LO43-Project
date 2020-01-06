package main.actor.staticactor;

import main.graphics.Color;
import main.graphics.Renderer;

public class CoffeeMachine extends Object{

    public CoffeeMachine(int x, int y,int level){
        super(x,y,level);
    }

    public void levelUp() {

    }

    public  void update(){

    }

    public void render(){
        Renderer.renderRectangle(position.getX() , position.getY(), 10 , 10 , Color.BLACK);
    }

}

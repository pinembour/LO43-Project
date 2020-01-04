package main.actor;

import main.graphics.Texture;
import main.math.Vector2;
import main.utiles.Constants;

public abstract class Actor {
    protected Vector2<Float> position = new Vector2<Float>(0f,0f);  //position en pixel
    protected Vector2<Integer> currentTile = new Vector2<Integer>(0,0); //position sur la TileMap
    protected boolean removed = false;      // si il est retiré du jeu
    protected Texture texture;              // la texture de l'acteur


    public Actor(float x , float y){
        this.position.setX(x);
        this.position.setY(y);
        this.currentTile.setY((int) (this.position.getY()/ Constants.TILE_SIZE));
        this.currentTile.setX((int) (this.position.getX()/Constants.TILE_SIZE));

    }

    public abstract void update();
    public abstract void render();

    public Vector2<Integer> getCurrentTile() {
        return currentTile;
    }

    public float getX(){
        return this.position.getX();
    }

    public float getY(){
        return this.position.getY();
    }

    public Vector2 <Float> getPosition(){
        return position;
    }

    public boolean getRemoved(){
        return removed;
    }

}

package main.actor.staticactor;

import main.game.level.Level;
import main.graphics.Color;
import main.graphics.Renderer;
import main.math.Vector2;
import main.utiles.Constants;

public class Sofa extends Object{

    enum SofaState{
        FREE, OCCUPIED, LOCKED
    }

    SofaState [] sofaStates;

    public Sofa(int x, int y,int level, int tilePosition) {
        super(x, y, level, tilePosition);
        levelMax = 3;
        sofaStates = new SofaState[]{SofaState.FREE, SofaState.LOCKED,SofaState.LOCKED};
    }


    public void levelUp() {
        if (level < levelMax) {
            if (Level.player.getGold() >= (10)) {
                Level.player.removeGold(10);
                level++;
                sofaStates[level-1] = SofaState.FREE;
            } else {
                System.out.println("Pas assez de gold");
            }
        }

    }

    public Vector2<Float> getHitBox(){
        return new Vector2<Float>( getPosition().getX() + level*Constants.TILE_SIZE/2 ,
                getPosition().getY() + Constants.TILE_SIZE/2);
    }

    public void render(){

    }

    public void addTeacherOnSofa(){
        if (hasFreePlace()){
            int i = 0;
            while (sofaStates[i].equals(SofaState.OCCUPIED)){
                i++;
            }
            sofaStates[i] = SofaState.OCCUPIED;
        }
    }

    public void removeTeacherAt(int index){
        sofaStates[index] = SofaState.FREE;
    }

    public int getFirstFreePlace(){
        int i = 0;
        while (sofaStates[i].equals(SofaState.OCCUPIED)){
            i++;
        }
        return i;
    }

    public Vector2<Integer> getPositionFirtFreePlace(){
        int i = 0;
        while (sofaStates[i].equals(SofaState.OCCUPIED)){
            i++;
        }

        return new Vector2<Integer>( currentTile.getX()+i ,currentTile.getY()+1);
    }

    public boolean hasFreePlace(){
        boolean ret = false;
        for (int i = 0 ; i< levelMax ; i++){
            if (sofaStates[i].equals(SofaState.FREE)){
                ret = true;
            }
        }
        return ret;
    }
}

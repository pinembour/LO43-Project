package main.actor.dynamicactor;

import main.actor.staticactor.Chair;
import main.graphics.Renderer;
import main.maps.TiledMap;
import main.math.Vector2;
import main.graphics.Color;
import main.utiles.Constants;

public class Teacher extends Character {

    private static int nbProf = 0;
    private int backToSpawn;

    protected float tired = 100;
    protected float comfort = 100;

    protected boolean isSelected = false;

    public Teacher(int x , int y, TiledMap map){
        super(x,y,map);
        backToSpawn = nbProf;
        nbProf++;
    }


    public void update() {

        //keyManagement();        // touche de clavier

        if (!isSelected){
            goalManagement();
        }

        if (chair != null){ // si une chaise lui est désigné
            if (!hasAGoal && !isSelected){ // et qu'il n'a pas d'objectif
                setSit(true);
                position.setX(chair.getX());
            }

            if (isSit){
                chair.setChairState(Chair.ChairState.OCCUPIED);
            }else {
                chair.setChairState(Chair.ChairState.RESERVED);
            }
        }

        if (tired > 0 ){
            tired -= 0.02f;
        }

        if (isSit && comfort>0){
            comfort -= 0.02f;
        }

        clickPosition.setX(position.getX() + Constants.CHARACTER_SIZE/2);
        clickPosition.setY(position.getY() + Constants.CHARACTER_SIZE/2);

    }

    public void render(){
        if (isSelected){
            renderCharacter(Color.RED);
        }else {
            renderCharacter(Color.WHITE);
        }

        int widthbar = Constants.TEACHER_BAR_WIDTH;
        int heightBar = Constants.TEACHER_BAR_HEIGHT ;
        int yBar = Constants.TEACHER_BAR_POSITION ;


        Renderer.renderVerticalLoadingBar(this.position.getX()-widthbar/2 + characterSize/2, this.position.getY() - yBar + characterSize/2, widthbar , heightBar , Color.BLUE,tired);
        Renderer.renderVerticalLoadingBar(this.position.getX()-widthbar/2+characterSize/2, this.position.getY() - yBar - heightBar +characterSize/2, widthbar , heightBar , Color.RED,comfort);
    }

    //---------------------------------------

    public void backToSpawn(){
        hasAGoal = true;
        isSelected = false;
        setSit(false);
        position.setX(chair.getX()-Constants.TILE_SIZE);
        chair = null;
        computer = null;
        goalPoint = new Vector2<Integer>( Constants.TEACHER_BACK_TO_SPAWN_X + backToSpawn,  Constants.TEACHER_BACK_TO_SPAWN_Y);

    }

    //---------------------------------------

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}

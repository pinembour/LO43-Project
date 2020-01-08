package main.actor.dynamicactor;

import main.actor.staticactor.Chair;
import main.actor.staticactor.CoffeeMachine;
import main.actor.staticactor.Sofa;
import main.game.level.GameTimer;
import main.game.level.Registration;
import main.graphics.Renderer;
import main.graphics.Texture;
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

    protected boolean moveToCoffee = false;
    protected boolean moveToSofa = false;

    protected CoffeeMachine coffeeMachine;
    protected Sofa sofa;

    protected boolean isSitOnSofa = false ;
    protected int positionOnSofa = 0;


    protected int flashingColor = 0;
    protected int flashingSpeed = Constants.TEACHER_FLASHING_SPEED;

    public Teacher(int x , int y, TiledMap map, Fise fise){
        super(x,y,map, fise);
        texture = Texture.teacherTexture;

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
            tired -= Constants.TIRED_LOSE;
        }

        if (isSit && comfort>0){
            comfort -= Constants.COMFORT_LOSE;
        }

        if (isSitOnSofa && comfort<100){
            comfort += 0.02f;
        }

        if (!hasAGoal && moveToCoffee && coffeeMachine!=null){
            moveToCoffee = false;
            tired=100;

            coffeeMachine.setCoffeeTimer(new GameTimer()) ;
            coffeeMachine.getCoffeeTimer().setTimeLimit(Constants.COFFEE_TIME_TO_AVAILABLE);
            coffeeMachine = null;
            backToSpawn();
        }

        if (!hasAGoal && moveToSofa){
            moveToSofa = false;
            isSitOnSofa = true;
            dir = 0 ;
            position.setY(position.getY()-Constants.TILE_SIZE);
        }

        if (isSelected){
            flashingColor ++;
            if (flashingColor > 2 * flashingSpeed){
                flashingColor = 0;
            }
        }

    }

    public void render(){
        if (isSelected){
            if (flashingColor < flashingSpeed){
                renderCharacter(Color.RED);
            }else {
                renderCharacter(color);
            }
        }else {
            renderCharacter(color);
        }

        int widthbar = Constants.TEACHER_BAR_WIDTH;
        int heightBar = Constants.TEACHER_BAR_HEIGHT ;
        int yBar = Constants.TEACHER_BAR_POSITION ;


        Renderer.renderVerticalLoadingBar(this.position.getX()-widthbar/2 + characterSize/2, this.position.getY() - yBar + characterSize/2, widthbar , heightBar , Color.BLUE,tired);
        Renderer.renderVerticalLoadingBar(this.position.getX()-widthbar/2+characterSize/2, this.position.getY() - yBar - heightBar +characterSize/2, widthbar , heightBar , Color.RED,comfort);
    }

    //---------------------------------------

    public void resetMoveTo(){
        moveToCoffee = false;
        moveToSofa = false;
        if (chair!= null){
            chair.setChairState(Chair.ChairState.FREE);
            dir = Constants.LOOK_DOWN;
            chair=null;
        }
        if (coffeeMachine !=null){
            coffeeMachine.setAvailable(true);
            coffeeMachine = null;
        }
        if (sofa != null){
            sofa.removeTeacherAt(positionOnSofa);
            sofa = null;
        }
        if (isSit){
            position.setX(position.getX()-Constants.TILE_SIZE);
            isSit = false;
        }
        if (isSitOnSofa){
            position.setY(position.getY()+Constants.TILE_SIZE);
            dir = Constants.LOOK_LEFT;
            isSitOnSofa =false;
        }
        if (computer != null){
            if (computer.getRegistration() !=null){
                computer.getRegistration().setRegistrationState(Registration.RegistrationState.PAUSED);
                computer.getRegistration().setTeacher(null);
            }
            computer.setTeacher(null);
            computer = null;
        }


    }

    public void backToSpawn(){
        hasAGoal = true;
        isSelected = false;
        if (isSit){
            setSit(false);
            position.setX(chair.getX()-Constants.TILE_SIZE);
            chair = null;
            computer = null;
        }
        goalPoint = new Vector2<Integer>( Constants.TEACHER_BACK_TO_SPAWN_X + backToSpawn,  Constants.TEACHER_BACK_TO_SPAWN_Y);

    }

    //---------------------------------------

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public float getTired() {
        return tired;
    }

    public float getComfort() {
        return comfort;
    }

    public void setMoveToCoffee(boolean moveToCoffee) {
        this.moveToCoffee = moveToCoffee;
    }

    public void setMoveToSofa(boolean moveToSofa) {
        this.moveToSofa = moveToSofa;
    }

    public void setPositionOnSofa(int positionOnSofa) {
        this.positionOnSofa = positionOnSofa;
    }

    public CoffeeMachine getCoffeeMachine() {
        return coffeeMachine;
    }

    public void setCoffeeMachine(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }


    public Sofa getSofa() {
        return sofa;
    }

    public void setSofa(Sofa sofa) {
        this.sofa = sofa;
    }
}

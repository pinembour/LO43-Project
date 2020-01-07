package main.game.level;

import main.actor.dynamicactor.Student;
import main.actor.dynamicactor.Teacher;
import main.actor.staticactor.Computer;
import main.graphics.Color;
import main.graphics.Renderer;
import main.utiles.Constants;

public class Registration {


    private RegistrationState registrationState;

    private Teacher teacher;
    private Student student;
    private Computer computer;

    private boolean sameFise;

    private float evolution;

    private int width = Constants.REGISTRATION_BAR_WIDTH;
    private int height = Constants.REGISTRATION_BAR_HEIGHT;
    private int x;
    private int y;

    public enum RegistrationState {
        STARTED, ENDED, PAUSED
    }

    public Registration(Teacher teacher, Student student, Computer computer){
        this.teacher = teacher;
        this.student = student;
        this.computer =computer;
        this.x = (int) computer.getX() - width /2 ;
        this.y = (int) computer.getY() ;
    }

    public void start(){
        registrationState = RegistrationState.STARTED;
    }

    public void pause(){
        registrationState = RegistrationState.PAUSED;
    }


    public void restart(){
        evolution = 0f;

    }

    public void end(){

        registrationState = RegistrationState.ENDED;
        Level.player.addGold(10);

    }

    //------------------------------

    public void update(){
        if (registrationState.equals(RegistrationState.STARTED)){

            evolution+=0.3 + 0.1*( computer.getLevel() -1 ) ;
            if (sameFise){
                evolution+=0.3 + 0.1*( computer.getLevel() -1 ) ;

            }

            if (evolution > 100){
                evolution = 100;
                end();
            }

            if (teacher.getTired()<1){
                registrationState = RegistrationState.PAUSED;
            }
            if (teacher.getComfort()<1){
                registrationState = RegistrationState.PAUSED;
            }
        }

        if (teacher!=null && student!=null){
            if (teacher.getFise().equals(student.getFise())){
                sameFise = true;
            }else {
                sameFise= false;
            }
        }else {
            sameFise=false;
        }
    }

    public void render(){
        if (sameFise){
            Renderer.renderHorizontalLoadingBar(x + Constants.TILE_SIZE,y,width,height,Color.GOLD,evolution);

        }else {
            Renderer.renderHorizontalLoadingBar(x + Constants.TILE_SIZE,y,width,height,Color.BLUE,evolution);
        }
    }

    //-------------------------------


    public RegistrationState getRegistrationState() {
        return registrationState;
    }

    public void setRegistrationState(RegistrationState registrationState) {
        this.registrationState = registrationState;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public float getEvolution() {
        return evolution;
    }

    public void setEvolution(float evolution) {
        this.evolution = evolution;
    }


}

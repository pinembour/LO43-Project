package main.actor.staticactor;

import main.actor.dynamicactor.Student;
import main.actor.dynamicactor.Teacher;
import main.game.level.Level;
import main.game.level.Registration;
import main.game.level.tiles.Tile;
import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;
import main.maps.TileSet;
import main.utiles.Constants;

import java.util.List;

public class Computer extends Object{


    private Chair teacherChair;
    private Chair studentChair;

    private Student student =null;
    private Teacher teacher = null;

    private Registration registration = null;

    private int tilePosition;

    private static int nbPcUnlock = 0;
    private static int goldToUnlock = 10;

    boolean hadEvent = false;
    int frameShowEvent = 0;


    public Computer(int x , int y, int level, int tilePosition){

        super(x,y,level, tilePosition);
        this.levelMax = Constants.COMPUTER_LVL_MAX;
//        texture = Texture.computer;
        texture = Texture.pcTable;
        size = 10;
        int decalY = Constants.CHAIR_POSTION_PLUSY;
        int distanceComputer = Constants.CHAIR_DISTANCE_FROM_COMPUTER;
        int distanceChair = Constants.CHAIR_DISTANCE_EACH_OTHER;

        teacherChair = new Chair(x -distanceComputer,y+ decalY, this);
        studentChair = new Chair(x -distanceComputer,y + decalY + distanceChair, this);

        //this.tilePosition = tilePosition;

        if (level > 0){nbPcUnlock++;}
    }
    public void restartRegistration(){
        this.registration.restart();
        hadEvent = true;
    }

//    public void levelUp(){
//
//    }

    public void levelDown(){
        if (level>0) level--;
    }

    public void levelUp() {
        if (level == levelMax ){
            //System.out.println("Ce pc est déjà niveau max");
        }else {
            if (level == 0) {
                if (Level.player.getGold() >= (goldToUnlock + 5 * (nbPcUnlock - 1))) {
                    Level.player.removeGold(goldToUnlock + 5 * (nbPcUnlock - 1));
                    if (level == 0) {
                        nbPcUnlock++;
                    }
                    level++;
                   // System.out.println("Ce pc est désormait niveau : " + level);
                } else {
                    System.out.println("Pas assez de gold");
                }
            }else {
                if (Level.player.getGold() >= (10)) {
                    Level.player.removeGold(10);
                    level++;
                    System.out.println("Ce pc est désormait niveau : " + level);
                } else {
                    System.out.println("Pas assez de gold");
                }
            }

        }
    }



    public void update(){
        if (studentChair.getChairState().equals(Chair.ChairState.OCCUPIED)
                && teacherChair.getChairState().equals(Chair.ChairState.OCCUPIED)
                && registration == null
                && teacher.getTired()>0
                && teacher.getComfort()>0) {

            registration = new Registration( teacher,student,this);
            registration.start();
        }

        if (registration != null){
            registration.update();

            // Si l'inscription est fini
            if (registration.getRegistrationState().equals(Registration.RegistrationState.ENDED)){
                student.setRegistered(true);
                registration.setTeacher(null);
                registration.setStudent(null);
                student = null;
                studentChair.setChairState(Chair.ChairState.FREE);

                teacherChair.setChairState(Chair.ChairState.FREE);

                teacher.backToSpawn();
                teacher = null;

                registration = null;

            }

            if(studentChair.getChairState().equals(Chair.ChairState.OCCUPIED)
                    && teacherChair.getChairState().equals(Chair.ChairState.OCCUPIED)
                    && registration.getRegistrationState().equals(Registration.RegistrationState.PAUSED)
                    && teacher.getTired()>0
                    && teacher.getComfort()>0 ) {

                registration.setRegistrationState(Registration.RegistrationState.STARTED);
            }

        }


        if (frameShowEvent > 60){
            hadEvent = false;
            frameShowEvent = 0;
        }

    }



    public void render(){
        if (level>0){
            //Renderer.drawText("" +level, (int)(float)this.position.getX() + Constants.TILE_SIZE , (int)(float) this.position.getY() , Constants.HUD_FONT_SIZE , Color.BLACK);
        }else {
            Renderer.drawText( " " + (goldToUnlock + 5 * (nbPcUnlock-1) ), (int)(float) this.position.getX() + Constants.TILE_SIZE +2, (int)(float) this.position.getY() , Constants.HUD_FONT_SIZE , Color.BLACK);
            Renderer.drawText(" " + (goldToUnlock + 5 * (nbPcUnlock-1) ), (int)(float) this.position.getX() + Constants.TILE_SIZE ,(int)(float) this.position.getY() , Constants.HUD_FONT_SIZE , Color.YELLOW);
        }

        //Renderer.renderRectangle(this.position.getX()  , this.position.getY()  , 10 , 10 , Color.YELLOW);

        if (hadEvent){
            Renderer.renderRectangle(position.getX(), position.getY(),size, size, Color.RED);
            frameShowEvent++;
        }


        //teacherChair.render();
        //studentChair.render();
        if (registration!= null){
            registration.render();
        }
    }

    //--


    public Chair getTeacherChair() {
        return teacherChair;
    }

    public Chair getStudentChair() {
        return studentChair;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

}

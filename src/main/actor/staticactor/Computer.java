package main.actor.staticactor;

import main.actor.dynamicactor.Student;
import main.actor.dynamicactor.Teacher;
import main.game.level.Level;
import main.game.level.Registration;
import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;

public class Computer extends Object{


    private Chair teacherChair;
    private Chair studentChair;

    private Student student =null;
    private Teacher teacher = null;

    private Registration registration = null;


    public Computer(int x , int y, int level){

        super(x,y);
        this.level = level;
        texture = Texture.computer;
        size = 50;
        int distanceComputer = 20;
        int distanceChair = 6;
        teacherChair = new Chair(x -distanceComputer,y-distanceChair, this);
        studentChair = new Chair(x -distanceComputer,y+distanceChair, this);
    }

    public void levelUp() {
        if (level == 2 ){
            System.out.println("Ce pc est déjà niveau max");
        }else {
            if (Level.player.getGold() >= 10) {
                level++;
                Level.player.removeGold(10);
                System.out.println("Ce pc est désormait niveau : " + level);
            } else {
                System.out.println("Pas assez de gold");
            }
        }

    }

    public void update(){
        if (studentChair.getChairState().equals(Chair.ChairState.OCCUPIED)
                && teacherChair.getChairState().equals(Chair.ChairState.OCCUPIED) && registration == null) {

            registration = new Registration( teacher,student,this);
            registration.start();

            System.out.println("Etudiant inscrit");
        }


        if (registration != null){
            registration.update();

            if (registration.getRegistrationState().equals(Registration.RegistrationState.ENDED)){
                student.setRegistred(true);
                student = null;
                studentChair.setChairState(Chair.ChairState.FREE);
                registration = null;

            }

            if(studentChair.getChairState().equals(Chair.ChairState.OCCUPIED)
                    && teacherChair.getChairState().equals(Chair.ChairState.OCCUPIED)
                    && registration.getRegistrationState().equals(Registration.RegistrationState.PAUSED)) {
                registration.setRegistrationState(Registration.RegistrationState.STARTED);
            }

        }

    }



    public void render(){
        if (level>0){
            texture.bind();
            Renderer.renderActor(x -size/2 , y -size/2, size, size , Color.WHITE, 1, 0 ,0);
            texture.unbind();
        }else {
            texture.bind();
            Renderer.renderActor(x -size/2 , y -size/2, size, size , Color.GREY, 1, 0 ,0);
            texture.unbind();
        }

        teacherChair.render();
        studentChair.render();
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

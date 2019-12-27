package main.actor.staticactor;

import main.actor.dynamicactor.Student;
import main.actor.dynamicactor.Teacher;
import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;

public class Computer extends Object{


    private Chair teacherChair;
    private Chair studentChair;

    private Student student =null;
    private Teacher teacher = null;


    public Computer(int x , int y){

        super(x,y);
        texture = Texture.computer;
        size = 50;
        int distanceComputer = 20;
        int distanceChair = 6;
        teacherChair = new Chair(x -distanceComputer,y-distanceChair);
        studentChair = new Chair(x -distanceComputer,y+distanceChair);
    }

    public void levelUp() {

    }

    public void update(){
        if (studentChair.getChairState().equals(Chair.ChairState.OCCUPIED)
                && teacherChair.getChairState().equals(Chair.ChairState.OCCUPIED)){

            // debut inscription
            student.setRegistred(true);
            student = null;
            studentChair.setChairState(Chair.ChairState.FREE);

            System.out.println("Etudiant inscrit");
        }
    }

    public void render(){
        texture.bind();
        Renderer.renderActor(x -size/2 , y -size/2, size, size , Color.WHITE, 1, 0 ,0);
        texture.unbind();

        teacherChair.render();
        studentChair.render();
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
}

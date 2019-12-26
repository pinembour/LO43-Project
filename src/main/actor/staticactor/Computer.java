package main.actor.staticactor;

import main.graphics.Color;
import main.graphics.Renderer;
import main.graphics.Texture;

public class Computer extends Object{


    private Chair teacherChair;
    private Chair studentChair;

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
        if (studentChair.isOccupied() && teacherChair.isOccupied()){
            // commence registration
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
}

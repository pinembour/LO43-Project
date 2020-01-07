package main.game;

import main.Component;
import main.actor.dynamicactor.Student;
import main.game.level.Level;
import main.math.Vector2;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class Game {

    public Level getLevel() {
        return level;
    }

    Level level ;

    public static float xScroll , yScroll;

    public Game(){
        level = new Level();
    }

    public void init(){
        level.init();
    }

    public void translateView(float xa ,float ya){
        xScroll += xa;
        yScroll += ya;
    }

    public void update(){
        //translateView(-0.1f,-0.1f);
        level.update();
    }

    public void render(){
        GL11.glTranslatef(xScroll,yScroll,0);   //afficher le niveau en fonction du scroll
        level.render();
    }

    //--------------Mouse--------------------
    public static double getMouseX(){
        DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
        glfwGetCursorPos(Component.window, posX , null);
        return posX.get(0) ;//  / Component.scale;
    }

    public static double getMouseY(){
        DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
         glfwGetCursorPos(Component.window, null , posY);
        return posY.get(0) ;  //  / Component.scale;
    }

    public static float getDistanceFromMouse(Student student){
        return (float) Math.sqrt(Math.pow(getMouseX() - student.getX() , 2) + Math.pow(getMouseY() - student.getY() , 2));
    }

    public static float getDistanceBetween(Vector2<Float> u , Vector2<Float> v){
        return (float) Math.sqrt(Math.pow(u.getX() - v.getX() , 2) + Math.pow(u.getY() - v.getY() , 2));
    }
}

package main.game;

import main.Component;
import main.actor.Actor;
import main.game.level.Level;
import main.graphics.Renderer;
import main.math.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.Buffer;
import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class Game {

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

    public static float getDistanceFromMouse(Actor actor){
        return (float) Math.sqrt(Math.pow(getMouseX() - actor.getX() , 2) + Math.pow(getMouseY() - actor.getY() , 2));
    }

    public static float getDistanceBetween(Vector2f u , Vector2f v){
        return (float) Math.sqrt(Math.pow(u.getX() - v.getX() , 2) + Math.pow(u.getY() - v.getY() , 2));
    }
}

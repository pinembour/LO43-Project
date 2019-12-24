package main.game;

import main.Component;
import main.game.level.Level;
import main.graphics.Renderer;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

import java.nio.Buffer;
import java.nio.DoubleBuffer;

import static org.lwjgl.glfw.GLFW.*;

public class Game {

    //Level level ;

    public static float xScroll , yScroll;

    public Game(){
        //level = new Level(Component.width/16,Component.height/16);
    }

    public void init(){
        //level.init();
    }

    public void translateView(float xa ,float ya){
        xScroll += xa;
        yScroll += ya;
    }

    public void update(){
        //translateView(-0.1f,-0.1f);
        //level.update();
    }

    public void render(){
        GL11.glTranslatef(xScroll,yScroll,0);   //afficher le niveau en fonction du scroll
        //level.render();

    }

    //--------------Mouse--------------------
    public static double getMouseX(){
        DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
         glfwGetCursorPos(Component.window, posX , null); //Component.scale - xScroll;
        return posX.get(0);
    }

    public static double getMouseY(){
        DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
         glfwGetCursorPos(Component.window, null , posY); //Component.scale - xScroll;
        return posY.get(0);
    }

}

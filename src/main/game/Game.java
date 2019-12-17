package main.game;

import main.Component;
import main.game.level.Level;
import main.graphics.Renderer;
import org.lwjgl.opengl.GL11;

public class Game {

    Level level ;

    public static float xScroll , yScroll;

    public Game(){
        level = new Level(Component.width/16,Component.height/16);
    }

    public void init(){
        level.init();
    }

    public void translateView(float xa ,float ya){
        xScroll += xa;
        yScroll += ya;
    }

    public void update(){
        translateView(-0.1f,-0.1f);
        level.update();
    }

    public void render(){
        GL11.glTranslatef(xScroll,yScroll,0);
        level.render();

    }
}

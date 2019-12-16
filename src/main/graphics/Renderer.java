package main.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public static void quadData(int x, int y, int w, int h, float[] color ){
        glColor4f(color[0], color[1] , color[2] , color [3] );
        glVertex2f(x , y);
        glVertex2f(x + w , y);
        glVertex2f(x + w ,y + h);
        glVertex2f( x ,y + h);
    }

    public static void renderQuad(int x, int y, int w, int h, float[] color){
        glBegin(GL_QUADS);
            Renderer.quadData( x,  y,  w,  h, color);
        glEnd();
    }

    public static void lineData(int x1, int y1 , int x2 , int y2){
        glVertex2f( x1, y1 );
        glVertex2f( x2, y2 );
    }

    public static void renderLine(int x1, int y1 , int x2 , int y2){
        glBegin(GL_LINES);
            Renderer.lineData(x1, y1, x2, y2);
        glEnd();
    }

}

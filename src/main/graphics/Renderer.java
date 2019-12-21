package main.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    public static void quadData(int x, int y, int w, int h, float[] color, int xo , int yo ){
        float d = 16;
        float sizeX = d;
        float sizeY = d;
        glColor4f(color[0], color[1] , color[2] , color [3] );
        glTexCoord2d((0 + xo)/sizeX , (0 + yo)/sizeY );        glVertex2f(x , y);
        glTexCoord2d((1 + xo)/sizeX , (0 + yo)/sizeY );        glVertex2f(x + w , y);
        glTexCoord2d((1 + xo)/sizeX , (1 + yo)/sizeY );        glVertex2f(x + w ,y + h);
        glTexCoord2d((0 + xo)/sizeX , (1 + yo)/sizeY );        glVertex2f( x ,y + h);
    }

    public static void renderQuad(int x, int y, int w, int h, float[] color, int xo, int yo){
        glBegin(GL_QUADS);
            Renderer.quadData( x,  y,  w,  h, color, xo , yo );
        glEnd();
    }
    /*----------------------------------------------------------------------------------------*/
    public static void lineData(int x1, int y1 , int x2 , int y2){
        glVertex2f( x1, y1 );
        glVertex2f( x2, y2 );
    }

    public static void renderLine(int x1, int y1 , int x2 , int y2){
        glBegin(GL_LINES);
            Renderer.lineData(x1, y1, x2, y2);
        glEnd();
    }

    /*----------------------------------------------------------------------------------------*/

    /*
     * x , y position
     * w , h taille
     * size -> nombre de texture dans une ligne/colonne du sprite
     * x0 , yo position de la texture dans le sprite
     */
    public static void renderActor(float x, float y, int w, int h, float[] color, float size , int xo , int yo ){
        glBegin(GL_QUADS);
        glColor4f(color[0], color[1] , color[2] , color [3] );
        glTexCoord2d((0 + xo)/size , (0 + yo)/size );        glVertex2f(x , y);
        glTexCoord2d((1 + xo)/size , (0 + yo)/size );        glVertex2f(x + w , y);
        glTexCoord2d((1 + xo)/size , (1 + yo)/size );        glVertex2f(x + w ,y + h);
        glTexCoord2d((0 + xo)/size , (1 + yo)/size );        glVertex2f( x ,y + h);
        glEnd();
    }




}


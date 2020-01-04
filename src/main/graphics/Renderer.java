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


    public static void renderRectangle(float x, float y, int w, int h , float[] color){
        glBegin(GL_QUADS);
            glColor4f(color[0], color[1] , color[2] , color [3] );
            glVertex2f(x , y);
            glVertex2f(x + w , y);
            glVertex2f(x + w ,y + h);
            glVertex2f( x ,y + h);
        glEnd();
    }

    public static void renderVericalLoadingBar(float x, float y, int w, int h , float[] color, float evolution){

        Renderer.renderRectangle(x, y, w, h, Color.WHITE);
        Renderer.renderRectangle(x, y , (int)evolution * w / 100, h , color);
    }

    public static void renderHorizontalLoadingBar(float x, float y, int w, int h , float[] color, float evolution){

        Renderer.renderRectangle(x, y, w, h, color);
        Renderer.renderRectangle(x, y , w , (int)(100- evolution) * h / 100 , Color.WHITE);
    }


    public static void drawText(String text, int x , int y , int fontSize,  float[] color){
        text=text.toUpperCase(); //on met le text en maj
        int offset = 0 ;
        for (int i = 0 ;i < text.length(); i++){
            int unicode = text.codePointAt(i) + 1;
            int xo = unicode % 16 ;
            int yo = unicode / 16 ;

            if (xo > 0){ xo--;}
            else { xo = 15; yo--;}

            //System.out.println(unicode);
            //System.out.println("xo = " + xo +"   yo = " + yo);
            Texture.alphabet.bind();
            renderActor(x + i*( fontSize -1.5f),y,fontSize,fontSize,color,16,xo,yo);
            Texture.alphabet.unbind();
        }

    }

}


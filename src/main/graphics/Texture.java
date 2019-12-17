package main.graphics;

import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;


public class Texture {

    public static Texture tiles = loadTexture("/textures/Tiles_ex.png");

    int width, height;
    int id;

    public Texture (int width, int height, int id){
        this.height = height;
        this.width = width;
        this.id = id;
    }

    public static Texture loadTexture(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(Texture.class.getResource(path) );
            System.out.println("Ooui");
        }catch (IOException e){
            System.out.println("On arrive pas à ouvir");
            e.printStackTrace();
        }

        int w = image.getWidth();
        int h = image.getHeight();

        int[] pixels = new int[w * h];
        image.getRGB(0,0, w ,h , pixels, 0 , w);

        // mettre les données de la texture ds le buffer
        ByteBuffer buffer = BufferUtils.createByteBuffer(w*h*4); // *4 -> R V B Alpha

        //mettre pixels dans le buffer
        for (int y = 0  ; y < w ; y++){
            for (int x = 0 ; x < h ; x++ ){
                int i = pixels[x+y * w];
                buffer.put((byte) ((i >> 16 ) & 0xFF) );    // R
                buffer.put((byte) ((i >> 8 ) & 0xFF) );     // V
                buffer.put((byte) ((i ) & 0xFF) );          // B
                buffer.put((byte) ((i >> 24 ) & 0xFF) );    //Alpha
            }
        }

        buffer.flip();
        //--
        int id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D,id);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D,0, GL_RGBA8 , w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        return new Texture(w, h, id);
    }

    public int getWidth(){
        return width;
    }


    public int getHeight(){
        return height;
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }


}
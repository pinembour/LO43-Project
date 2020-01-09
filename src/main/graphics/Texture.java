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

    // Listes des différentes texture utilisé
    public static Texture studentTexture = loadTexture("/ActorSprite/eleve.png");
    public static Texture teacherTexture = loadTexture("/ActorSprite/prof.png");

    public static Texture alphabet = loadTexture("/Alphabet/alphabet.png");

    public static Texture floor = loadTexture("/tileset/floor.png");
    public static Texture furniture = loadTexture("/tileset/furniture.png");
    public static Texture pcTable = loadTexture("/tileset/pcTable.png");
    public static Texture wallsCarpet = loadTexture("/tileset/wallsCarpet.png");




    int width, height;
    int id;                 // un fois importer, les texture on une id

    public Texture (int width, int height, int id){
        this.height = height;
        this.width = width;
        this.id = id;
    }

    //charger une texture ( 256 * 256 )
    public static Texture loadTexture(String path){
        BufferedImage image = null;
        try {
            image = ImageIO.read(Texture.class.getResource(path) );
            //System.out.println("on a ouvert l'image : " + path );
        }catch (IOException e){
            System.out.println("On arrive pas à ouvir");
            e.printStackTrace();
        }

        int w = image.getWidth();
        int h = image.getHeight();
        //System.out.println("Taille de la texture : W = " + w + " ,  H = "+ h);


        int[] pixels = new int[w * h];
        image.getRGB(0,0, w ,h , pixels, 0 , w);

        //System.out.println("Texture : getRGB fait ");


        // mettre les données de la texture ds le buffer
        ByteBuffer buffer = BufferUtils.createByteBuffer(w*h*4); // *4 -> R V B Alpha

        //System.out.println("Texture : buffer créé");

        //mettre pixels dans le buffer
        for (int y = 0  ; y < w ; y++){
            for (int x = 0 ; x < h ; x++ ){
                int i = pixels[x + y * w];
                buffer.put((byte) ((i >> 16 ) & 0xFF) );    // R
                buffer.put((byte) ((i >> 8 ) & 0xFF) );     // V
                buffer.put((byte) ((i ) & 0xFF) );          // B
                buffer.put((byte) ((i >> 24 ) & 0xFF) );    //Alpha
            }
        }
//        System.out.println("Texture : buffer rempli");

        buffer.flip();
  //      System.out.println("Texture : buffer flip");

        //--
        int id = glGenTextures();
        glBindTexture(GL_TEXTURE_2D,id);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        glTexImage2D(GL_TEXTURE_2D,0, GL_RGBA8 , w, h, 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        //System.out.println("Importation terminé");
        return new Texture(w, h, id);
    }

    public int getWidth(){
        return width;
    }


    public int getHeight(){
        return height;
    }

    // charge la texture pour l'afficher
    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }

    // retire la texture
    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }


}

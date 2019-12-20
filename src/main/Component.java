package main;

import main.game.Game;
import main.graphics.Renderer;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

public class Component {

    public boolean running = false ;

    public static String title = "TITRE";
    public static int scale = 3;
    public static int width = 720/scale;
    public static int height = 480/scale;

    int time = 0;
    public static boolean tick = false;
    public static boolean render = false;

    DisplayMode mode = new DisplayMode(width * scale , height * scale);

    Game game;

    public Component(){
        display();

        game = new Game();
    }


    //start the game
    public void start(){
        running = true;
        loop();
    }

    public void stop(){
        running = false;
    }

    //quitter le jeu
    public void exit(){
        Display.destroy();          //fermer fenetre
        System.exit(0);      //quitter le jeu
    }

    public void loop(){

        game.init();

        //--
        long timer = System.currentTimeMillis();

        long before = System.nanoTime();            // temps du system depuis dernire update
        double elapsed = 0 ;                        // temps entre maintenant et la dernière update
        double nanoSeconds = 1000000000.0 / 60.0;   // 1sec

        int ticks = 0;      // temps ds le jeu
        int frames = 0;     // affichage des images

        while (running){
            if (Display.isCloseRequested()){
                stop();
            }
            Display.update();   // update le fenetre
            width = Display.getWidth() / scale;
            height = Display.getHeight() / scale;

            tick = false;
            render = false;
            long now = System.nanoTime();
            elapsed = now - before ;

            if (elapsed > nanoSeconds){
                before += nanoSeconds;
                tick =true;
                ticks ++ ;

                render = true;
                frames ++;
            }else {
                /*
                render = true;
                frames ++;

                 */
            }

            if (tick) update();           // update le jeu
            if (render) render();         // afficher le jeu

            // affichage toute les secondes
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                Display.setTitle(title + " -- ticks : " + ticks + ", fps : " + frames);
                ticks = 0;
                frames = 0;
            }


        }
        exit();
    }


    public void update(){
        time++;

        game.update();
    }

    public void render(){
        view2D(width , height);
        glClear(GL_COLOR_BUFFER_BIT);   //supprime le reste de l'ancien rendu
        glClearColor(0.8f , 0.9f , 1.0f , 1.0f);

        //--

        game.render();

    }

    //-------------------------------------------------

    // ouvrir une fenetre
    public void display(){
        try {
            Display.setDisplayMode(mode);
            Display.setResizable(true);
            Display.setFullscreen(false);
            Display.setTitle(title);
            Display.create();

            view2D(width, height);
        } catch (LWJGLException e){
            e.printStackTrace();
        }
    }

    // Initialiser OpenGL
    private void view2D(int width , int height){
        glViewport(0,0,width * scale , height * scale );
        glMatrixMode(GL_PROJECTION);    //Determiner une matrix
        glLoadIdentity();               //Mettre a jour l'identite
        GLU.gluOrtho2D(0, width, height,0); // On veut de la 2D
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glEnable(GL_TEXTURE_2D);
    }


    //-------------------------------------------------

    public static void main(String[] args) {
        Component main = new Component();
        main.start();
    }

}

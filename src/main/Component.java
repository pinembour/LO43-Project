package main;

import main.graphics.Color;
import main.graphics.Renderer;
import main.utiles.Constants;
import main.utiles.Input;
import org.lwjgl.Version;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;

import main.game.Game;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class Component {

    public static String title = "Come At 8 AM";        //titre
    //public static int scale = 3;                        // echelle
    public static int width = Constants.WINDOW_WIDTH ;                // Longeur
    public static int height = Constants.WINDOW_HEIGHT ;               // hauteur

    public static long window ;

    public static Input input;

    Game game;

    int lastFPS = 0;

    // constructeur
    public Component(){

        setCallBacks();
        System.out.println("LWJGL Version " + Version.getVersion() + " is working.");

        display();  // on affiche la fenetre

        input = new Input(window);

        game = new Game();
    }


    //start the game
    public void start(){
        game.init();

        loop();
    }



    //quitter le jeu
    public void exit(){
        glfwTerminate();
    }


    // boucle du jeu avec gestion FPS
    public void loop(){

        double frame_cap = 1.0/60.0;

        double frame_time = 0;
        int frames = 0;

        double time = System.nanoTime() / (double) 1000000000L;
        double unprocessed = 0;

        while (!glfwWindowShouldClose(window) ){
            boolean can_render = false;
            double time_2 = System.nanoTime() / (double) 1000000000L;
            double passed = time_2-time;
            unprocessed += passed;
            frame_time += passed;

            time = time_2;
            if(!game.getLevel().isOver()) {
                while (unprocessed >= frame_cap){
                    unprocessed -= frame_cap;
                    can_render = true;

                    update();
                    if (frame_time >= 1.0){
                        frame_time = 0;
                        //System.out.println("FPS: " + frames);
                        lastFPS = frames;
                        frames = 0;
                    }
                }

                if (can_render){
                    render();

                    glfwSwapBuffers(window);
                    frames++;
                }
            } else {
                if(game.getLevel().isLevelWon()){
                    if(game.getLevel().isGameWon()){
                        System.out.println("You won");
                        break;
                    } else game.getLevel().lvlUp();
                } else {
                    System.out.println("Game over, lvl max réussit = " + game.getLevel().getLevel());
                    break;
                }
            }
        }
        exit();
    }

    //Calcul du jeu
    public void update(){
        game.update();          // maj du jeu

        input.update();         // gestion des input du joueur

        glfwPollEvents();       // a laisser après les update
    }

    // affichage graphique
    public void render(){
        glClear(GL_COLOR_BUFFER_BIT);   //supprime le reste de l'ancien rendu
        //glClearColor(0.8f , 0.9f , 1.0f , 1.0f);
        //--
        game.render();                  // rendu du jeu

        //Renderer.drawText("FPS:" + lastFPS, 0, Constants.HUD_LIGNE_TOP, Constants.HUD_FONT_SIZE,Color.GREEN);

    }

    //-------------------------------------------------

    // ouvrir une fenetre
    public void display(){
        // Initialse la Library
        if (!glfwInit()){
            throw new IllegalStateException("Failed ton initialize GLFW");
        }
        org.lwjgl.glfw.GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE,GLFW.GLFW_FALSE);
        // creer la fenetre
        window = glfwCreateWindow(width , height, title, NULL, NULL);
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }

        // on affiche la fenetre
        glfwShowWindow(window);
        // on relie openGL a la fenetre
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glAlphaFunc(GL_GREATER, 0.1f);

        setOrthoOn();
    }


    // parametré la fenetre pour de la 2D
    public static void setOrthoOn()
    {
        int viewportX = 0;
        int viewportY = 0;

        // prepare to render in 2D
        GL11.glDisable(GL11.GL_DEPTH_TEST);             // so 2D stuff stays on top of 3D scene
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();                            // preserve perspective view
        GL11.glLoadIdentity();                          // clear the perspective matrix
        GL11.glOrtho(viewportX,viewportX+width,viewportY + height,viewportY,-1,1);  // turn on 2D
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glPushMatrix();				// Preserve the Modelview Matrix
        GL11.glLoadIdentity();				// clear the Modelview Matrix
    }

    //erreur
    public static void setCallBacks(){
        glfwSetErrorCallback(new GLFWErrorCallback() {
            @Override
            public void invoke(int error, long description){
                throw new IllegalStateException(GLFWErrorCallback.getDescription(description));
            }
        });
    }


    //-------------------------------------------------


    public static void main(String[] args) {

        Component main = new Component();
        main.start();

    }
}

package main;

import main.graphics.Color;
import main.graphics.Texture;
import main.utiles.Input;
import org.lwjgl.Version;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.GLFW.*;


import main.game.Game;
import main.graphics.Renderer;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;



public class Component {


    public boolean running = false ;

    public static String title = "TITRE";
    public static int scale = 3;
    public static int width = 720/scale;
    public static int height = 480/scale;

    public static long window ;

    public static Input input;

    //Texture texture = Texture.loadTexture("/textures/texture_test_8v2.png");
    Game game;

    public Component(){

        setCallBacks();
        System.out.println("LWJGL Version " + Version.getVersion() + " is working.");

        display();

        input = new Input(window);

        game = new Game();
    }


    //start the game
    public void start(){
        running = true;
        game.init();

        loop();
    }


    public void stop(){
        running = false;
    }

    //quitter le jeu
    public void exit(){
        glfwTerminate();
    }


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

            while (unprocessed >= frame_cap){
                unprocessed -= frame_cap;
                can_render = true;

                update();
                if (frame_time >= 1.0){
                    frame_time = 0;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }

            if (can_render){
                render();

                glfwSwapBuffers(window);
                frames++;
            }
        }
        exit();
    }


    public void update(){

        if (input.isMouseButtonPressed(GLFW_MOUSE_BUTTON_1)){
            System.out.println("Coord : " + game.getMouseX() + " ; "+ game.getMouseY());
            //System.out.println("blaa");
        }

        if (input.isKeyPressed(GLFW_KEY_ESCAPE)){System.out.println("marche");}


        game.update();


        input.update();



        glfwPollEvents();
    }

    public void render(){
        glClear(GL_COLOR_BUFFER_BIT);   //supprime le reste de l'ancien rendu
        //glClearColor(0.8f , 0.9f , 1.0f , 1.0f);
        //--
        game.render();

    }

    //-------------------------------------------------

    // ouvrir une fenetre
    public void display(){
        // Initialse la Library
        if (!glfwInit()){
            throw new IllegalStateException("Failed ton initialize GLFW");
        }

        // creer la fenetre
        window = glfwCreateWindow(width * scale, height*scale, title, NULL, NULL);
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


    // en 2D
    public static void setOrthoOn()
    {
        int viewportX = 0;
        int viewportY = 0;

        // prepare to render in 2D
        GL11.glDisable(GL11.GL_DEPTH_TEST);             // so 2D stuff stays on top of 3D scene
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glPushMatrix();                            // preserve perspective view
        GL11.glLoadIdentity();                          // clear the perspective matrix
        GL11.glOrtho(viewportX,viewportX+width*scale,viewportY + height*scale,viewportY,-1,1);  // turn on 2D
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

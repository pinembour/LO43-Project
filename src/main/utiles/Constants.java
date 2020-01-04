package main.utiles;

public class Constants {

    public static final int HORIZONTAL_TILES= 24 ;
    public static final int VERTICAL_TILES = 17 ;


    public static final int TILE_SIZE = 32 ;
    public static final int CHARACTER_SIZE = TILE_SIZE;
    public static final int CHARACTER_SPEED = 1;

    public static final int TEACHER_BAR_WIDTH = CHARACTER_SIZE - CHARACTER_SIZE/5;
    public static final int TEACHER_BAR_HEIGHT = CHARACTER_SIZE/10;
    public static final int TEACHER_BAR_POSITION = CHARACTER_SIZE / 2;




    //-----------WINDOW--------------------

    public static final int WINDOW_WIDTH = HORIZONTAL_TILES*TILE_SIZE ;
    public static final int WINDOW_HEIGHT = VERTICAL_TILES*TILE_SIZE;


    //-----------DISTANCE INPUT-----------------

    public static final int CLICK_DISTANCE_FROM_COMPUTER  = 50;
    public static final int CLICK_DISTANCE_FROM_TEACHER  = 20;


    //--------CHAIR + COMPUTER---------------

    public static final int CHAIR_DISTANCE_FROM_COMPUTER = TILE_SIZE/2;
    public static final int CHAIR_POSTION_PLUSY = TILE_SIZE/2 ;
    public static final int CHAIR_DISTANCE_EACH_OTHER = TILE_SIZE ;


    //-------------HUD-----------------------


    public static final int HUD_GOLD_Y = 13 * TILE_SIZE;
    public static final int HUD_STUDENT_Y = HUD_GOLD_Y + TILE_SIZE;
    public static final int HUD_X = 13 * TILE_SIZE;
    public static final int HUD_FONT_SIZE = TILE_SIZE/2;


    //------------------TILES INT---------------------

    public static final int TILE_INT_OLD_COMPUTER = 90;
//    public static final
//    public static final
//    public static final
//    public static final
//    public static final
//    public static final
//    public static final
}

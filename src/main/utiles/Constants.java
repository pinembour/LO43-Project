package main.utiles;

public class Constants {

    public static final int HORIZONTAL_TILES= 24 ;
    public static final int VERTICAL_TILES = 17 ;


    public static final int TILE_SIZE = 38 ;


    //---------CHARACTER-------------

    public static final int CHARACTER_SIZE = TILE_SIZE;
    public static final int CHARACTER_SPEED = 1;

    public static final int TEACHER_BAR_WIDTH = CHARACTER_SIZE - CHARACTER_SIZE/5;
    public static final int TEACHER_BAR_HEIGHT = CHARACTER_SIZE/10;
    public static final int TEACHER_BAR_POSITION = CHARACTER_SIZE / 2;

    public static final int TEACHER_NUMBER = 8;
    public static final int TEACHER_SPAWN_X = TILE_SIZE * 14;
    public static final int TEACHER_SPAWN_Y = TILE_SIZE * 6;
    public static final int TEACHER_BACK_TO_SPAWN_X = TEACHER_SPAWN_X / TILE_SIZE;
    public static final int TEACHER_BACK_TO_SPAWN_Y = TEACHER_SPAWN_Y/ TILE_SIZE;

    public static final int STUDENT_SPAWN_X = TILE_SIZE * 3;
    public static final int STUDENT_SPAWN_Y = TILE_SIZE * 15;


    public static final int STUDENT_DESPAWN_X =  8;
    public static final int STUDENT_DESPAWN_Y =  16;


    //--------------------------------

    public static final int REGISTRATION_BAR_WIDTH = TILE_SIZE/5;
    public static final int REGISTRATION_BAR_HEIGHT = TILE_SIZE;

    //-----------WINDOW--------------------

    public static final int WINDOW_WIDTH = HORIZONTAL_TILES*TILE_SIZE ;
    public static final int WINDOW_HEIGHT = VERTICAL_TILES*TILE_SIZE;


    //-----------DISTANCE INPUT-----------------

    public static final int CLICK_DISTANCE_FROM_COMPUTER  = 50;
    public static final int CLICK_DISTANCE_FROM_TEACHER  = 20;
    public static final int CLICK_DISTANCE_FROM_COFFEE_MACHINE  = 50;


    //--------CHAIR + COMPUTER---------------

    public static final int CHAIR_DISTANCE_FROM_COMPUTER = TILE_SIZE;
    public static final int CHAIR_POSTION_PLUSY = 0 ;
    public static final int CHAIR_DISTANCE_EACH_OTHER = TILE_SIZE ;
    public static final int COMPUTER_LVL_MAX = 3;





    //-------------HUD-----------------------


    public static final int HUD_GOLD_Y = 13 * TILE_SIZE;
    public static final int HUD_STUDENT_Y = HUD_GOLD_Y + TILE_SIZE;
    public static final int HUD_X = 13 * TILE_SIZE;
    public static final int HUD_FONT_SIZE = TILE_SIZE/2;


    //------------------TILES INT---------------------

    public static final int TIlE_PER_LAYER = HORIZONTAL_TILES * VERTICAL_TILES;


    // y = 6 -> 8 , x = 15 -> 19
    public static final int TILE_INT_LV1_COFFEE_MACHINE_1 = 253;    //
    public static final int TILE_INT_LV1_COFFEE_MACHINE_2 = 459;    // 459
    public static final int TILE_INT_LV1_COFFEE_MACHINE_3 = 475;    //

    public static final int TILE_INT_LV2_COFFEE_MACHINE_1 = 150;
    public static final int TILE_INT_LV2_COFFEE_MACHINE_2 = 166;
    public static final int TILE_INT_LV2_COFFEE_MACHINE_3 = 182;


    public static final int TILE_INT_LV1_COMPUTER_1 = 253;
    public static final int TILE_INT_LV1_COMPUTER_2 = 107;
    public static final int TILE_INT_LV1_COMPUTER_3 = 123;


    public static final int TILE_INT_LV2_COMPUTER_1 =  74 ;
    public static final int TILE_INT_LV2_COMPUTER_2 = 90;
    public static final int TILE_INT_LV2_COMPUTER_3 = 106;



    //----------------Layer------------------------




    public static final int LAYER_FLOOR = 0;
    public static final int LAYER_DECOR_BOTTOM = 1;
    public static final int LAYER_DECOR_MIDDLE = 2;
    public static final int LAYER_WALL_BOTTOM = 3;
    public static final int LAYER_LV1_BOTTOM = 4;
    public static final int LAYER_LV2_BOTTOM = 5;
    public static final int LAYER_LV3_BOTTOM = 6;
    public static final int LAYER_DECOR_TOP = 7;
    public static final int LAYER_LV1_TOP = 8;
    public static final int LAYER_LV2_TOP = 9;
    public static final int LAYER_LV3_TOP = 10;
    public static final int LAYER_WALL_TOP = 11;
    public static final int LAYER_COLLISION = 12;

    //---------------Timer------------------------

    public static final int EVENT_PERIOD = 50;
    public static final int TIME_LIMIT=120;


//
//
//    public static final int LAYER_TAPIS_TABLEAU = 1;
//    public static final int LAYER_LV2_AV_TEACHER = 2 ;
//    public static final int LAYER_LV1_AV_TEACHER = 4;
//    public static final int LAYER_LV2_AP_TEACHER = 3;
//    public static final int LAYER_LV1_AP_TEACHER = 5;
//    public static final int LAYER_WALL = 6;

}

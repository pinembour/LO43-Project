package main.utiles;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Constants {

    public static final int HORIZONTAL_TILES= 24 ;
    public static final int VERTICAL_TILES = 17 ;


    public static final int TILE_SIZE = 38 ;


    //---------CHARACTER-------------

    public static final int CHARACTER_SIZE = TILE_SIZE;
    public static final int CHARACTER_SPEED = 1;

    public static final int TEACHER_BAR_WIDTH = CHARACTER_SIZE - CHARACTER_SIZE/5;
    public static final int TEACHER_BAR_HEIGHT = CHARACTER_SIZE/10;
    public static final int TEACHER_BAR_POSITION = CHARACTER_SIZE  * 2/5;

    public static final int TEACHER_NUMBER = 8;
    public static final int TEACHER_SPAWN_X = TILE_SIZE * 14;
    public static final int TEACHER_SPAWN_Y = TILE_SIZE * 6;
    public static final int TEACHER_BACK_TO_SPAWN_X = TEACHER_SPAWN_X / TILE_SIZE;
    public static final int TEACHER_BACK_TO_SPAWN_Y = TEACHER_SPAWN_Y/ TILE_SIZE;

    public static final int STUDENT_SPAWN_X = TILE_SIZE * 3;
    public static final int STUDENT_SPAWN_Y = TILE_SIZE * 15;


    public static final int STUDENT_DESPAWN_X =  8;
    public static final int STUDENT_DESPAWN_Y =  16;


    //-----------------------------------
    public static final float COMFORT_LOSE =  0.2f;
    public static final float TIRED_LOSE = 0.008f;


    //---- watch dir

    public static final int LOOK_LEFT = 1 ;
    public static final int LOOK_RIGHT= 2 ;
    public static final int LOOK_UP= 3 ;
    public static final int LOOK_DOWN= 0  ;


    //--------------------------------

    public static final int REGISTRATION_BAR_WIDTH = TILE_SIZE/5;
    public static final int REGISTRATION_BAR_HEIGHT = TILE_SIZE;

    //-----------WINDOW--------------------

    public static final int WINDOW_WIDTH = HORIZONTAL_TILES*TILE_SIZE ;
    public static final int WINDOW_HEIGHT = VERTICAL_TILES*TILE_SIZE;


    //-----------DISTANCE INPUT-----------------

    public static final int CLICK_DISTANCE_FROM_COMPUTER  = 50;
    public static final int CLICK_DISTANCE_FROM_TEACHER  = 20;
    public static final int CLICK_DISTANCE_FROM_COFFEE_MACHINE  = 30;


    //--------CHAIR + COMPUTER---------------

    public static final int CHAIR_DISTANCE_FROM_COMPUTER = TILE_SIZE;
    public static final int CHAIR_POSTION_PLUSY = 0 ;
    public static final int CHAIR_DISTANCE_EACH_OTHER = TILE_SIZE ;
    public static final int COMPUTER_LVL_MAX = 3;


    //--------------COFFE

    public static final int COFFEE_TIME_TO_AVAILABLE = 8;


    //-------------HUD-----------------------


    public static final int HUD_GOLD_Y = 13 * TILE_SIZE;
    public static final int HUD_STUDENT_Y = HUD_GOLD_Y + TILE_SIZE;
    public static final int HUD_X = 13 * TILE_SIZE;
    public static final int HUD_FONT_SIZE = TILE_SIZE/2;


    //------------------TILES INT---------------------

    public static final int TIlE_PER_LAYER = HORIZONTAL_TILES * VERTICAL_TILES;


    // y = 6 -> 8 , x = 15 -> 19
    public static final int TILE_INT_LV1_COFFEE_MACHINE_1 = 511;    //
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


    public static final int TILE_INT_LV1_SOFA_1 = 381;//107

    public static final int TILE_INT_LV1_SOFA_VIDE = 512;//125

    public static final int TILE_INT_LV2_SOFA_1_1 = 365;
    public static final int TILE_INT_LV2_SOFA_2_1 = 366;
    public static final int TILE_INT_LV2_SOFA_1_2 = 349;
    public static final int TILE_INT_LV2_SOFA_2_2 = 350;

    public static final int TILE_INT_LV3_SOFA_1_1 = 331;
    public static final int TILE_INT_LV3_SOFA_2_1 = 332;
    public static final int TILE_INT_LV3_SOFA_3_1 = 333;
    public static final int TILE_INT_LV3_SOFA_1_2 = 315;
    public static final int TILE_INT_LV3_SOFA_2_2 = 316;
    public static final int TILE_INT_LV3_SOFA_3_2 = 317;



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
    public static final int TIME_LIMIT=150;


    //---------------Levels------------------------

    public static final List<Integer> STUDENTS_LVL= Arrays.asList(5,8,10);

//
//
//    public static final int LAYER_TAPIS_TABLEAU = 1;
//    public static final int LAYER_LV2_AV_TEACHER = 2 ;
//    public static final int LAYER_LV1_AV_TEACHER = 4;
//    public static final int LAYER_LV2_AP_TEACHER = 3;
//    public static final int LAYER_LV1_AP_TEACHER = 5;
//    public static final int LAYER_WALL = 6;

}

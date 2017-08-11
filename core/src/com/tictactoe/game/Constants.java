package com.tictactoe.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by RAJESHGUPTA on 08/08/17.
 */

public class Constants {

    public static final float WORLD_SIZE = 720.0f;
    public static final float MARGINS = 60.0f;
    public static final float BOX_SIZE = WORLD_SIZE/3;
    public static final float CIRCLE_RADIUS = BOX_SIZE/2 - MARGINS;
    public static final float acceleration = 75.0f;
    public static final Vector2 BOX_1 = new Vector2(BOX_SIZE - BOX_SIZE/2,BOX_SIZE - BOX_SIZE/2);
    public static final Vector2 BOX_2 = new Vector2(WORLD_SIZE/2,BOX_SIZE - BOX_SIZE/2);
    public static final Vector2 BOX_3 = new Vector2(WORLD_SIZE/2 + BOX_SIZE,BOX_SIZE - BOX_SIZE/2);
    public static final Vector2 BOX_4 = new Vector2(BOX_SIZE - BOX_SIZE/2,WORLD_SIZE/2);
    public static final Vector2 BOX_5 = new Vector2(WORLD_SIZE/2,WORLD_SIZE/2);
    public static final Vector2 BOX_6 = new Vector2(WORLD_SIZE/2 + BOX_SIZE,WORLD_SIZE/2);
    public static final Vector2 BOX_7 = new Vector2(BOX_SIZE - BOX_SIZE/2,WORLD_SIZE/2 + BOX_SIZE);
    public static final Vector2 BOX_8 = new Vector2(WORLD_SIZE/2,WORLD_SIZE/2 + BOX_SIZE);
    public static final Vector2 BOX_9 = new Vector2(WORLD_SIZE/2 + BOX_SIZE,WORLD_SIZE/2 + BOX_SIZE);

    public enum RESULT {
        xWin,
        oWin,
        draw
    }

    public static final Vector2[] BOXES = {
            BOX_1,
            BOX_2,
            BOX_3,
            BOX_4,
            BOX_5,
            BOX_6,
            BOX_7,
            BOX_8,
            BOX_9
    };
    public static final int[][] WINNER_RULES = {
            {0,1,2},
            {3,4,5},
            {6,7,8},
            {0,3,6},
            {1,4,7},
            {2,5,8},
            {0,4,8},
            {2,4,6}

    };

}

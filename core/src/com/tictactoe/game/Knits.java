package com.tictactoe.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by RAJESHGUPTA on 08/08/17.
 */

public class Knits {

    Vector2 centerPos;
    boolean isCircle = true;
    public Knits(Vector2 pos,boolean circle)
    {
        centerPos = pos;
        isCircle = circle;
    }
    public void render(ShapeRenderer renderer)
    {

        if (isCircle) {

            renderer.setColor(Color.WHITE);
            renderer.set(ShapeRenderer.ShapeType.Filled);
            renderer.circle(centerPos.x, centerPos.y, Constants.CIRCLE_RADIUS);

        }
        else
        {
            renderer.setColor(Color.WHITE);
            renderer.set(ShapeRenderer.ShapeType.Line);

            renderer.line(centerPos.x,centerPos.y,centerPos.x - Constants.BOX_SIZE/2 + Constants.MARGINS,centerPos.y - Constants.BOX_SIZE/2 + Constants.MARGINS);
            renderer.line(centerPos.x,centerPos.y,centerPos.x + Constants.BOX_SIZE/2 - Constants.MARGINS,centerPos.y - Constants.BOX_SIZE/2 + Constants.MARGINS);

            renderer.line(centerPos.x,centerPos.y,centerPos.x + Constants.BOX_SIZE/2 - Constants.MARGINS,centerPos.y + Constants.BOX_SIZE/2 - Constants.MARGINS);
            renderer.line(centerPos.x,centerPos.y,centerPos.x - Constants.BOX_SIZE/2 + Constants.MARGINS,centerPos.y + Constants.BOX_SIZE/2 - Constants.MARGINS);


        }
    }
}

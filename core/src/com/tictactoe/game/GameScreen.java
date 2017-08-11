package com.tictactoe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by RAJESHGUPTA on 08/08/17.
 */

public class GameScreen extends InputAdapter implements Screen  {

    ShapeRenderer mShapeRenderer;
    FitViewport mViewport;
    ScreenViewport screenViewPort;
    SpriteBatch batch;
    BitmapFont bitmap;
    Knits[] records = new Knits[9];
    boolean isCircle = true;
    boolean endGame = false;


    TicTacToe game;

    public GameScreen(TicTacToe game)
    {
        this.game = game;
    }
    @Override
    public void show() {
        mShapeRenderer = new ShapeRenderer();
        mShapeRenderer.setAutoShapeType(true);
        mViewport = new FitViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);
        screenViewPort = new ScreenViewport(mViewport.getCamera());
        batch = new SpriteBatch();
        bitmap = new BitmapFont();
        bitmap.getData().scale(1.1f);
        bitmap.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Gdx.input.setInputProcessor(this);

        for (int i=0;i<9;i++)
        {
            for (int j=0;j<3;j++)
            //Knits record = records.get(i);
            records[i] = null;
        }
    }

    @Override
    public void render(float delta) {
        mViewport.apply();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mShapeRenderer.setProjectionMatrix(mViewport.getCamera().combined);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        mShapeRenderer.setColor(Color.WHITE);
        mShapeRenderer.line(mViewport.getWorldWidth()/2 - Constants.BOX_SIZE/2,mViewport.getWorldHeight(),mViewport.getWorldWidth()/2 - Constants.BOX_SIZE/2,0);
        mShapeRenderer.line(mViewport.getWorldWidth()/2 + Constants.BOX_SIZE/2,mViewport.getWorldHeight(),mViewport.getWorldWidth()/2 + Constants.BOX_SIZE/2,0);
        mShapeRenderer.line(0,mViewport.getWorldHeight()/2 - Constants.BOX_SIZE/2,mViewport.getWorldWidth(),mViewport.getWorldHeight()/2 - Constants.BOX_SIZE/2);
        mShapeRenderer.line(0,mViewport.getWorldHeight()/2 + Constants.BOX_SIZE/2,mViewport.getWorldWidth(),mViewport.getWorldHeight()/2 + Constants.BOX_SIZE/2);
        for (int i=0;i<9;i++)
        {
            if (records[i] != null) {
                Knits record = records[i];
                record.render(mShapeRenderer);
            }
        }
        mShapeRenderer.end();
        batch.setProjectionMatrix(screenViewPort.getCamera().combined);
        batch.begin();
        bitmap.draw(batch,"x: " + game.xGames + "\no: " + game.oGames,1.0f,mViewport.getWorldHeight());
        batch.end();


    }

    @Override
    public void resize(int width, int height) {
        mViewport.update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        mShapeRenderer.dispose();
        batch.dispose();
        bitmap.dispose();
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 worldTouch = mViewport.unproject(new Vector2(screenX,screenY));
            for (int i = 0; i < Constants.BOXES.length; i++) {
                Knits knit = new Knits(Constants.BOXES[i], isCircle);
                if (worldTouch.dst(Constants.BOXES[i]) < Constants.BOX_SIZE) {
                    if (records[i] == null) {
                        records[i] = knit;
                        isCircle = !isCircle;
                        if (!checkIfAnyOneWon()) {
                            if (!game.isVsPlayer) {
                                playOtherPlayer();
                            }
                        }
                        return true;
                    }
                }
            }
        return true;
    }
    boolean checkIfAnyOneWon()
    {
        int[][] rules = Constants.WINNER_RULES;
        boolean isDraw = true;
        boolean isWon = false;
        for(int i=0;i<9;i++)
        {
            if (records[i]==null)
            {
                isDraw = false;
            }
        }
        if (!isDraw) {
            for (int i = 0; i < rules.length; i++) {

                boolean xWon = registerWin(rules[i][0], rules[i][1], rules[i][2], false);
                if (!xWon) {
                    boolean oWon = registerWin(rules[i][0], rules[i][1], rules[i][2], true);
                    if (oWon) {
                        Gdx.app.debug("Won", "O Won");

                        game.oGames++;
                        endGame = true;
                        game.showEndScreen(Constants.RESULT.oWin);
                        break;
                    }
                    else
                    {
                        isWon = false;
                    }
                } else {
                    Gdx.app.debug("Won", "X Won");

                    game.xGames++;
                    endGame = true;
                    game.showEndScreen(Constants.RESULT.xWin);
                    break;
                }
            }
        }
        else
        {
            Gdx.app.debug("Draw", "Draw");

            game.showEndScreen(Constants.RESULT.draw);
            return true;
        }
        return isWon;
    }
    boolean registerWin(int i,int j,int k, boolean circle)
    {
        if (records[i] != null && records[j] != null && records[k]!= null)
        {
            if (records[i].isCircle == circle && records[j].isCircle == circle && records[k].isCircle == circle)
            {
                return true;
            }
        }
        return false;
    }
    void playOtherPlayer()
    {

        for (int i=0;i<Constants.WINNER_RULES.length;i++)
        {

            int blockNumber = findBlockToDraw(Constants.WINNER_RULES[i][0],Constants.WINNER_RULES[i][1],Constants.WINNER_RULES[i][2]);
            if (blockNumber != -1)
            {
                if (records[blockNumber] == null) {

                    records[blockNumber] = new Knits(Constants.BOXES[blockNumber], isCircle);
                    checkIfAnyOneWon();
                    break;

                }
                else
                {
                    drawRandom();
                }
            }
            if(blockNumber==-1 && i == Constants.WINNER_RULES.length - 1)
            {
                drawRandom();
                break;
            }

        }
        isCircle = !isCircle;
    }
    int findBlockToDraw(int x,int y,int z) {


        if (records[x] != null && records[y] != null) {
            if (records[x].isCircle == isCircle && records[y].isCircle == isCircle) {
                return z;
            }
            else if (records[x].isCircle != isCircle && records[y].isCircle != isCircle) {
                return z;
            }
        }
        // x and z played
        else if (records[y] != null && records[z] != null) {
            if (records[y].isCircle == isCircle && records[z].isCircle == isCircle) {
                return x;
            }
            else if (records[y].isCircle != isCircle && records[z].isCircle != isCircle) {
                return x;
            }
        }
        // z and y played
        else if (records[z] != null && records[x] != null) {
            if (records[z].isCircle == isCircle && records[x].isCircle == isCircle) {
                return y;
            }
            else if (records[z].isCircle != isCircle && records[x].isCircle != isCircle) {
                return y;
            }
        }
        return -1;
    }
    void drawRandom()
    {
        for (int i = 0; i < 100; i++) {

            int n = (int)Math.floor((Math.random() * 9));
            if ( records[n] == null ) {

                Knits knit = new Knits(Constants.BOXES[n],isCircle);
                records[n] = knit;
                checkIfAnyOneWon();
                break;
            }

        }
    }
}

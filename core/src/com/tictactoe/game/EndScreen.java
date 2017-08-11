package com.tictactoe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by RAJESHGUPTA on 10/08/17.
 */

public class EndScreen implements Screen,InputProcessor {


    SpriteBatch batch;
    BitmapFont mBitmapFont;
    ScreenViewport mScreenViewport;
    FitViewport mExtendViewport;
    ShapeRenderer mShapeRenderer;

    TicTacToe mGame;

    Constants.RESULT mRESULT;
    String resulStr = "";

    public EndScreen(TicTacToe game,Constants.RESULT result)
    {
        mGame = game;
        mRESULT = result;
    }
    @Override
    public void show() {

        mShapeRenderer = new ShapeRenderer();
        mExtendViewport = new FitViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);
        mScreenViewport = new ScreenViewport(mExtendViewport.getCamera());
        batch = new SpriteBatch();
        mBitmapFont = new BitmapFont();
        mBitmapFont.getData().scale(2.0f);
        mBitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Gdx.input.setInputProcessor(this);

        if (mRESULT == Constants.RESULT.draw)
        {
            resulStr = "Thats's a TIE";
        }
        else if (mRESULT == Constants.RESULT.xWin)
        {
            resulStr = "X Wins";
        }
        else if (mRESULT == Constants.RESULT.oWin)
        {
            resulStr = "O Wins";
        }

    }

    @Override
    public void render(float delta) {

        mExtendViewport.apply();
        Gdx.gl20.glClearColor(1.0f,1.0f,1.0f,1.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mShapeRenderer.setProjectionMatrix(mExtendViewport.getCamera().combined);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mShapeRenderer.end();
        batch.setProjectionMatrix(mScreenViewport.getCamera().combined);
        batch.begin();
        mBitmapFont.setColor(com.badlogic.gdx.graphics.Color.BLACK);

        mBitmapFont.draw(batch,resulStr,mExtendViewport.getWorldWidth()/4,mExtendViewport.getWorldHeight()/2);
        mBitmapFont.draw(batch,"Press x to Exit to Main Menu",mExtendViewport.getWorldWidth()/4,mExtendViewport.getWorldHeight()/2 - Constants.MARGINS * 4);
        mBitmapFont.draw(batch,"Press Space To Play",mExtendViewport.getWorldWidth()/4,mExtendViewport.getWorldHeight()/2 - Constants.MARGINS*2);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        mExtendViewport.update(width,height,true);
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
        mBitmapFont.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE)
        {
            mGame.showGameScreen();
        }
        else if (keycode == Input.Keys.X)
        {
            mGame.showStartScreen();
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

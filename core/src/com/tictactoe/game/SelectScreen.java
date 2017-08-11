package com.tictactoe.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Created by RAJESHGUPTA on 09/08/17.
 */

public class SelectScreen implements Screen, InputProcessor {

    TicTacToe mGame;

    SpriteBatch batch;
    BitmapFont mBitmapFont;
    ScreenViewport mScreenViewport;
    FitViewport mExtendViewport;
    ShapeRenderer mShapeRenderer;
    GlyphLayout mGlyphLayout;
    Vector2 position;
    //Vector2 velocity;

    public SelectScreen(TicTacToe game)
    {
        mGame = game;
    }
    @Override
    public void show() {
        mShapeRenderer = new ShapeRenderer();
        mExtendViewport = new FitViewport(Constants.WORLD_SIZE,Constants.WORLD_SIZE);
        mScreenViewport = new ScreenViewport(mExtendViewport.getCamera());
        batch = new SpriteBatch();
        position = new Vector2(mExtendViewport.getWorldWidth(),mExtendViewport.getWorldHeight() - Constants.MARGINS);
        //mGlyphLayout = new GlyphLayout(mBitmapFont);
        mBitmapFont = new BitmapFont();
        mBitmapFont.getData().scale(1.5f);
        mBitmapFont.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        mExtendViewport.apply();

        Gdx.gl20.glClearColor(1.0f,1.0f,1.0f,1.0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mShapeRenderer.setProjectionMatrix(mExtendViewport.getCamera().combined);
        mShapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mShapeRenderer.end();

       // position.x -= delta * Constants.acceleration;

        position.sub(delta * Constants.acceleration,0);
        if (position.x < -300.0f)
        {
            position.x = mExtendViewport.getWorldWidth();
        }
        batch.setProjectionMatrix(mScreenViewport.getCamera().combined);
        batch.begin();
        mBitmapFont.setColor(com.badlogic.gdx.graphics.Color.BLACK);

        mBitmapFont.draw(batch,"TIC-TAC-TOE",position.x,position.y,mExtendViewport.getWorldWidth()/2, Align.center,false);

//        mBitmapFont.draw(batch,"2 PLAYERS",mExtendViewport.getWorldWidth()/4,mExtendViewport.getWorldHeight()/2);
        mBitmapFont.draw(batch,"Press A To Play vs AI",mExtendViewport.getWorldWidth()/4,mExtendViewport.getWorldHeight()/2 - Constants.MARGINS);
        mBitmapFont.draw(batch,"Press Space To Play with Player",mExtendViewport.getWorldWidth()/4,mExtendViewport.getWorldHeight()/2 - Constants.MARGINS*2);
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.SPACE)
        {
            mGame.isVsPlayer = true;
            mGame.showGameScreen();
            return true;
        }
        if(keycode == Input.Keys.A)
        {
            mGame.isVsPlayer = false;
            mGame.showGameScreen();
            return true;
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


}

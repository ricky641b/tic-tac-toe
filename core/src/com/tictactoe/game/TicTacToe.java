package com.tictactoe.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TicTacToe extends Game {

	GameScreen mGameScreen;
	SelectScreen mSelectScreen;
	EndScreen mEndScreen;
	public int xGames = 0;
	public int oGames = 0;
	public boolean isVsPlayer = true;
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		mGameScreen = new GameScreen(this);
		mSelectScreen = new SelectScreen(this);

		showStartScreen();
	}
	public void showGameScreen()
	{
		setScreen(mGameScreen);
	}
	public void showEndScreen(Constants.RESULT result)
	{
		mEndScreen = new EndScreen(this,result);
		setScreen(mEndScreen);
	}
	public void showStartScreen()
	{
		setScreen(mSelectScreen);
	}
}

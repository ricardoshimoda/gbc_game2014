package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WowGame extends GameBeta {
	TiledWindow theWindow;
	AnotherTiledWindow anotherTileWindow;
	int currentWindow = 0;
	@Override
	public void create () {
		super.create();
		theWindow = new TiledWindow();
		anotherTileWindow = new AnotherTiledWindow();
		setActiveScreen(theWindow);
		Gdx.app.log("Gdx version", com.badlogic.gdx.Version.VERSION);
	}


	@Override
	public void render () {
		super.render();

		if (currentWindow == 0&& theWindow.TransitionTo != "")
		{
			theWindow.TransitionTo = "";
			setActiveScreen(anotherTileWindow);
			currentWindow = 1;
		}

		if (currentWindow == 1 && anotherTileWindow.TransitionTo != "")
		{
			anotherTileWindow.TransitionTo = "";
			setActiveScreen(theWindow);
			currentWindow = 0;

		}
	}

	@Override
	public void dispose () {
	}
}

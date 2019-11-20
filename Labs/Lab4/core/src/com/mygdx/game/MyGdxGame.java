package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.base.GameBeta;

public class MyGdxGame extends GameBeta {

	ParticleScreen particleScreen;

	@Override
	public void create () {
		super.create();
		particleScreen = new ParticleScreen();
		setActiveScreen(particleScreen);
	}

	@Override
	public void render () {
	}
	
	@Override
	public void dispose () {
	}
}

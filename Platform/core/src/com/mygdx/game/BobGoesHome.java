package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.screen.LevelScreen;
import com.mygdx.game.screen.TitleScreen;

public class BobGoesHome extends GameBeta {
	LevelScreen levelScreen;
	TitleScreen titleScreen;

	static final int TITLE = 0;
	static final int LEVEL = 1;

	@Override
	public void create () {
		super.create();
		Gdx.app.log("Gdx version", com.badlogic.gdx.Version.VERSION);

		levelScreen = new LevelScreen();
		titleScreen = new TitleScreen();

		currentScene = LEVEL;
		setActiveScreen(levelScreen);	}

	@Override
	public void render () {
		switch (currentScene){
			case TITLE:
				/* Current Scene: Title
				 * Transitions from Title to Level Screen
				 */
				if(titleScreen.transitionTo == "Level"){
					resetScene(LEVEL);
					setActiveScreen(levelScreen);
				}
				break;
		}
		// Renders only after changing the scene
		super.render();
	}

	@Override
	protected void resetScene(int finalScene) {
		levelScreen.transitionTo = "";
		titleScreen.transitionTo = "";
		currentScene = finalScene;
	}

	@Override
	public void dispose () {
		levelScreen.dispose();
		titleScreen.dispose();
	}
}

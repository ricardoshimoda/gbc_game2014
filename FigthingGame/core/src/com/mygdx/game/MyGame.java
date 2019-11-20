package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.base.ScreenBeta;
import com.mygdx.game.scenes.DialogScene;
import com.mygdx.game.scenes.FightScene;
import com.mygdx.game.scenes.SelectionScene;
import com.mygdx.game.scenes.TitleScene;

public class MyGame extends GameBeta {

	TitleScene titleScene;
	SelectionScene selectionScene;
	FightScene fightScene;
	DialogScene dialogScene;

	public String Player = "";
	public String Enemy = "";

	public void setPlayerName (String playerName){
		Gdx.app.log("Selected","Setting Player 1: " + playerName);
		Player = playerName;
	}
	public void setEnemyName (String enemyName){
		Gdx.app.log("Selected","Setting Player 2: " + enemyName);
		Enemy = enemyName;
	}

	@Override
	public void create () {
		super.create();
		titleScene = new TitleScene();
		selectionScene = new SelectionScene( );
		fightScene = new FightScene();
		dialogScene = new DialogScene();
		// Sets the first scene
		currentScene = 0;
		setScreen(titleScene);
		//currentScene = 1;
		//setScreen(selectionScene);
		//currentScene = 2;
		//setScreen(dialogScene);
		//currentScene = 3;
		//setScreen(fightScene);
	}

	@Override
	public void render () {
		super.render();
		// Every time checks if there is any requesting a scene transition
		switch (currentScene){
			case 0:
				if(titleScene.transitionTo == "Selection"){
					Player = "";
					Enemy = "";
					resetScene(1);
					setActiveScreen(selectionScene);
				}
				break;
			case 1:
				if(selectionScene.transitionTo == "Title"){
					resetScene(0);
					setActiveScreen(titleScene);
				}
				if(selectionScene.transitionTo == "Dialog"){
					Preferences prefs = Gdx.app.getPreferences("FightGamePreferences");
					Gdx.app.log("Selected","Enemy: " + prefs.getString("Enemy")+ " - Player: " +
							prefs.getString("Player"));

					resetScene(2);
					setActiveScreen(dialogScene);
				}
				break;
			case 2:
				if(dialogScene.transitionTo == "Fight"){
					resetScene(3);
					setActiveScreen(fightScene);
				}
				break;
			case 3:
				if(fightScene.transitionTo == "Title"){
					Preferences prefs = Gdx.app.getPreferences("FightGamePreferences");
					prefs.putString("Enemy","");
					prefs.putString("Player","");
					prefs.flush();

					Player = "";
					Enemy = "";
					resetScene(0);
					setActiveScreen(titleScene);
				}
				break;
		}
	}
	
	@Override
	public void dispose () {
		titleScene.dispose();;
		selectionScene.dispose();
		fightScene.dispose();
		dialogScene.dispose();
	}

	@Override
	protected void resetScene(int finalScreen)
	{
		titleScene.transitionTo="";
		selectionScene.transitionTo="";
		dialogScene.transitionTo="";
		fightScene.transitionTo="";
		currentScene = finalScreen;
	}

}

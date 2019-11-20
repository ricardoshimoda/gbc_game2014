package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.base.GameBeta;
import com.mygdx.game.scenes.LevelOne;
import com.mygdx.game.scenes.Play;
import com.mygdx.game.scenes.Score;
import com.mygdx.game.scenes.Title;

/**
 * Created by markapptist on 2018-09-26.
 * Modified by Ricardo Shimoda Nakasako - 101128885 - 2018-10-05
 */

public class MyGame extends GameBeta {

    /*
     * Scenes
     */
    Title titleScene;
    Play playScene;
    Score scoreScene;
    LevelOne levelOne;

    String activeScreen;

    @Override
    public void create() {
        super.create();
        titleScene = new Title();
        playScene = new Play();
        scoreScene = new Score();
        /*
        -- Testing scenes

        -- Score
        setScreen(scoreScene);
        activeScreen = "Score";

        -- Title
        setScreen(titleScene);
        activeScreen = "Title";

        -- Play
        setScreen(playScene);
        activeScreen = "Play";
        */
        setScreen(titleScene);
        activeScreen = "Title";
    }

    private void resetScreen(String finalScreen)
    {
        titleScene.transitionTo="";
        playScene.transitionTo="";
        scoreScene.transitionTo="";
        activeScreen = finalScreen;
    }

    @Override
    public void render()
    {
        //clearWhite();
        if (activeScreen == "Title")
        {
            if (titleScene.transitionTo == "Play")
            {
                resetScreen("Play");
                playScene.initialize();
                setActiveScreen(playScene);
            }
            if (titleScene.transitionTo == "Score")
            {
                Gdx.app.log("Debug","Changing to Score");
                resetScreen("Score");
                setActiveScreen(scoreScene);
            }
        }
        if (activeScreen == "Score")
        {
            if (scoreScene.transitionTo == "Title")
            {
                Gdx.app.log("Debug","Changing to Title");
                resetScreen("Title");

                setActiveScreen(titleScene);
            }
        }
        if (activeScreen == "Play")
        {
            if (playScene.transitionTo == "Title")
            {
                Gdx.app.log("Debug","Changing to Title");
                resetScreen("Title");
                setActiveScreen(titleScene);
            }
        }
        super.render();

    }
}

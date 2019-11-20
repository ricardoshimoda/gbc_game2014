package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Actors.Background;
import com.mygdx.game.Actors.Bird;
import com.mygdx.game.Actors.GameTitle;
import com.mygdx.game.Actors.Ground;
import com.mygdx.game.base.ScreenBeta;

public final class Title extends ScreenBeta {

    Background background;

    Ground ground;
    Ground ground2;
    Ground[] grounds;

    Table formTable;
    Table formTable2;

    ImageButton startButton;
    ImageButton scoreButton;

    Bird titleBird;
    GameTitle gameTitle;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();


    @Override
    public void initialize() {
        //st.setDebugAll(true);

        // adds background and ground animation
        background = new Background(WIDTH/2, HEIGHT/2, st, WIDTH, HEIGHT);
        ground = new Ground(0,0, st, WIDTH, HEIGHT, 600);
        ground2 = new Ground(ground.getRealWidth(),0, st, WIDTH, HEIGHT, 600);

        // Adds buttons
        startButton = new ImageButton(GetButtonDrawable("BtnStart.png"));
        startButton.getImage().setFillParent(true);
        startButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                transitionTo = "Play";
                Gdx.app.log("Debug","Play Pressed From Title");
            }
        });
        formTable = new Table();
        formTable.setPosition(WIDTH / 10, HEIGHT - (7*HEIGHT / 8));
        formTable.add(startButton).size(350,350);
        st.addActor(formTable);

        scoreButton = new ImageButton(GetButtonDrawable("BtnScore.png"));
        scoreButton.getImage().setFillParent(true);
        scoreButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                transitionTo = "Score";
                Gdx.app.log("Debug","Score Pressed From Title");
            }
        });
        formTable2 = new Table();
        formTable2.setPosition(8*WIDTH / 13, HEIGHT - (7*HEIGHT / 8));
        formTable2.add(scoreButton).size(350,350);
        st.addActor(formTable2);

        // Adds game Title
        titleBird = new Bird(9*HEIGHT/12, HEIGHT);
        titleBird.setPosition(12*WIDTH/14, 9*HEIGHT/12);
        titleBird.setScale(8.0f);
        st.addActor(titleBird);
        gameTitle = new GameTitle(4*WIDTH/10, 9*HEIGHT/12, st, HEIGHT);
        gameTitle.setScale(7.5f);

        // Adds Theme music
        ///defaultBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Upbeat.wav"));
        //defaultBackgroundMusic.play();
        //defaultBackgroundMusic.setLooping(true);

        // Initialize prefences
        Preferences prefs = Gdx.app.getPreferences("FlappyBirdPreferences");
        if(!prefs.contains("HighScore"))
        {
            prefs.putInteger("HighScore", 0);
            prefs.flush();
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void update(float dt) {
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}

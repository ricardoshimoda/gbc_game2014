package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Actors.Background;
import com.mygdx.game.Actors.Bird;
import com.mygdx.game.Actors.GameTitle;
import com.mygdx.game.Actors.Ground;
import com.mygdx.game.base.ScreenBeta;

public final class Score extends ScreenBeta {
    Background background;

    Ground ground;
    Ground ground2;

    Table formTable;

    ImageButton menuButton;

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
        menuButton = new ImageButton(GetButtonDrawable("BtnMenu.png"));
        menuButton.getImage().setFillParent(true);
        menuButton.addListener(new ClickListener() {
            public void dfasfasdfasdfasdfasdfasdfasdfasdfasd(InputEvent event, float x, float y){
                transitionTo = "Title";
                Gdx.app.log("Debug","Title Pressed From Score");
            }
        });
        formTable = new Table();
        formTable.setPosition((WIDTH / 2)-175, HEIGHT - (7*HEIGHT / 8));
        formTable.add(menuButton).size(350,350);
        st.addActor(formTable);

        // Adds game Title
        titleBird = new Bird(9*HEIGHT/12, HEIGHT);
        titleBird.setPosition(12*WIDTH/14, 9*HEIGHT/12);
        titleBird.setScale(8.0f);
        st.addActor(titleBird);
        gameTitle = new GameTitle(4*WIDTH/10, 9*HEIGHT/12, st, HEIGHT);
        gameTitle.setScale(7.5f);

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

package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Actors.Ground;
import com.mygdx.game.Actors.Plane;
import com.mygdx.game.Actors.Sky;
import com.mygdx.game.base.ActorBeta;
import com.mygdx.game.base.ScreenBeta;

/**
 * Created by markapptist on 2018-09-26.
 */

public final class LevelOne extends ScreenBeta {

    Plane greenPlane;

    Ground ground;
    Ground ground2;

    Sky sky;
    Sky sky2;

    static Ground[] grounds;

    ActorBeta background;
    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    @Override
    public void initialize() {
/*
        sky = new Sky(0, 0, st);
        sky.setScale(3);

        sky2 = new Sky(800, 0, st);
        sky2.setScale(3);

        ground = new Ground(-190, 0, st, WIDTH, HEIGHT, 100);
        ground.setScale(2);

        ground2 = new Ground(ground.getWidth(), 0, st, WIDTH, HEIGHT, 100);
        ground2.setScale(2);

        grounds = new Ground[] {ground, ground2};

        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        greenPlane = new Plane();
        greenPlane.setPosition(250, 500);
        greenPlane.setScale(1.0f);
        st.addActor(greenPlane);

        defaultSoundEffect = Gdx.audio.newSound(Gdx.files.internal("sparkle.mp3"));
        defaultBackgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Prelude-and-Action.mp3"));

       // defaultBackgroundMusic.setPosition(20.0f);
        defaultBackgroundMusic.play();
        defaultBackgroundMusic.setLooping(true);*/
    }

    @Override
    public void hide() {

    }

    @Override
    public void update(float dt) {
/*
        this.st.getCamera().position.set(greenPlane.getX(), this.st.getCamera().position.y, this.st.getCamera().position.z);

        ActorBeta.setWorldBounds(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for(int i = 0; i < grounds.length; i++) {
            if(greenPlane.overlaps(grounds[i])) {
                greenPlane.preventOverlap(grounds[i]);
            }
        }*/



    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        /*
        super.touchDown(screenX, screenY, pointer, button);

        greenPlane.boost();

        defaultSoundEffect.play(1.0f);*/

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        super.touchUp(screenX, screenY, pointer, button);

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //super.touchDragged(screenX, screenY, pointer);

        return true;
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

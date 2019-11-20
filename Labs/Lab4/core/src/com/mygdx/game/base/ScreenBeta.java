package com.mygdx.game.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by markapptist on 2018-10-16.
 */

public abstract class ScreenBeta implements Screen, InputProcessor {

    public String transitionTo;
    protected Stage st;
    protected Music defaultBackgroundMusic;
    protected Sound defaultSoundEffect;
    boolean isPaused;

    public ScreenBeta()
    {
        isPaused = false;
        st = new Stage();
        initialize();
    }

    public abstract void initialize();

    /**
     *  Called when this becomes the active screen in a Game.
     *  Set up InputMultiplexer here, in case screen is reactivated at a later time.
     */
    @Override
    public void show() {
        //GET the InputMultiplexer
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        //Add InputProcessor to the screen
        im.addProcessor(this);
        //Add InputProcessor to the stage
        im.addProcessor(st);
    }

    /**
     *  Called when this is no longer the active screen in a Game.
     *  Screen class and Stages no longer process input.
     *  Other InputProcessors must be removed manually.
     */
    @Override
    public void hide() {
        //Get InputProcessor
        InputMultiplexer im = (InputMultiplexer)Gdx.input.getInputProcessor();
        //Remove InputProcessor
        im.removeProcessor(this);
        im.removeProcessor(st);
    }

    @Override
    public void resume() {
        isPaused = false;
    }

    @Override
    public void pause() {isPaused = true;}

    public boolean isPaused(){return isPaused;}

    public abstract void update(float dt);

    @Override
    public void render(float delta) {
        //PAUSE LOGIC
        if(isPaused)
            delta = 0;
        else {
            delta = Math.min(delta, 1/30.0f);
        }
        st.act(delta);
        update(delta); // whooosh
        st.setDebugAll(true);
        Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        st.draw();
    }

    public boolean isTouchDownEvent(Event e) {
        return (e instanceof InputEvent) && ((InputEvent)e).getType().equals(InputEvent.Type.touchDown);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public Drawable GetButtonDrawable(String fileName){
        // Trying to avoid using LibGDX Skins at all costs
        Texture texture = new Texture(Gdx.files.internal(fileName));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion textureRegion = new TextureRegion(texture);
        Drawable valueToReturn = new TextureRegionDrawable(textureRegion);
        return valueToReturn;
    }

}

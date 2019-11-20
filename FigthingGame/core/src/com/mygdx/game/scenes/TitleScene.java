package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.actors.Background;
import com.mygdx.game.actors.title.GameTitle;
import com.mygdx.game.base.ScreenBeta;

public final class TitleScene extends ScreenBeta {
    Background titleBackground;
    GameTitle gameTitle;
    Music slap;
    Music soundTrack;
    boolean hasntPlayed = true;
    boolean hasntPlayedSoundtrack = true;
    float soundTrackAcc = 0.0f;
    float comeAndGo = 0.0f;

    public  void initialize(){
       titleBackground = new Background(WIDTH/2, HEIGHT/2, st, WIDTH, HEIGHT, "Title/TitleScreen.jpg");
       titleBackground.setOrigin(0f,0f);
       titleBackground.setPosition(-0.1f*WIDTH, 0);
       gameTitle = new GameTitle(WIDTH/2, HEIGHT/2, st, WIDTH, HEIGHT);
       gameTitle.setOrigin(0f,0f);
       gameTitle.setPosition(-0.1f*WIDTH, 0);
       slap = Gdx.audio.newMusic(Gdx.files.internal("Title/Slap.wav"));
       soundTrack = Gdx.audio.newMusic(Gdx.files.internal("Title/TitleSoundtrack.wav"));
    }

    @Override
    public void show()
    {
        super.show();
        gameTitle.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(0.3f),Actions.fadeOut(1)));
        hasntPlayed=true;
        hasntPlayedSoundtrack=true;
    }
    @Override
    public void hide()
    {
        super.hide();
        soundTrack.stop();
    }

    public void update(float dt){
        if(hasntPlayed){
            hasntPlayed=false;
            slap.play();
        }
        if(hasntPlayedSoundtrack && ! hasntPlayed){
            soundTrackAcc +=dt;
            if(soundTrackAcc > 1.4f)
            {
                soundTrack.setLooping(true);
                soundTrack.setVolume(0.3f);
                soundTrack.play();// (only when not testing
            }
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        transitionTo = "Selection";
        // if anything else depends on this...
        return true;
    }


}

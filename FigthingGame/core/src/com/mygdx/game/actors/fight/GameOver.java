package com.mygdx.game.actors.fight;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class GameOver extends ActorBeta {
    public GameOver(float x, float y, Stage s) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture("Fight/GameOver.png");//"Title/TitleScreen.jpg"
    }
}

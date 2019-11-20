package com.mygdx.game.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class DeathTitle extends ActorBeta {
    public DeathTitle(float x, float y, Stage s) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture("DeathTitle.png");
    }
}

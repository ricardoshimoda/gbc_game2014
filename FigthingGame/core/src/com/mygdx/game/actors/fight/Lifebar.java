package com.mygdx.game.actors.fight;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class Lifebar extends ActorBeta {
    public Lifebar(float x, float y, Stage s) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture("Fight/Lifebar.png");
    }
}

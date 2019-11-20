package com.mygdx.game.actors.dialog;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class DialogBackground extends ActorBeta {
    public DialogBackground(float x, float y, Stage s) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture("Dialog/DialogBackground.png");
    }
}

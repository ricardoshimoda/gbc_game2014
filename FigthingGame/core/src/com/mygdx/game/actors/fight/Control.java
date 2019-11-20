package com.mygdx.game.actors.fight;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class Control extends ActorBeta {
    int controlPurpose;
    public int GetPurpose(){return controlPurpose;}
    public Control(float x, float y, Stage s, String texture, int purpose) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture(texture);
        controlPurpose = purpose;
    }

}

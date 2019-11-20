package com.mygdx.game.actors.selection;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class Plane extends ActorBeta {
    public Plane (float x, float y, Stage s) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture("Selection/Plane.png");//"Title/TitleScreen.jpg"
    }
}

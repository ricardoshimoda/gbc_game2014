package com.mygdx.game.actors.fight;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class Thumb extends ActorBeta {
    public Thumb(float x, float y, Stage s, String texturePath) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture(texturePath);//"Title/TitleScreen.jpg"
    }
}

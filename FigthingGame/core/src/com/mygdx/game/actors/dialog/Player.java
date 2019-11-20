package com.mygdx.game.actors.dialog;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public class Player extends ActorBeta {
    public Player (float x, float y, Stage s, String texturePath) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture(texturePath);//"Title/TitleScreen.jpg"
    }
}

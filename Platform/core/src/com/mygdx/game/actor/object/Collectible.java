package com.mygdx.game.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class Collectible extends ActorBeta {
    public boolean collected = false;
    public Collectible(float x, float y, Stage s, float scaling, String texturePath)
    {
        super(x * scaling, y * scaling, s);
        loadTexture(texturePath);
        setSize(scaling * getWidth(), scaling * getHeight());
    }

}

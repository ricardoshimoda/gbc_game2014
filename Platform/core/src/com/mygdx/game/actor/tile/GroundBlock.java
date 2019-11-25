package com.mygdx.game.actor.tile;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class GroundBlock extends ActorBeta {
    public boolean Collision = true;
    public GroundBlock(float x, float y, Stage s, float scaling, String texturePath)
    {
        super(x * scaling, y * scaling, s);
        loadTexture(texturePath);
        setSize(scaling * getWidth(), scaling * getHeight());
    }

}

package com.mygdx.game.actor.tile;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class SandBlock extends ActorBeta {
    private float scale;
    private Animation idle;
    public SandBlock(float x, float y, Stage s)
    {
        super(x, y, s);
        loadTexture("Level01/Tile/2.png");
    }
}

package com.mygdx.game.actor.UI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class DeathButton extends ActorBeta {
    private static final float screenHeightRatio = 0.3f;

    public DeathButton(float x, float y, Stage st, float screenHeight){
        super(x,y,st);
        loadTexture("UI/Retry.png");
        float factor = screenHeightRatio * screenHeight/getHeight();
        setSize(factor*getWidth(), factor*getHeight());
    }

}

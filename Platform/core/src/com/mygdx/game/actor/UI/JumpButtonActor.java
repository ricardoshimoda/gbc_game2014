package com.mygdx.game.actor.UI;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class JumpButtonActor extends ActorBeta {
    private static final float screenHeightRatio = 0.14f;

    public JumpButtonActor (float x, float y, Stage st, float screenHeight){
        super(x,y,st);
        loadTexture("UI/Jump.png");
        float factor = screenHeightRatio * screenHeight/getHeight();
        setSize(factor*getWidth(), factor*getHeight());
    }

}

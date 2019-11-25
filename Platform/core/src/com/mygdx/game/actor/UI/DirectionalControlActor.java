package com.mygdx.game.actor.UI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class DirectionalControlActor extends ActorBeta {
    private static final float screenHeightRatio = 0.14f;

    public DirectionalControlActor (float x, float y, Stage st, float screenHeight, boolean right){
        super(x,y,st);
        if(right){
            loadTexture("UI/Right.png");
        }else{
            loadTexture("UI/Left.png");
        }
        float factor = screenHeightRatio * screenHeight/getHeight();
        setSize(factor*getWidth(), factor*getHeight());
    }

}

package com.mygdx.game.actor.UI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.base.ActorBeta;

public final class ObjectActor extends ActorBeta {
    private static final float screenHeightRatio = 0.075f;
    public ObjectActor (float screenHeight){
        loadTexture("UI/Objects.png");
        float factor = screenHeightRatio * screenHeight/getHeight();
        setSize(factor*getWidth(), factor*getHeight());
    }

}

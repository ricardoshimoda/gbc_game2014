package com.mygdx.game.actor.UI;

import com.mygdx.game.base.ActorBeta;

public final class HourglassActor extends ActorBeta {
    private static final float screenHeightRatio = 0.075f;

    public HourglassActor (float screenHeight){
        loadTexture("UI/TimeIcon.png");
        float factor = screenHeightRatio * screenHeight/getHeight();
        setSize(factor*getWidth(), factor*getHeight());
    }

}


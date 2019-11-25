package com.mygdx.game.actor.UI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class NextButton extends ActorBeta {
    private static final float screenHeightRatio = 0.3f;

    public NextButton(float x, float y, Stage st, float screenHeight){
        super(x,y,st);
        loadTexture("UI/Next.png");
        float factor = screenHeightRatio * screenHeight/getHeight();
        setSize(factor*getWidth(), factor*getHeight());
    }

}

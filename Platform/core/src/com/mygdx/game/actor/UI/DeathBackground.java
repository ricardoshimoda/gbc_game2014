package com.mygdx.game.actor.UI;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.base.ActorBeta;

public final class DeathBackground extends ActorBeta {
    public DeathBackground (float x, float y, Stage s, float sizeX, float sizeY) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture("UI/White.png");
        float originalWidth = getWidth();
        float originalHeight = getHeight();
        setOrigin(Align.center);

        // Defines the largest ratio to cover the screen
        float ratioX = sizeX/originalWidth;
        float ratioY = sizeY/originalHeight;
        float finalScale = (ratioX > ratioY) ? ratioX:ratioY;

        setHeight(originalHeight * finalScale);
        setWidth(originalWidth * finalScale);

        // Centers the image regardless of what is passed on x and y
        setX((sizeX - getWidth())/2);
        setY((sizeY - getHeight())/2);
    }
}

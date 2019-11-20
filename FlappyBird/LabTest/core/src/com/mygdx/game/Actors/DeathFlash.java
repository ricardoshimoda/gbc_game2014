package com.mygdx.game.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class DeathFlash extends ActorBeta {
    public DeathFlash(float x, float y, Stage s, float sizeX, float sizeY) {

        // Setup basic stuff
        super(x, y, s);
        loadTexture("DeathFlash.png");

        // Defines the largest ratio to cover the screen
        float ratioX = sizeX/this.getWidth();
        float ratioY = sizeY/this.getHeight();
        if(ratioX > ratioY) {
            this.setScale(ratioX);
        }
        else {
            this.setScale(ratioY);
        }

        // Centers the image regardless of what is passed on x and y
        setX((sizeX - getWidth())/2);
        setY((sizeY - getHeight())/2);
    }

}

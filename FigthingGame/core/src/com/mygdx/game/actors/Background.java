package com.mygdx.game.actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class Background extends ActorBeta {
    public Background (float x, float y, Stage s, float sizeX, float sizeY, String texturePath) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture(texturePath);//"Title/TitleScreen.jpg"

        // Defines the largest ratio to cover the screen
        float ratioX = sizeX/this.getWidth();
        float ratioY = sizeY/this.getHeight();
        if(ratioX > ratioY) {
            this.setScale(ratioX*1.1f);
        }
        else {
            this.setScale(ratioY*1.1f);
        }

        // Centers the image regardless of what is passed on x and y
        setX((sizeX - getWidth())/2);
        setY((sizeY - getHeight())/2);
    }
}

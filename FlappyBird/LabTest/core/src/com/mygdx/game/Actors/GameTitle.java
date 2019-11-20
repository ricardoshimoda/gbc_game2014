package com.mygdx.game.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class GameTitle extends ActorBeta {
    private float startingY;
    private float screenY;
    public GameTitle(float x, float y, Stage s, float sizeY) {

        // Setup basic stuff
        super(x, y, s);
        loadTexture("Title.png");

        // Sets motion
        startingY = y;
        screenY = sizeY;
        setSpeed(100);
        setMotionAngle(90);
    }
    public void act(float dt) {
        super.act(dt);
        applyPhysics(dt);
        if (getY() > startingY + screenY/25) {
            setMotionAngle(-90);
        }
        else if (getY() < startingY - screenY/25) {
            setMotionAngle(90);
        }
    }

}

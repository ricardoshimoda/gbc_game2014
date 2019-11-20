package com.mygdx.game.Actors;

import com.mygdx.game.base.ActorBeta;

public final class Bird extends ActorBeta {
    private float startingY;
    private float screenY;
    public Bird(float y, float sizeY)
    {
        String[] birdAnim = {"Bird1.png", "Bird2.png", "Bird3.png"};
        loadAnimationFromFiles(birdAnim, 0.1f, true);
        this.setBoundaryRectangle();

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
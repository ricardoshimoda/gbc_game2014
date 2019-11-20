package com.mygdx.game.Actors;

import com.mygdx.game.base.ActorBeta;

public final class Plane extends ActorBeta {

    public Plane() {

        String[] planeAnim = {"planeGreen0.png", "planeGreen1.png", "planeGreen2.png"};

        loadAnimationFromFiles(planeAnim, 0.1f, true);

        this.setBoundaryRectangle();

        setMaxSpeed(800);

    }

    @Override
    public void act(float dt) {

        super.act(dt);

        setAcceleration(800);
        accelerateAtAngle(270);
        applyPhysics(dt);


        boundToWorld();
   }

    public void boost() {
        setSpeed(800);
        setMotionAngle(90);
    }
}

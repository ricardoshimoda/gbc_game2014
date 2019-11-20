package com.mygdx.game.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class Sky extends ActorBeta {

    public Sky(float x, float y, Stage s) {
        super(x, y, s);
        loadTexture("sky.png");
        setSpeed(40);
        setMotionAngle(180);

        this.setBoundaryRectangle();
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        applyPhysics(dt);

        if (getX() + getWidth() < 0) {
            moveBy(2 * getWidth(), 0);
        }
    }
}

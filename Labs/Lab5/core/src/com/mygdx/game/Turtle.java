package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Turtle extends ActorBeta {

    public Turtle(float x, float y, Stage s) {
        super(x, y, s);

        String[] filenames = {
                "turtle-1.png", "turtle-2.png", "turtle-3.png", "turtle-4.png", "turtle-5.png", "turtle-6.png"
        };

        loadAnimationFromFiles(filenames, 0.1f, true);

        setAcceleration(400);
        setMaxSpeed(100);
        setDeceleration(400);

        setBoundaryPolygon(8);
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT))
            accelerateAtAngle(100);
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT))
            accelerateAtAngle(0);
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            accelerateAtAngle(90);
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN))
            accelerateAtAngle(270);

        applyPhysics(dt);

        setAnimationPaused(!isMoving());

        if(getSpeed() > 0)
            setRotation(getMotionAngle());

//        boundToWorld();

    }
}

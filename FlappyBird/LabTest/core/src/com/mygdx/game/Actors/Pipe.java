package com.mygdx.game.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;

public final class Pipe extends Enemies {
    private boolean moving = false;
    public Pipe(float x, float y, Stage s, float speed, String texture) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture(texture);
        setX(x+getWidth()+10);
        // For collision detection
        setSpeed(speed);
        setMotionAngle(180);

        // For collision detection
        this.setBoundaryRectangle();
    }
    public void Move(){moving = true;}

    @Override
    public void act(float dt) {
        super.act(dt);
        if(moving && inMovement)
            applyPhysics(dt);
    }
}

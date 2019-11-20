package com.mygdx.game.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class Ground extends Enemies {

    private float scrProportion = 0.12f;
    private float scrBar = 1.5f;
    private float finalRatio;
    private float finalSizeX;

    public Ground(float x, float y, Stage s, float sizeX, float sizeY, float speed) {

        // Sets the basic data
        super(x, y, s);
        loadTexture("ground.png");
        finalSizeX=sizeX;

        // sets the ratio to cover the full length
        finalRatio = scrBar*sizeX/getWidth();
        setScale(finalRatio);

        setX(x+(getWidth()*finalRatio-getWidth())/2);
        // sets the height to be not too much
        setY(sizeY * scrProportion -(getHeight()/2 * finalRatio));

        // sets the speed - it is rather different on the title screen than in the game itself
        setSpeed(speed);
        setMotionAngle(180);

        // For collision detection
        this.setBoundaryRectangle();
    }

    public float getRealWidth(){return this.getWidth()*finalRatio;}
    public float getRealHeight(){return this.getHeight()*finalRatio;}

    @Override
    public void act(float dt) {
        super.act(dt);
        if(inMovement) {
            applyPhysics(dt);

            if (getX() + (getWidth() * finalRatio + getWidth()) / 2 < 0) {
                moveBy(2 * (getWidth() * finalRatio - getWidth()), 0);
            }
        }
    }
}

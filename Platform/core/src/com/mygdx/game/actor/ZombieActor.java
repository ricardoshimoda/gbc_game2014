package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class ZombieActor extends ActorBeta {
    public Animation<TextureRegion> idleR;
    public Animation<TextureRegion> walkR;
    public Animation<TextureRegion> idleL;
    public Animation<TextureRegion> walkL;
    String gender;

    private static final int idleFrames = 15;
    private static final int walkFrames = 10;
    private static final float screenHeightRatio = 0.25f;

    /* Enemy movement */
    public float startX;
    private float xRange;
    private ActorMovement currentMovement;
    private float mySpeed;

    public ZombieActor(float x, float y, Stage s, float scaling, float screenHeight, boolean male,  float xWidth, float speed)
    {
        super(x * scaling, y * scaling, s);
        startX = x * scaling;
        gender = male?"male":"female";
        mySpeed = speed;
        idleR = LoadAnimation(idleFrames, "MovingRight", "Idle");
        idleL = LoadAnimation(idleFrames, "MovingLeft", "Idle" );
        walkR = LoadAnimation(walkFrames, "MovingRight", "Walk");
        walkL = LoadAnimation(walkFrames, "MovingLeft", "Walk");
        factor = screenHeightRatio * screenHeight/getHeight();
        xRange = xWidth * scaling - getWidth() * factor;
        // Sets First Animation;
        setAnimation(idleL, true, true);
    }

    @Override
    public void act(float dt)
    {
        super.act(dt);
        Move(dt);
    }

    private void Move(float dt)
    {
        float pX = getX();

        if(pX <= startX) {
            currentMovement = ActorMovement.RIGHT;
            setAnimation(walkR, true);
        } else if (pX >= startX + xRange) {
            currentMovement = ActorMovement.LEFT;
            setAnimation(walkL,true);
        }

        if(currentMovement == ActorMovement.RIGHT){
            setX(pX+ mySpeed *dt);
        }
        else if(currentMovement == ActorMovement.LEFT){
            if(pX >= mySpeed*dt){
                setX(pX - mySpeed *dt);
            }
        }
    }

    private Animation<TextureRegion> LoadAnimation(int frameQtd, String orientation, String situation)
    {
        String[] animationFramePathList = new String[frameQtd];
        for (int i = 1; i <= frameQtd; i++){
            animationFramePathList[i-1] = "Baddie/"+ orientation + "/" + gender + "/" + situation + " ("+i+").png";
        }
        return loadAnimationFromFiles(animationFramePathList, 0.1f, true);
    }



}

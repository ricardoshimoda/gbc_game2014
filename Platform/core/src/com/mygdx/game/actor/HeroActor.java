package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.ActorBeta;

public final class HeroActor extends ActorBeta {

    private Animation<TextureRegion> idleR;
    private Animation<TextureRegion> walkR;
    private Animation<TextureRegion> jumpR;
    private Animation<TextureRegion> fallR;
    private Animation<TextureRegion> deadR;

    private Animation<TextureRegion> idleL;
    private Animation<TextureRegion> walkL;
    private Animation<TextureRegion> jumpL;
    private Animation<TextureRegion> fallL;
    private Animation<TextureRegion> deadL;

    private static final int idleFrames = 10;
    private static final int walkFrames = 10;
    private static final int jumpFrames = 8;
    private static final int fallFrames = 8;
    private static final int deadFrames = 10;
    private static final float screenHeightRatio = 0.25f;
    private float speed = 1.0f;

    public boolean orientation = true;
    public boolean grounded = true;

    public HeroActor(float screenHeight)
    {
        idleR = LoadAnimation(idleFrames, "MovingRight", "Idle");
        idleL = LoadAnimation(idleFrames, "MovingLeft", "Idle" );

        walkR = LoadAnimation(walkFrames, "MovingRight", "Walk");
        walkL = LoadAnimation(walkFrames, "MovingLeft", "Walk");

        /* NEVER IN LOOP */
        jumpR = LoadAnimation(jumpFrames, "MovingRight", "Jump");
        jumpL = LoadAnimation(jumpFrames, "MovingLeft", "Jump");

        fallR = LoadAnimation(fallFrames, "MovingRight", "Fall");
        fallL = LoadAnimation(fallFrames, "MovingLeft", "Fall");

        deadR = LoadAnimation(deadFrames, "MovingRight", "Dead");
        deadL = LoadAnimation(deadFrames, "MovingLeft", "Dead");

        factor = screenHeightRatio * screenHeight/getHeight();
        // Sets First Animation;
        setAnimation(idleR, true, true);
    }

    public void MoveActor()
    {

    }

    private Animation<TextureRegion> LoadAnimation(int frameQtd, String orientation, String situation)
    {
        String[] animationFramePathList = new String[frameQtd];
        for (int i = 1; i <= frameQtd; i++){
            animationFramePathList[i-1] = "Hero/"+ orientation + "/" + situation + " ("+i+").png";
        }
        return loadAnimationFromFiles(animationFramePathList, 0.07f, true);
    }


}

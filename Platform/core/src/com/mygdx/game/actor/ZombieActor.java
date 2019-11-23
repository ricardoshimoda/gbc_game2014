package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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


    public ZombieActor(boolean male, float screenHeight)
    {
        gender = male?"male":"female";
        idleR = LoadAnimation(idleFrames, "MovingRight", "Idle");
        idleL = LoadAnimation(idleFrames, "MovingLeft", "Idle" );
        walkR = LoadAnimation(walkFrames, "MovingRight", "Walk");
        walkL = LoadAnimation(walkFrames, "MovingLeft", "Walk");

        factor = screenHeightRatio * screenHeight/getHeight();
        // Sets First Animation;
        setAnimation(idleL, true, true);
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

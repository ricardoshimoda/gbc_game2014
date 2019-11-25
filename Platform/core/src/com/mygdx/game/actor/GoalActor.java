package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class GoalActor extends ActorBeta {
    public Animation<TextureRegion> idleR;
    public Animation<TextureRegion> idleL;
    private static final float screenHeightRatio = 0.25f;

    private static final int[] idleFrames = {
        10, 10, 10, 16, 15
    };


    public GoalActor(float x, float y, Stage s, float scaling, float screenHeight, int level)
    {
        super(x * scaling, y * scaling, s);
        idleR = LoadAnimation(level, "MovingRight");
        idleL = LoadAnimation(level, "MovingLeft" );

        factor = screenHeightRatio * screenHeight/getHeight();
        // Sets First Animation;
        setAnimation(idleL, true);
    }

    private Animation<TextureRegion> LoadAnimation(int level, String orientation)
    {
        int frameQtd = idleFrames[level-1];
        String[] animationFramePathList = new String[frameQtd];
        for (int i = 1; i <= frameQtd; i++){
            animationFramePathList[i-1] = "Level0" + level + "/Goal/"+ orientation + "/Idle ("+i+").png";
        }
        return loadAnimationFromFiles(animationFramePathList, 0.1f, true);
    }
}

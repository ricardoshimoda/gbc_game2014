package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.ActorBeta;

public final class GoalActor extends ActorBeta {
    public Animation<TextureRegion> idleR;
    public Animation<TextureRegion> idleL;
    private static final float screenHeightRatio = 0.25f;

    private static final int[] idleFrames = {
        10, 10, 10, 16, 15
    };

    private static final String[] Goals = {
        "You have to get 5 coins in total to open the passage",
        "You have to get 10 coins in total to open the passage",
        "You have to get 15 coins in total to open the passage",
        "You have to get 20 coins in total to open the passage"
    };

    public GoalActor(int level, float screenHeight)
    {
        idleR = LoadAnimation(level, "MovingRight");
        idleL = LoadAnimation(level, "MovingLeft" );

        factor = screenHeightRatio * screenHeight/getHeight();
        // Sets First Animation;
        setAnimation(idleL, true, true);
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

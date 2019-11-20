package com.mygdx.game.actors.fight;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.ActorBeta;

public final class Player extends ActorBeta {

    String currentName;
    int currentSide;
    int[] animationFrames;

    public Animation<TextureRegion> idle;
    public Animation<TextureRegion> walk;
    public Animation<TextureRegion> punch;
    public Animation<TextureRegion> block;
    public Animation<TextureRegion> hit;

    int[] ryuAnimationFrames    = {4, 5, 5, 1, 4};
    int[] blankaAnimationFrames = {4, 5, 5, 1, 2};
    int[] chunLiAnimationFrames = {4, 8, 3, 1, 1};

    public Player(String name, int side)
    {
        currentName = name;
        currentSide = side;
        LoadAnimations();
    }

    private void LoadAnimations()
    {
        if(currentName.contentEquals("Ryu")){
            Gdx.app.log("Selected","Getting Ryu Animations");
            animationFrames = ryuAnimationFrames;
        } else if (currentName.contentEquals("ChunLi")){
            Gdx.app.log("Selected","Getting Chun-Li Animations");
            animationFrames = chunLiAnimationFrames;
        } else if (currentName.contentEquals("Blanka")){
            Gdx.app.log("Selected","Getting Blanka Animations");
            animationFrames = blankaAnimationFrames;
        }

        // Idle Animations
        idle = loadAnimation  (animationFrames[0],"Idle");
        walk = loadAnimation  (animationFrames[1],"Walk");
        punch = loadAnimation (animationFrames[2],"Punch");
        block= loadAnimation  (animationFrames[3],"Block");
        hit = loadAnimation   (animationFrames[4],"Hit");
    }

    private Animation<TextureRegion> loadAnimation(int frameQtd, String situation)
    {
        String[] animationFramePathList = new String[frameQtd];
        for (int i = 0; i < frameQtd; i++){
            animationFramePathList[i] = "Fight/"+ currentName + "/" + currentSide + "P/" + situation + "/"+i+".png";
        }
        return loadAnimationFromFiles(animationFramePathList, 0.1f, true);
    }

}

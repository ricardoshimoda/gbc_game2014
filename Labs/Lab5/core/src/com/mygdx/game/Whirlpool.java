package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Whirlpool extends ActorBeta {

    public Whirlpool(float x, float y, Stage s){
        super(x, y, s);

        loadAnimationFromSheet("whirlpool.png", 2, 5, 0.1f, false);
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        if(isAnimationFinished())
            remove();
    }
}

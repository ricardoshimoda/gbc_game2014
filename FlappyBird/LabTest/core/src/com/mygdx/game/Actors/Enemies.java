package com.mygdx.game.Actors;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public class Enemies extends ActorBeta {
    public boolean inMovement = true;
    public Enemies(float x, float y, Stage s){
        super(x, y, s);
    }
    public void Stop()
    {
        inMovement = false;
    }
}


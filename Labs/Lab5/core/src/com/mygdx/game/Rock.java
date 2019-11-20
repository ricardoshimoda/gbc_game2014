package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Rock extends ActorBeta {

    public Rock(float x, float y, Stage s, float zoom) {
        super(x, y, s);
        loadTexture("rock.png");
        setHeight(zoom * getHeight());
        setWidth(zoom * getWidth());
        setBoundaryPolygon(4);
    }
}

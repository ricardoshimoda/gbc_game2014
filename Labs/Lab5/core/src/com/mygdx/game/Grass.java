package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Grass extends ActorBeta {

    public Grass(float x, float y, Stage s, float zoom) {
        super(x, y, s);
        loadTexture("grass_flowers1.png");
        setHeight(zoom * getHeight());
        setWidth(zoom * getWidth());
        setBoundaryPolygon(4);

    }
}

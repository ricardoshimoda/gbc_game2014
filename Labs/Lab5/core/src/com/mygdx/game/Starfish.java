package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Starfish extends ActorBeta {

    public boolean collected;

    public Starfish(float x, float y, Stage s, float zoom) {
        super(x, y, s);

        loadTexture("starfish.png");

        Action spin = Actions.rotateBy(30, 1);
        this.addAction(Actions.forever(spin));

        setBoundaryPolygon(8);
        setHeight(zoom * getHeight());
        setWidth(zoom * getWidth());

        collected = false;
    }
}

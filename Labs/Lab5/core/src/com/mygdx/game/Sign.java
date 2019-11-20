package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class Sign extends ActorBeta {

    private String text;

    private boolean viewing;

    public Sign(float x, float y, Stage s, float zoom) {
        super(x, y, s);
        loadTexture("sign.png");
        setHeight(zoom * getHeight());
        setWidth(zoom * getWidth());
        text = "mumbo jumbo mumbo jumbo";
        viewing = false;
    }

    public void setText(String t) {
        text = t;
    }

    public String getText() {
        return text;
    }

    public void setViewing(boolean v) {
        viewing = v;
    }

    public boolean isViewing() {
        return viewing;
    }
}

package com.mygdx.game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;

public final class TiledWindow extends ScreenBeta {
    public String TransitionTo = "";
    @Override
    public void initialize() {
    }

    @Override
    public void update(float dt) {
        TilemapActor.windowHeight = (int)HEIGHT;
        TilemapActor.windowWidth = (int)WIDTH;
        TilemapActor tma = new TilemapActor("map.tmx", mainStage, TILEMAP_ZOOM);
        for(MapObject obj:tma.getTileList("Grass")){
            MapProperties props = obj.getProperties();
            new Grass((float)props.get("x")*TILEMAP_ZOOM, (float)props.get("y")*TILEMAP_ZOOM, mainStage,TILEMAP_ZOOM);
        }

        for(MapObject obj:tma.getTileList("Rock")){
            MapProperties props = obj.getProperties();
            new Rock((float)props.get("x")*TILEMAP_ZOOM, (float)props.get("y")*TILEMAP_ZOOM, mainStage,TILEMAP_ZOOM);
        }

        for(MapObject obj:tma.getTileList("Starfish")){
            MapProperties props = obj.getProperties();
            new Starfish((float)props.get("x")*TILEMAP_ZOOM, (float)props.get("y")*TILEMAP_ZOOM, mainStage,TILEMAP_ZOOM);
        }

        for(MapObject obj:tma.getTileList("Sign")){
            MapProperties props = obj.getProperties();
            Sign s = new Sign((float)props.get("x")*TILEMAP_ZOOM, (float)props.get("y")*TILEMAP_ZOOM, mainStage,TILEMAP_ZOOM);
            s.setText("Hello World");
        }

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        TransitionTo = "theotherone";
        return super.touchDown(screenX, screenY, pointer, button);
    }
}

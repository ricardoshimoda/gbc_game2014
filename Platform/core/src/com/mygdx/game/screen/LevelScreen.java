package com.mygdx.game.screen;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.mygdx.game.actor.GoalActor;
import com.mygdx.game.actor.HeroActor;
import com.mygdx.game.actor.ZombieActor;
import com.mygdx.game.actor.tile.SandBlock;
import com.mygdx.game.base.ScreenBeta;
import com.mygdx.game.base.TilemapActor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;

public final class LevelScreen extends ScreenBeta {

    // UI elements
    Label mission;
    Label time;
    Label objectCounter;

    // Actors
    HeroActor hero;
    ZombieActor zombie;
    GoalActor goal;

    // Background



    // Tilemap objects

    /*
    private TiledMap tiledMap;
    private MapObjects tileObjects;
    private MapObjects objects;
    ArrayList<SandBlock> blocks;*/

    public void initialize() {
        LoadLevel(1);
        //mission
    }

    @Override
    public void show()
    {
        super.show();
        /*
        TilemapActor.windowHeight = (int)HEIGHT;
        TilemapActor.windowWidth = (int)WIDTH;
        TilemapActor tma = new TilemapActor("Level01/Level1Q.tmx", st, TILEMAP_ZOOM);

        for(MapObject obj:tma.getTileList("SandPlatform")){
            MapProperties props = obj.getProperties();
            new SandBlock((float)props.get("x")*TILEMAP_ZOOM, (float)props.get("y")*TILEMAP_ZOOM, st);
        }*/

        /*
        blocks = new ArrayList<SandBlock>();
        tiledMap = new TmxMapLoader().load("Level01/Level1Q.tmx");
        tileObjects = tiledMap.getLayers().get(0).getObjects();
        objects = tiledMap.getLayers().get(1).getObjects();

        for (int i = 0; i < objects.getCount(); i++)
        {
            float x = (float)objects.get(i).getProperties().get("x");
            float y = (float)objects.get(i).getProperties().get("y");
            blocks.add(new SandBlock(x, y, st));
        }
        */

        /*
        blocks = new ArrayList<>();
        enemies = new ArrayList<>();
        objects = tiledMap.getLayers().get(0).getObjects();*/
        /*
        hero = new HeroActor(HEIGHT);
        st.addActor(hero);
        hero.setPosition(0, 0.1f*HEIGHT);

        zombie = new ZombieActor(false, HEIGHT);
        st.addActor(zombie);
        zombie.setPosition(0.5f*WIDTH, 0.1f*HEIGHT);

        goal = new GoalActor(5,HEIGHT);
        st.addActor(goal);
        goal.setPosition(0.7f*WIDTH, 0.1f*HEIGHT);*/

        /*
        hero.setOrigin(0,0);
        hero.setPosition(0, 0.1f*HEIGHT);
        float playerRatio = 0.6f*HEIGHT/hero.getHeight();
        hero.setScale(playerRatio);

        zombie.setOrigin(0,0);
        float zombieRatio = 0.6f*HEIGHT/zombie.getHeight();
        zombie.setScale(playerRatio);*/
    }

    public void LoadLevel(int level){

    }

    @Override
    public void update(float dt) {
    }

    @Override
    public void hide(){
        super.hide();

    }

}
/*
tiledMap = new TmxMapLoader().load("level_" + currentLevel + ".tmx");
        blocks = new ArrayList<>();
        enemies = new ArrayList<>();
        objects = tiledMap.getLayers().get(0).getObjects();

        for (int i = 0; i < objects.getCount(); i++)
        {
            float x = (float)objects.get(i).getProperties().get("x");
            float y = (float)objects.get(i).getProperties().get("y");
            if ((float)objects.get(i).getProperties().get("height") == 96.0f)
                blocks.add(new BrickBlock(x, y, mainStage));
            else if ((float)objects.get(i).getProperties().get("height") < 96.0f)
                enemies.add(new Enemy(x, y, objects.get(i).getProperties().get("width", Float.class), currentLevel, mainStage));
            else if ((float)objects.get(i).getProperties().get("height") > 96.0f)
                exitGate = new Gate(x, y, mainStage);
        }
        for (int i = 0; i < blocks.size(); i++)
        {
            blocks.get(i).ResizeActor(6);
        }
        for (int i = 0; i < enemies.size(); i++)
        {
            enemies.get(i).ResizeActor(6);
        }
        exitGate.ResizeActor(6);*
* */
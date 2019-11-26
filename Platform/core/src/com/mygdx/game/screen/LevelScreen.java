package com.mygdx.game.screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.actor.GoalActor;
import com.mygdx.game.actor.HeroActor;
import com.mygdx.game.actor.ActorMovement;
import com.mygdx.game.actor.UI.Background;
import com.mygdx.game.actor.UI.DeathBackground;
import com.mygdx.game.actor.UI.DeathButton;
import com.mygdx.game.actor.UI.DirectionalControlActor;
import com.mygdx.game.actor.UI.HealthActor;
import com.mygdx.game.actor.UI.HourglassActor;
import com.mygdx.game.actor.UI.JumpButtonActor;
import com.mygdx.game.actor.UI.NextButton;
import com.mygdx.game.actor.UI.ObjectActor;
import com.mygdx.game.actor.ZombieActor;
import com.mygdx.game.actor.object.Collectible;
import com.mygdx.game.actor.tile.GroundBlock;
import com.mygdx.game.base.ActorBeta;
import com.mygdx.game.base.ScreenBeta;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.base.TilemapActor;

import java.util.ArrayList;
import java.util.Random;

public final class LevelScreen extends ScreenBeta {

    // UI elements
    Background background;
    Table uiElementsTable;
    Label mission;
    Label time;
    Label objectCounter;
    Label healthCounter;
    ObjectActor objectIcon;
    HealthActor healthIcon;
    HourglassActor hourglassIcon;
    DirectionalControlActor rightButton;
    DirectionalControlActor leftButton;
    JumpButtonActor jumpButton;
    DeathButton deathButton;
    Label deathLabel;
    DeathBackground deathBG;
    NextButton nextButton;

    // Actors
    HeroActor hero;
    GoalActor goal;

    int currentLevel;

    float levelTimer = 0.0f;
    int timeToComplete = 180;
    int items = 0;

    private static final String[] GOALS = {
        "You have to get 5 crates in total to open the passage",
        "You have to get 7 boxes in total to open the passage",
        "You have to get 10 tombs in total to open the passage",
        "You have to get 12 crystals in total to open the passage",
        "Greet your owner at the end of the level"
    };

    private static final int[] COUNTABLE_GOALS = {
            5,
            7,
            10,
            12,
            0
    };

    private static final String[] SKINS = {
         "Comic/comic-ui","Neon/neon-ui","Neon/neon-ui","Neon/neon-ui","Comic/comic-ui"
    };
    private static final float[] SKIN_FONT_SIZE = {
         2.0f,1.5f,1.5f,1.5f,2.0f
    };

    // Background
    // Tilemap objects
    private TilemapActor tma;
    private ArrayList<GroundBlock> ground;
    private ArrayList<GroundBlock> groundCollision;
    private ArrayList<Collectible> collectible;
    private ArrayList<ZombieActor> zombies;
    private boolean showDeath = false;
    private boolean showWin = false;
    private boolean win = false;

    Random randomGen;
    /*
    private TiledMap tiledMap;
    private MapObjects tileObjects;
    private MapObjects objects;;*/

    public void initialize() {
        randomGen = new Random();
        currentLevel=1;
        LoadLevel(currentLevel);
        //mission
    }

    @Override
    public void show()
    {
        super.show();
    }

    public void LoadLevel(int level){
        showWin = false;
        win = false;
        showDeath = false;
        timeToComplete = 180;
        items = 0;
        sk = new Skin(Gdx.files.internal("UI/Skin/" + SKINS[level-1] + ".json"));
        float labelFontSize = SKIN_FONT_SIZE[level-1];


        background = new Background(0,0,st,WIDTH,HEIGHT,level);

        hero = new HeroActor(HEIGHT, WIDTH, TILEMAP_ZOOM);
        hero.win = false;
        st.addActor(hero);
        hero.setPosition(0.5f*WIDTH, 0.3f*HEIGHT);

        tma = new TilemapActor("Level0" + level+ "/Map.tmx", st, TILEMAP_ZOOM);
        ActorBeta inicialMark = null;
        ground = new  ArrayList<GroundBlock>();
        groundCollision = new  ArrayList<GroundBlock>();
        for(MapObject obj:tma.getTileList("Ground")){
            MapProperties props = obj.getProperties();
            GroundBlock currentBlock = new GroundBlock((float)props.get("x"), (float)props.get("y"), st, TILEMAP_ZOOM,
                    "Level0" + level+ "/" + props.get("source"));
            ground.add(currentBlock);
            if(!props.containsKey("collision")){
                groundCollision.add(currentBlock);
            }
            if((float)props.get("x") <=  0.1f && inicialMark == null){
                inicialMark = currentBlock;
            }

        }

        for(MapObject obj:tma.getTileList("Goal")){
            MapProperties props = obj.getProperties();
            goal = new GoalActor((float)props.get("x"), (float)props.get("y"), st, TILEMAP_ZOOM, HEIGHT, level);
        }


        collectible = new ArrayList<Collectible>();
        for(MapObject obj:tma.getTileList("Collectible")){
            MapProperties props = obj.getProperties();
            Collectible currentCollect = new Collectible((float)props.get("x"), (float)props.get("y"), st, TILEMAP_ZOOM,
                    "Level0" + level+ "/" + props.get("source"));
            collectible.add(currentCollect);
        }

        zombies = new ArrayList<ZombieActor>();
        for(MapObject obj:tma.getTileList("Enemy")){
            MapProperties props = obj.getProperties();
            ZombieActor currentZombie = new ZombieActor(
                    (float)props.get("x"), (float)props.get("y"), st, TILEMAP_ZOOM, HEIGHT,
                    randomGen.nextBoolean(), (float)props.get("width"), ((1+(float)level/10) * 30 + 50 *randomGen.nextFloat()) * TILEMAP_ZOOM );
            zombies.add(currentZombie);
        }

        ArrayList<ActorBeta> allOtherActors = new ArrayList<ActorBeta>();
        allOtherActors.addAll(ground);
        allOtherActors.addAll(collectible);
        allOtherActors.add(goal);

        hero.setMoveAlong(allOtherActors);
        hero.setZombieMoveAlong(zombies);

        mission = new Label(GOALS[level-1], sk);
        mission.setFontScale(labelFontSize);
        time = new Label(Integer.toString(timeToComplete), sk);
        time.setFontScale(labelFontSize);
        objectCounter = new Label("X " + items, sk);
        objectCounter.setFontScale(labelFontSize);
        healthCounter = new Label("X "+ hero.currentHealth, sk);
        healthCounter.setFontScale(labelFontSize);
        objectIcon = new ObjectActor(HEIGHT);
        healthIcon = new HealthActor(HEIGHT);
        hourglassIcon = new HourglassActor(HEIGHT);

        uiElementsTable = new Table();
        uiElementsTable.setOrigin(Align.top);
        uiElementsTable.setPosition(WIDTH/2,11*HEIGHT/12);
        uiElementsTable.add(objectIcon).width(objectIcon.getWidth()).right();
        uiElementsTable.add(objectCounter).left().padLeft(10);
        uiElementsTable.add(hourglassIcon).width(hourglassIcon.getWidth()).right();
        uiElementsTable.add(time).left().padLeft(10);
        uiElementsTable.add(healthIcon).width(healthIcon.getWidth()).right();
        uiElementsTable.add(healthCounter).left().padLeft(10);
        uiElementsTable.row();

        uiElementsTable.add(mission).colspan(6);
        uiElementsTable.row();
        uiElementsTable.setDebug(false);
        st.addActor(uiElementsTable);

        rightButton = new DirectionalControlActor(0.15f * WIDTH,0.03f * HEIGHT,st, HEIGHT,true);
        leftButton = new DirectionalControlActor(0.05f * WIDTH,0.03f * HEIGHT,st, HEIGHT,false);
        jumpButton = new JumpButtonActor(0.85f * WIDTH,0.03f * HEIGHT,st, HEIGHT);

    }

    public void CharacterDeath()
    {
        if(!showDeath){
            showDeath = true;
            deathBG = new DeathBackground(0,0,st,WIDTH,HEIGHT);
            deathLabel = new Label("Retry?", sk);
            st.addActor(deathLabel);
            deathLabel.setOrigin(Align.center);
            deathLabel.setFontScale(3.0f);
            deathLabel.setPosition(0.45f*WIDTH,0.65f*HEIGHT);
            deathButton = new DeathButton(0.43f*WIDTH,0.3f*HEIGHT,st,HEIGHT);
        }
    }


    public void CharacterWin()
    {
        if(!showWin){
            showWin = true;
            deathBG = new DeathBackground(0,0,st,WIDTH,HEIGHT);
            deathLabel = new Label("Next Level", sk);
            st.addActor(deathLabel);
            deathLabel.setOrigin(Align.center);
            deathLabel.setFontScale(3.0f);
            deathLabel.setPosition(0.45f*WIDTH,0.65f*HEIGHT);
            nextButton = new NextButton(0.43f*WIDTH,0.3f*HEIGHT,st,HEIGHT);
            hero.StopMoving();
        }
    }
    @Override
    public void update(float dt) {
        if(hero.currentHealth > 0 && !showWin){
            // Certain things only matter when the player is alive
            levelTimer += dt;
            if(levelTimer >=1.0f)
            {
                levelTimer -=1.0f;
                timeToComplete--;
                time.setText(timeToComplete);
                if(timeToComplete == 0){
                    hero.Death();
                    CharacterDeath();
                }
            }
            Collision_Ground_Hero();
            Collision_Collectible_Hero();
            Collision_Zombie_Hero();
            Collision_Goal_Hero();
        }
        else if(hero.currentHealth <= 0 && !showDeath){
            CharacterDeath();
        }
        else {
            CharacterWin();
        }

    }

    @Override
    public void hide(){
        super.hide();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float realX = screenX;
        float realY = HEIGHT - screenY; // This guy is kind of inverted
        if(hero.currentHealth > 0)
        {
            if(!win){
                //Going Right
                if(IsTouchingButton(realX,realY,rightButton)){
                    hero.StartMoving(ActorMovement.RIGHT);
                } else if(IsTouchingButton(realX,realY,leftButton)){
                    hero.StartMoving(ActorMovement.LEFT);
                }
                if(IsTouchingButton(realX,realY,jumpButton)){
                    hero.StartJump();
                }
            } else if(win)
            {
                if(IsTouchingButton(realX,realY,nextButton)){
                    st.clear();
                    if(currentLevel < 5){
                        currentLevel++;
                        LoadLevel(currentLevel);
                    }
                }
            }
        }
        else
        {
            if(IsTouchingButton(realX,realY,deathButton)){
                st.clear();
                LoadLevel(currentLevel);
            }
        }

        return super.touchDown(screenX, screenY, pointer, button);
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        float realX = screenX;
        float realY = HEIGHT - screenY; // This guy is kind of inverted
        if(hero.currentHealth > 0)
        {
            if(IsTouchingButton(realX, realY, rightButton) ||
                    IsTouchingButton(realX, realY, leftButton)){
                hero.StopMoving();
            }
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    public void Collision_Goal_Hero(){
        if(!showWin) {
            float hY = hero.getY();
            float hX = hero.getX() + 0.2f * hero.getWidth();
            float hSizeY = hero.getHeight();
            float hSizeX = 0.52f * hero.getWidth();
            float gX = goal.getX();
            float gY = goal.getY() + 0.8f * goal.getHeight();
            float gSizeX = goal.getWidth();
            float gSizeY = 0.2f * goal.getHeight();

            if (hX < gX + gSizeX &&
                    hX + hSizeX > gX &&
                    hY < gY + gSizeY &&
                    hY + hSizeY > gY && items >= COUNTABLE_GOALS[currentLevel-1]) {
                win = true;
                hero.win = true;
                CharacterWin();
            }
        }
    }

    public void Collision_Zombie_Hero(){
        float hY = hero.getY();
        float hX = hero.getX() + 0.2f * hero.getWidth();
        float hSizeY = hero.getHeight();
        float hSizeX = 0.52f * hero.getWidth();
        for(int i = 0; i < zombies.size(); i++){
            // We con
            ZombieActor zB = zombies.get(i);
            float zX = zB.getX();
            float zY = zB.getY() + 0.8f * zB.getHeight();
            float zSizeX = zB.getWidth();
            float zSizeY = 0.2f * zB.getHeight();

            if (    hX< zX + zSizeX &&
                    hX + hSizeX > zX &&
                    hY < zY + zSizeY &&
                    hY + hSizeY > zY ) {
                hero.Hurt();
                healthCounter.setText("X " + hero.currentHealth);
                return;
            }
        }
    }

    public void Collision_Collectible_Hero()
    {
        float hY = hero.getY();
        float hX = hero.getX() + 0.2f * hero.getWidth();
        float hSizeY = hero.getHeight();
        float hSizeX = 0.52f * hero.getWidth();
        for(int i = 0; i < collectible.size(); i++){
            // We con
            Collectible cB = collectible.get(i);
            float cX = cB.getX();
            float cY = cB.getY() + 0.8f * cB.getHeight();
            float cSizeX = cB.getWidth();
            float cSizeY = 0.2f * cB.getHeight();

            if (    hX< cX + cSizeX &&
                    hX + hSizeX > cX &&
                    hY < cY + cSizeY &&
                    hY + hSizeY > cY && !cB.collected) {
                items++;
                objectCounter.setText("X " + items);
                cB.collected = true;
                cB.remove();
                return;
            }
        }
    }

    public void Collision_Ground_Hero()
    {
        if(hero.goingUp)
            return;
        // We consider only the feet of the hero
        float hY = hero.getY();
        float hX = hero.getX() + 0.3f * hero.getWidth();
        float hSizeY = 0.2f * hero.getHeight();
        float hSizeX = 0.3f * hero.getWidth();

        for(int i = 0; i < groundCollision.size(); i++){
            // We con
            GroundBlock cB = groundCollision.get(i);
            float gX = cB.getX();
            float gY = cB.getY() + 0.8f * cB.getHeight();
            float gSizeX = cB.getWidth();
            float gSizeY = 0.2f * cB.getHeight();

            if (    hX< gX + gSizeX &&
                    hX + hSizeX > gX &&
                    hY < gY + gSizeY &&
                    hY + hSizeY > gY) {
                hero.Land();
                return;
            }
        }
        hero.Flying();
    }

}

package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.game.Actors.Background;
import com.mygdx.game.Actors.DeathBoard;
import com.mygdx.game.Actors.DeathFlash;
import com.mygdx.game.Actors.DeathTitle;
import com.mygdx.game.Actors.Enemies;
import com.mygdx.game.Actors.GameTutorial;
import com.mygdx.game.Actors.Ground;
import com.mygdx.game.Actors.Pipe;
import com.mygdx.game.Actors.Player;
import com.mygdx.game.base.ActorBeta;
import com.mygdx.game.base.ScreenBeta;

import java.util.Random;

public final class Play extends ScreenBeta {

    Background background;

    // Ground control to Major Tom
    Ground ground;
    Ground ground2;

    GameTutorial gameTutorial;

    ImageButton pauseButton;
    //ImageButton resumeButton;

    Table formTable;
    Table scoreTable;

    // Peter Pipes
    Pipe pipeAbove1;
    Pipe pipeBelow1;
    Pipe pipeAbove2;
    Pipe pipeBelow2;

    Enemies[] enemies;

    boolean pipeEnsamble1;
    boolean pipeEnsemble2;

    Player player;

    static float WIDTH = Gdx.graphics.getWidth();
    static float HEIGHT = Gdx.graphics.getHeight();

    static float WORLD_SPEED = 150;
    static float DISTANCE_BETWEEN_PIPES = 1600;// i dont care
    float deltaPipeBelow = 250;

    static float delta = 10;

    static float tutorialTime = 5f;
    boolean tutorialEnd;
    static float deathTime = 1f;
    boolean deathEnd;
    float timeAccumulator = 0;

    Image scoreUnit;
    Image scoreDecimal;
    Image scoreHundreds;
    int score = 0;

    Texture[] numbers;

    Sound point;
    Sound hit;
    Sound death;
    Sound wings;

    boolean alive;

    DeathFlash deathFlash;
    DeathBoard deathBoard;
    DeathTitle deathTitle;
    ImageButton btnShare;
    ImageButton btnOk;
    float deathBoardFinalX;
    float deathBoardFinalY;
    Table deathButtonTable;
    Image deathScoreUnit;
    Image deathScoreDecimal;
    Image deathScoreHundreds;
    Table deathScoreTable;
    Image deathHighScoreUnit;
    Image deathHighScoreDecimal;
    Image deathHighScoreHundreds;
    Table deathHighScoreTable;
    float deathScoreFinalX;
    float deathScoreFinalY;
    float deathHighScoreFinalX;
    float deathHighScoreFinalY;

    @Override
    public void initialize() {

        tutorialEnd=false;
        deathEnd=false;
        alive = true;
        score = 0;
        pipeEnsamble1 = false;
        pipeEnsemble2 = false;

        ActorBeta.setWorldBounds(WIDTH, HEIGHT);

        st.setDebugAll(false);

        // adds background
        background = new Background(WIDTH/2, HEIGHT/2, st, WIDTH, HEIGHT);

        // Adds pipes
        pipeAbove1 = new Pipe(WIDTH,deltaPipeBelow+DISTANCE_BETWEEN_PIPES,st,WORLD_SPEED, "PipeAbove.png");
        pipeAbove1.setScale(4,10);
        pipeBelow1= new Pipe(WIDTH,deltaPipeBelow,st,WORLD_SPEED, "PipeBelow.png");
        pipeBelow1.setScale(4,10);

        Random degenerator = new Random();
        deltaPipeBelow = 500 * degenerator.nextFloat();// from 0 to 500;
        pipeAbove2 = new Pipe(WIDTH+WIDTH/2,deltaPipeBelow+DISTANCE_BETWEEN_PIPES,st,WORLD_SPEED, "PipeAbove.png");
        pipeAbove2.setScale(4,10);
        pipeBelow2= new Pipe(WIDTH+WIDTH/2,deltaPipeBelow,st,WORLD_SPEED, "PipeBelow.png");
        pipeBelow2.setScale(4,10);

        // adds ground
        ground = new Ground(0,0, st, WIDTH, HEIGHT, 150);
        ground2 = new Ground(ground.getRealWidth(),0, st, WIDTH, HEIGHT, WORLD_SPEED);

        // Organize enemies
        enemies = new Enemies[] {ground, ground2, pipeAbove1, pipeBelow1, pipeAbove2, pipeBelow2};


        // Adds game tutorial
        gameTutorial = new GameTutorial(6*WIDTH/13, 7*HEIGHT/12, st);
        gameTutorial.setScale(7.5f);
        gameTutorial.addAction(Actions.sequence(Actions.fadeOut(tutorialTime)));

        // Adds pause/unpause button
        pauseButton = new ImageButton(GetButtonDrawable("BtnPause.png"), GetButtonDrawable("BtnPause.png"), GetButtonDrawable("BtnResume.png") );
        pauseButton.getImage().setFillParent(true);

        // Pause/Resume action
        pauseButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
            if(!isPaused()) {
                pauseButton.setChecked(true);
                pause();
            }
            else{
                pauseButton.setChecked(false);
                resume();
            }
            }
        });

        formTable = new Table();
        formTable.setPosition(WIDTH / 12, 11*HEIGHT / 12);
        formTable.add(pauseButton).size(100,100);
        st.addActor(formTable);

        // player
        player = new Player(ground.getRealHeight()/2 /* Just to really bury him */);
        player.setPosition(WIDTH/4,850);
        player.setScale(10);
        st.addActor(player);

        defaultSoundEffect = Gdx.audio.newSound(Gdx.files.internal("Wings.wav"));
        point = Gdx.audio.newSound(Gdx.files.internal("Point.wav"));
        hit = Gdx.audio.newSound(Gdx.files.internal("Hit.wav"));
        death = Gdx.audio.newSound(Gdx.files.internal("Death.wav"));
        wings = Gdx.audio.newSound(Gdx.files.internal("Wings.wav"));

        // Score
        score = 0;
        numbers = new Texture[]{
            new Texture(Gdx.files.internal("Score/0.png")),
            new Texture(Gdx.files.internal("Score/1.png")),
            new Texture(Gdx.files.internal("Score/2.png")),
            new Texture(Gdx.files.internal("Score/3.png")),
            new Texture(Gdx.files.internal("Score/4.png")),
            new Texture(Gdx.files.internal("Score/5.png")),
            new Texture(Gdx.files.internal("Score/6.png")),
            new Texture(Gdx.files.internal("Score/7.png")),
            new Texture(Gdx.files.internal("Score/8.png")),
            new Texture(Gdx.files.internal("Score/9.png"))
        };

        scoreUnit = new Image(numbers[0]);
        scoreDecimal = new Image(numbers[0]);
        scoreHundreds = new Image(numbers[0]);

        scoreTable = new Table();
        scoreTable.setPosition(WIDTH / 2+50, 14*HEIGHT / 15);
        scoreTable.add(scoreHundreds).size(50,100);
        scoreTable.add(scoreDecimal).size(50,100);
        scoreTable.add(scoreUnit).size(50,100);

        scoreDecimal.setVisible(false);
        scoreHundreds.setVisible(false);
        st.addActor(scoreTable);

        // Death
        deathFlash = new DeathFlash(0,0,st, WIDTH, HEIGHT);
        deathFlash.addAction(Actions.sequence(Actions.fadeOut(0)));

        deathTitle = new DeathTitle(6*WIDTH/13, 9*HEIGHT/12, st);
        deathTitle.setScale(7.5f);
        deathTitle.addAction(Actions.fadeOut(0));

        //deathFlash.setVisible(false);
        deathBoardFinalX =6*WIDTH/13;
        deathBoardFinalY = 6*HEIGHT/12;

        deathBoard = new DeathBoard(6*WIDTH/13, 0, st);
        deathBoard.setScale(7.5f);
        deathBoard.setY(-8.5f/2f*deathBoard.getHeight());

        btnShare = new ImageButton(GetButtonDrawable("BtnShare.png"));
        btnShare.getImage().setFillParent(true);
        btnShare.setVisible(false);

        btnOk = new ImageButton(GetButtonDrawable("BtnOk.png"));
        btnOk.getImage().setFillParent(true);
        btnOk.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                transitionTo = "Title";
                Gdx.app.log("Debug","Title Pressed From Play");
            }
        });
        btnOk.setVisible(false);

        deathButtonTable =  new Table();
        deathButtonTable.setPosition(5*WIDTH / 13, 2*HEIGHT / 12);
        deathButtonTable.add(btnOk).size(350,350).padRight(50);
        deathButtonTable.add(btnShare).size(350,350);
        st.addActor(deathButtonTable);

        deathScoreUnit = new Image(numbers[0]);
        deathScoreDecimal  = new Image(numbers[0]);
        deathScoreHundreds  = new Image(numbers[0]);
        deathScoreTable = new Table();
        deathScoreTable.setPosition(3*WIDTH / 4+40, 8*HEIGHT / 15+20);
        deathScoreTable.add(deathScoreHundreds).size(30,60);
        deathScoreTable.add(deathScoreDecimal).size(30,60);
        deathScoreTable.add(deathScoreUnit).size(30,60);
        st.addActor(deathScoreTable);
        deathScoreTable.setY(-8.5f/2f*deathBoard.getHeight());
        deathScoreDecimal.setVisible(false);
        deathScoreHundreds.setVisible(false);

        deathHighScoreUnit = new Image(numbers[0]);
        deathHighScoreDecimal  = new Image(numbers[0]);
        deathHighScoreHundreds  = new Image(numbers[0]);
        deathHighScoreTable = new Table();
        deathHighScoreTable.setPosition(3*WIDTH / 4+40, 7*HEIGHT / 15-20);
        deathHighScoreTable.add(deathHighScoreHundreds).size(30,60);
        deathHighScoreTable.add(deathHighScoreDecimal).size(30,60);
        deathHighScoreTable.add(deathHighScoreUnit).size(30,60);
        st.addActor(deathHighScoreTable);
        deathHighScoreTable.setY(-8.5f/2f*deathBoard.getHeight());
        deathHighScoreDecimal.setVisible(false);
        deathHighScoreHundreds.setVisible(false);

        deathScoreFinalX = 3*WIDTH / 4+40;
        deathScoreFinalY = 8*HEIGHT / 15+20;
        deathHighScoreFinalX =3*WIDTH / 4+40;
        deathHighScoreFinalY=7*HEIGHT / 15-20;


    }
    @Override
    public void hide() {

    }

    @Override
    public void update(float dt) {
        if(!tutorialEnd)
        {
            timeAccumulator += dt;
            // After tutorial let it go, let it go
            if(timeAccumulator > tutorialTime )
            {
                tutorialEnd = true;
                pipeAbove1.Move();
                pipeAbove2.Move();
                pipeBelow1.Move();
                pipeBelow2.Move();
                player.Move();
            }
        }
        // Piping the pipes
        if(pipeAbove1.getX() + pipeAbove1.getWidth()/2 < 0){
            // Reposition Pipe 1
            Random degenerator = new Random();
            deltaPipeBelow = 500 * degenerator.nextFloat();
            pipeAbove1.setY(deltaPipeBelow+DISTANCE_BETWEEN_PIPES);
            pipeBelow1.setY(deltaPipeBelow);
            pipeAbove1.setX(WIDTH+pipeAbove1.getWidth()/2);
            pipeBelow1.setX(WIDTH+pipeAbove1.getWidth()/2);
            pipeEnsamble1 = false;
        }
        if(pipeAbove2.getX() + pipeAbove2.getWidth()/2 < 0){
            // Reposition Pipe 1
            Random degenerator = new Random();
            deltaPipeBelow = 500 * degenerator.nextFloat();
            pipeAbove2.setY(deltaPipeBelow+DISTANCE_BETWEEN_PIPES);
            pipeBelow2.setY(deltaPipeBelow);
            pipeAbove2.setX(WIDTH+pipeAbove2.getWidth()/2);
            pipeBelow2.setX(WIDTH+pipeAbove2.getWidth()/2);
            pipeEnsemble2 = false;
        }
        // Checking for death
        if(player.IsAlive())
        {
            // Checking for score
            if(player.getX() <  pipeAbove1.getX() + delta && player.getX() > pipeAbove1.getX() - delta  && !pipeEnsamble1)
            {
                pipeEnsamble1=true;
                point.play(1.0f);
                score++;
                UpdateScore();
            }
            if(player.getX() <  pipeAbove2.getX() + delta && player.getX() > pipeAbove2.getX() - delta && !pipeEnsemble2)
            {
                pipeEnsemble2=true;
                point.play(1.0f);
                score++;
                UpdateScore();
            }

            // Checking for death
            for(int i = 0; i < enemies.length; i++) {
                if(player.overlaps(enemies[i])) {
                    player.Death();
                    hit.play(1.0f);
                    deathFlash.addAction(Actions.sequence(Actions.fadeIn(0.2f),Actions.fadeOut(0.2f)));
                    alive = false;
                    timeAccumulator = 0;
                    break;
                }
            }
            if(!alive){
                for(int i = 0; i < enemies.length; i++) {
                    enemies[i].Stop();
                    Preferences prefs = Gdx.app.getPreferences("FlappyBirdPreferences");
                    int currentHS = prefs.getInteger("HighScore");
                    if(score > currentHS)
                    {
                        prefs.putInteger("HighScore", score);
                        prefs.flush();
                    }
                }
            }
        }
        else if(!deathEnd){
            timeAccumulator += dt;
            if(timeAccumulator > deathTime )
            {
                deathEnd= true;
                death.play(1.0f);
                // Show the death screen
                UpdateDeathScore();
                deathTitle.addAction(Actions.sequence(Actions.fadeIn(0.25f), Actions.moveBy(0, 10, 0.25f)));
                deathBoard.addAction(Actions.moveTo(deathBoardFinalX, deathBoardFinalY, 0.5f));
                deathHighScoreTable.addAction(Actions.moveTo(deathHighScoreFinalX, deathHighScoreFinalY, 0.5f));
                deathScoreTable.addAction(Actions.moveTo(deathScoreFinalX, deathScoreFinalY, 0.5f));
                btnShare.setVisible(true);
                btnOk.setVisible(true);

            }
        }
    }

    private void UpdateDeathScore()
    {
        int rest = score % 10;
        deathScoreUnit.setDrawable(new SpriteDrawable(new Sprite(numbers[rest])));
        if(score >= 10){
            int dec = (score/10)%10;
            deathScoreDecimal.setDrawable(new SpriteDrawable(new Sprite(numbers[dec])));
            deathScoreDecimal.setVisible(true);
        }
        if(score >= 100){
            int hund = (score/100)%10;
            deathScoreHundreds.setDrawable(new SpriteDrawable(new Sprite(numbers[hund])));
            deathScoreHundreds.setVisible(true);
        }

        Preferences prefs = Gdx.app.getPreferences("FlappyBirdPreferences");
        int currentHS = prefs.getInteger("HighScore");
        rest = currentHS % 10;
        deathHighScoreUnit.setDrawable(new SpriteDrawable(new Sprite(numbers[rest])));
        if(currentHS >= 10){
            int dec = (currentHS/10)%10;
            deathHighScoreDecimal.setDrawable(new SpriteDrawable(new Sprite(numbers[dec])));
            deathHighScoreDecimal.setVisible(true);
        }
        if(currentHS >= 100){
            int hund = (currentHS/100)%10;
            deathHighScoreHundreds.setDrawable(new SpriteDrawable(new Sprite(numbers[hund])));
            deathHighScoreHundreds.setVisible(true);
        }
    }

    private void UpdateScore()
    {
        int rest = score % 10;
        scoreUnit.setDrawable(new SpriteDrawable(new Sprite(numbers[rest])));
        if(score >= 10){
            int dec = (score/10)%10;
            scoreDecimal.setDrawable(new SpriteDrawable(new Sprite(numbers[dec])));
            scoreDecimal.setVisible(true);
        }
        if(score >= 100){
            int hund = (score/100)%10;
            scoreHundreds.setDrawable(new SpriteDrawable(new Sprite(numbers[hund])));
            scoreHundreds.setVisible(true);
        }
    }


    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        if(alive) {
            player.boost();
            wings.play(1.0f);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {

    }
}

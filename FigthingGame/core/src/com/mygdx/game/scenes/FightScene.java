package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.MyGame;
import com.mygdx.game.actors.Background;
import com.mygdx.game.actors.fight.Control;
import com.mygdx.game.actors.fight.DirectionalControl;
import com.mygdx.game.actors.fight.FightResult;
import com.mygdx.game.actors.fight.GameOver;
import com.mygdx.game.actors.fight.Lifebar;
import com.mygdx.game.actors.fight.Player;
import com.mygdx.game.actors.fight.Thumb;
import com.mygdx.game.base.ScreenBeta;

public final class FightScene extends ScreenBeta {

    String playerName;
    String enemyName;

    Background background;
    Control left;
    Control right;
    Control punch;
    Control block;
    Lifebar playerBar;
    Lifebar enemyBar;
    Thumb playerThumb;
    Thumb enemyThumb;
    Label timerDisplay;
    Skin mySkin;

    Player player;
    Player enemy;

    Music fightMusic;
    Sound hit;
    Sound miss;

    int duration;
    float timer = 0.0f;
    float playerLife = 0;
    float enemyLife = 0;
    float originalWidth = 0;

    boolean moveLeft = false;
    boolean moveRight = false;

    GameOver gameOverPopUp;
    FightResult playerResult;
    FightResult enemyResult;
    Control restartButton;
    boolean gameOver;
    Boolean activeScene;
    Boolean checkCollision = false;

    float playerSpeed = 1000f;

    boolean isPunching = false;
    boolean isHurting = false;


    public FightScene(){
        super();
    }
    public void initialize()
    {
        fightMusic = Gdx.audio.newMusic(Gdx.files.internal("Fight/DannyTheDog.mp3"));
        fightMusic.setVolume(0.3f);
        fightMusic.setLooping(true);

        hit = Gdx.audio.newSound(Gdx.files.internal("Fight/Hit.wav"));
        miss = Gdx.audio.newSound(Gdx.files.internal("Fight/Miss.wav"));

        duration = 180;
        playerLife = 100;
        enemyLife = 100;
        timer = 0;
        gameOver = false;

        mySkin = new Skin(Gdx.files.internal("Skin/arcade/skin/arcade-ui.json"));
    }

    @Override
    public void show(){
        super.show();

        // Loads basic data
        duration = 180;
        playerLife = 100;
        enemyLife = 100;
        activeScene = true;
        gameOver = false;
        checkCollision = false;

        Preferences prefs = Gdx.app.getPreferences("FightGamePreferences");

        enemyName = prefs.getString("Enemy");
        playerName = prefs.getString("Player");

        //fightMusic.play();

        background = new Background(WIDTH/2, HEIGHT/2, st, WIDTH, HEIGHT, "Fight/" + playerName + "Stage.png");

        player = new Player(playerName, 1);
        st.addActor(player);
        player.setOrigin(0,0);
        player.setPosition(0, 0.1f*HEIGHT);
        float playerRatio = 0.6f*HEIGHT/player.getHeight();
        player.setScale(playerRatio);

        enemy = new Player(enemyName, 2);
        st.addActor(enemy);
        enemy.setOrigin(0,0);
        enemy.setPosition(0.6f*WIDTH, 0.1f*HEIGHT);
        float enemyRatio = 0.6f*HEIGHT/enemy.getHeight();
        enemy.setScale(enemyRatio);

        left = new Control(0.05f*WIDTH, 0.05f*HEIGHT, st, "Fight/Left.png",0);
        left.setSize(0.1f*WIDTH,0.1f*WIDTH);
        right  = new Control(0.15f*WIDTH, 0.05f*HEIGHT, st, "Fight/Right.png",1);
        right.setSize(0.1f*WIDTH,0.1f*WIDTH);
        punch = new Control(0.70f*WIDTH, 0.05f*HEIGHT, st, "Fight/Punch.png", 2);
        punch.setSize(0.1f*WIDTH,0.1f*WIDTH);
        block= new Control(0.85f*WIDTH, 0.05f*HEIGHT, st, "Fight/Block.png", 3);
        block.setSize(0.1f*WIDTH,0.1f*WIDTH);

        timerDisplay = new Label("", mySkin);
        timerDisplay.setFontScale(3f);
        timerDisplay.setPosition(0.5f*WIDTH, 0.940f*HEIGHT);
        timerDisplay.setAlignment(Align.center);
        st.addActor(timerDisplay);
        updateTimer();

        playerBar = new Lifebar(0.06f*WIDTH, 0.9f*HEIGHT, st);
        playerBar.setSize(0.4f*WIDTH, 0.07f*HEIGHT);
        enemyBar = new Lifebar(0.54f*WIDTH, 0.9f*HEIGHT, st);
        enemyBar.setSize(0.4f*WIDTH, 0.07f*HEIGHT);
        enemyBar.setOrigin(0,0);
        originalWidth = 0.4f*WIDTH;
        playerThumb = new Thumb(0.01f*WIDTH, 0.9f*HEIGHT, st, "Fight/"+playerName+"1PW.png");
        enemyThumb = new Thumb(0.95f*WIDTH, 0.9f*HEIGHT, st, "Fight/"+enemyName+"2PW.png");

    }

    @Override
    public void hide(){

        background.remove();
        left.remove();
        right.remove();
        punch.remove();
        block.remove();
        timerDisplay.remove();
        playerBar.remove();
        enemyBar.remove();
        gameOverPopUp.remove();
        playerResult.remove();
        enemyResult.remove();
        restartButton.remove();
        playerThumb.remove();
        enemyThumb.remove();
        player.remove();
        enemy.remove();


        background = null;
        left = null;
        right = null;
        punch = null;
        block = null;
        timerDisplay = null;
        playerBar = null;
        enemyBar = null;
        gameOverPopUp = null;
        playerResult = null;
        enemyResult = null;
        restartButton = null;
        playerThumb = null;
        enemyThumb= null;
        player = null;
        enemy = null;

        activeScene = false;
        fightMusic.stop();
    }

    public void update(float dt)
    {
        if(gameOver)
            return;
        //
        if(duration > 0)
        {
            timer += dt;
            if(timer > 1){
                duration--;
                timer -= 1;
                updateTimer();
            }
        }
        else if(!gameOver)
        {
            gameOver = true;
            GameOverScreen();
        }
        if(moveLeft && player.getX() > playerSpeed*dt){
            player.moveBy(-playerSpeed*dt,0);
        }
        else if(moveRight && player.getX() < enemy.getX() + 0.05f*enemy.getWidth() - playerSpeed*dt){
            player.moveBy(playerSpeed*dt,0);
        }
        if(isPunching)
        {
            if(player.isAnimationFinished()){
                isPunching = false;
                player.setAnimation(player.idle, true);
                //checkCollision = false;
            }
            else
            {
                if(player.getX() > enemy.getX() -  0.15f*enemy.getWidth() && !checkCollision)
                {
                    checkCollision = true;
                    enemy.setAnimation(enemy.hit, false);
                    isHurting = true;
                    enemyLife -= 10;
                    updateLifeBar();
                    hit.play();
                    if(enemyLife <=0)
                    {
                        gameOver = true;
                        GameOverScreen();
                    }
                }
                else if(!checkCollision){
                    checkCollision = true;
                    miss.play();
                }

            }
        }
        if(isHurting){
            if(enemy.isAnimationFinished()){
                enemy.setAnimation(enemy.idle,true);

            }
        }

    }

    public void updateLifeBar(){
        float newWidth = originalWidth * enemyLife/100f;
        float withdDiff = enemyBar.getWidth() - newWidth;
        enemyBar.setWidth(newWidth);
        enemyBar.moveBy(withdDiff,0);
    }
    public void updateTimer()
    {
        int minutes = duration/60;
        int seconds = duration%60;
        //timerDisplay.setText(minutes + ":" + seconds);
        timerDisplay.setText(duration);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        float realY = HEIGHT - screenY;
        if(!activeScene)
            return false;
        Gdx.app.log("Position","x: " + screenX + "y:" + (HEIGHT-screenY));
        Gdx.app.log("Position","player: " + player.getX() + "enemy:" + enemy.getX());

        if(gameOver){
            if(screenX > restartButton.getX() && screenX < restartButton.getX()+restartButton.getWidth() &&
                    realY > restartButton.getY() && realY < restartButton.getY()+restartButton.getHeight()
            ){
                transitionTo="Title";

            }
            return false;
        }

        // Goes Left
        if(screenX > left.getX() && screenX < left.getX() + left.getWidth() &&
                realY > left.getY() && realY < left.getY() + left.getHeight()
        ){
            moveLeft = true;
            moveRight = false;
            player.setAnimation(player.walk, true);
        }

        // Goes Right
        if(screenX > right.getX() && screenX < right.getX() + right.getWidth() &&
                realY > right.getY() && realY < right.getY() + right.getHeight()
        ){
            moveLeft = false;
            moveRight = true;
            player.setAnimation(player.walk, true);
        }

        if(screenX > punch.getX() && screenX < punch.getX() + punch.getWidth() &&
                realY > punch.getY() && realY < punch.getY() + punch.getHeight() && !isPunching
        ){
            player.setAnimation(player.punch, false);
            isPunching = true;
            checkCollision = false;
        }

        if(screenX > block.getX() && screenX < block.getX() + block.getWidth() &&
                realY > block.getY() && realY < block.getY() + block.getHeight()
        ){
            player.setAnimation(player.block, true);
        }

        return true;
    }
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        moveLeft = false;
        moveRight = false;
        if(!isPunching)
        {
            player.setAnimation(player.idle, true);
        }
        return false;
    }
    public void GameOverScreen()
    {
        String Player1Result = "";
        String Player2Result = "";
        if(playerLife >= enemyLife){
            // This is a Win
            Player1Result = "Fight/"+ playerName+"1PW.png";
        }
        else {
            Player1Result = "Fight/"+ playerName+"1PL.png";
        }
        if(enemyLife >= playerLife){
            // This is a Win
            Player2Result = "Fight/"+ enemyName+"1PW.png";
        }
        else {
            Player2Result = "Fight/"+ enemyName+"1PL.png";
        }

        gameOverPopUp = new GameOver(0,0,st);
        float gameOverRatio = gameOverPopUp.getWidth()/gameOverPopUp.getHeight();
        gameOverPopUp.setSize(0.8f*HEIGHT*gameOverRatio, 0.8f*HEIGHT);
        gameOverPopUp.setPosition((WIDTH-gameOverPopUp.getWidth())/2,0.1f*HEIGHT);

        playerResult = new FightResult(0,0,st,Player1Result);
        float playerResultRatio = playerResult.getHeight()/playerResult.getWidth();
        playerResult.setSize(0.16f*WIDTH, 0.16f*WIDTH*playerResultRatio);
        playerResult.setPosition(0.24f*WIDTH,0.332f*HEIGHT);

        enemyResult = new FightResult(0,0,st,Player2Result);
        float enemyResultRatio = enemyResult.getHeight()/enemyResult.getWidth();
        enemyResult.setSize(0.16f*WIDTH, 0.16f*WIDTH*enemyResultRatio);
        enemyResult.setPosition(0.6f*WIDTH,0.332f*HEIGHT);

        restartButton = new Control(0,0, st,"Fight/StartBtn.png",5);
        float ratio = restartButton.getHeight()/restartButton.getWidth() ;
        restartButton.setSize(0.15f*WIDTH, 0.15f*WIDTH*ratio);
        restartButton.setPosition(0.425f*WIDTH,0.2f*HEIGHT);

    }
}

package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.MyGame;
import com.mygdx.game.actors.dialog.Background;
import com.mygdx.game.actors.dialog.DialogBackground;
import com.mygdx.game.actors.dialog.Enemy;
import com.mygdx.game.actors.dialog.Player;
import com.mygdx.game.actors.selection.Plane;
import com.mygdx.game.actors.selection.SelectedPlayer;
import com.mygdx.game.base.ScreenBeta;


public final class DialogScene extends ScreenBeta {
    Background screenBackground;
    Music dialogMusic;
    DialogBackground dialogBackground;
    Player player;
    Enemy enemy;
    int indexPlayer = 0;
    int indexEnemy = 0;
    Label name;
    Label text;
    Skin mySkin;
    String playerName;
    String enemyName;
    Boolean activeScene;

    String[] ryuTalk= new String[]{
        "You are in my path to defeat shadowloo",
        "I'll use all my might to overcome my opponents",
        "Prepare to fight"
    };
    String[] chunLiTalk= new String[]{
        "I have to avenge my family",
        "I'll destroy Shadowloo from the inside",
        "Let's make this quick"
    };
    String[] blankaTalk= new String[]{
        "AAAAHHDDGHD Fight Fight AHHGGGHHAAA AAAAAAA",
        "GO AHHHHHHH AWAY AARRRGGGHHH HAAAAAAA",
        "PREPARE GAGHAAAAA TO DIE!!!!!"
    };

    String[] playerTalk;
    String[] enemyTalk;


    public DialogScene(){
        super();

    }

    public  void initialize()
    {
        mySkin = new Skin(Gdx.files.internal("Skin/arcade/skin/arcade-ui.json"));

    }

    @Override
    public void show(){
        super.show();
        indexPlayer = 0;
        indexEnemy = 0;

        activeScene = true;
        Preferences prefs = Gdx.app.getPreferences("FightGamePreferences");
        enemyName = prefs.getString("Enemy");
        playerName = prefs.getString("Player");

        if(playerName.contentEquals("Ryu")){
            Gdx.app.log("Selected","Getting Ryutalk");
            playerTalk = ryuTalk;
        } else if (playerName.contentEquals("ChunLi")){
            Gdx.app.log("Selected","Getting ChunTalk");
            playerTalk = chunLiTalk;
        } else if (playerName.contentEquals("Blanka")){
            Gdx.app.log("Selected","Getting BlankaTalk");
            playerTalk = blankaTalk;
        }

        if(enemyName.contentEquals("Ryu")){
            Gdx.app.log("Selected","Getting Enemy Ryutalk");
            enemyTalk = ryuTalk;
        } else if (enemyName.contentEquals( "ChunLi")){
            Gdx.app.log("Selected","Getting Enemy ChunTalk");
            enemyTalk = chunLiTalk;
        } else if (enemyName.contentEquals("Blanka")){
            Gdx.app.log("Selected","Getting Enemy BlankaTalk");
            enemyTalk = blankaTalk;
        }

        screenBackground = new Background(0,0,st,"Dialog/" + playerName + "Stage.png");
        screenBackground.setScale(2,2);

        dialogMusic = Gdx.audio.newMusic(Gdx.files.internal("Dialog/" + playerName  + "Music.mp3"));
        dialogMusic.setVolume(0.3f);
        dialogMusic.setLooping(true);
        dialogMusic.play();

        player = new Player(0.1f*WIDTH,0.05f*HEIGHT,st,"Dialog/"+ playerName  +"1P.png");
        float playerRatio = player.getWidth()/player.getHeight();
        player.setSize(0.8f*HEIGHT*playerRatio,0.8f*HEIGHT);

        enemy = new Enemy(0.6f*WIDTH,0.05f*HEIGHT,st,"Dialog/"+ enemyName +"2P.png");
        float enemyRatio = enemy.getWidth()/enemy.getHeight();
        enemy.setSize(0.8f*HEIGHT*enemyRatio,0.8f*HEIGHT);

        dialogBackground = new DialogBackground(0,0,st);
        dialogBackground.setSize(WIDTH+10,HEIGHT/3);

        name = new Label(playerName, mySkin);
        name.setFontScale(3.0f);
        name.setPosition(0.1f*WIDTH, 0.31f*HEIGHT);

        text = new Label(playerTalk[indexPlayer++], mySkin);
        text.setFontScale(1.5f);
        text.setPosition(0.05f*WIDTH,0.2f*HEIGHT);

        st.addActor(name);
        st.addActor(text);

    }

    @Override
    public void hide(){
        screenBackground.remove();
        player.remove();
        enemy.remove();
        dialogBackground.remove();
        name.remove();
        text.remove();

        screenBackground = null;
        player = null;
        enemy = null;
        dialogBackground = null;
        name = null;
        text = null;

        activeScene = false;
        dialogMusic.stop();
    }


    public void update(float dt)
    {

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(!activeScene)
            return false;

        Gdx.app.log("Selected","indexPlayer: " + indexPlayer + " indexEnemy: " + indexEnemy);

        if(indexPlayer == indexEnemy){
            if(indexPlayer == 3){
                transitionTo = "Fight";
                return true;
            }
            name.setText(playerName);
            text.setText(playerTalk[indexPlayer++]);
            player.addAction(Actions.sequence(
                    Actions.scaleTo(1.1f,1.1f,0.3f),
                    Actions.scaleTo(1,1,0.3f)));
            return true;
        }
        if(indexPlayer != indexEnemy){
            name.setText(enemyName);
            text.setText(enemyTalk[indexEnemy++]);
            enemy.addAction(Actions.sequence(
                    Actions.scaleTo(1.1f,1.1f,0.3f),
                    Actions.scaleTo(1,1,0.3f)));
            return true;
        }
        // if anything else depends on this...
        return true;
    }

}

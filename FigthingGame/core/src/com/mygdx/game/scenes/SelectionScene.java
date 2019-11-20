package com.mygdx.game.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.game.MyGame;
import com.mygdx.game.actors.selection.Background;
import com.mygdx.game.actors.selection.Plane;
import com.mygdx.game.actors.selection.SelectedPlayer;
import com.mygdx.game.actors.selection.StartButton;
import com.mygdx.game.base.ScreenBeta;

public final class SelectionScene extends ScreenBeta {

    Background selectionBackground;
    StartButton startButton;
    SelectedPlayer player1;
    SelectedPlayer player2;
    Music selectionScreenMusic;
    Plane plane;
    Vector2 finalCoordnates;
    Boolean activeScreen = true;

    public SelectionScene()
    {
        super();
    }

    public  void initialize(){
        selectionBackground = new Background(0, 0, st, "Selection/SelectionScreenFinal.png");
        selectionBackground.setSize(WIDTH, HEIGHT+10);

        startButton = new StartButton(0.75f*WIDTH,0.13f*HEIGHT,st);
        float ratio = startButton.getHeight()/startButton.getWidth() ;
        startButton.setSize(0.2f*WIDTH, 0.2f*WIDTH*ratio);
        startButton.setVisible(false);// Only when both players are selected

        selectionScreenMusic = Gdx.audio.newMusic(Gdx.files.internal("Selection/PlayerSelectTheme.mp3"));
        selectionScreenMusic.setVolume(0.3f);
        selectionScreenMusic.setLooping(true);
    }

    @Override
    public void show(){
        super.show();
        activeScreen = true;
        selectionScreenMusic.play();
    }

    @Override
    public void hide(){
        player1.remove();
        player2.remove();
        player1 = null;
        player2 = null;
        startButton.setVisible(false);
        activeScreen = false;
        selectionScreenMusic.stop();
    }


    public void update(float dt){
    }

    Action switchScreenAction = new Action(){
        @Override
        public boolean act(float delta){
            transitionTo = "Dialog";
            return true;
        }
    };

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if(!activeScreen){
            return false;
        }
        float realY = (HEIGHT - screenY)/HEIGHT; // This has been inverted for pete's sake
        float realX = screenX/WIDTH;

        Gdx.app.log("Selection Position","x: " + screenX + "y:" + (HEIGHT-screenY));
        //Gdx.app.log("Measurement","WIDTH: " + WIDTH + "HEIGHT:" + HEIGHT);

        boolean selectRyu      = realX >= 572f/1794f  && realX <= 731f/1794f  && realY >= 238f/1080f && realY <= 388f/1080f;
        boolean selectChunLi   = realX >= 732f/1794f  && realX <= 898f/1794f  && realY >= 84f/1080f  && realY <= 237f/1080f;
        boolean selectBlanka   = realX >= 899f/1794f  && realX <= 1065f/1794f && realY >= 238f/1080f && realY <= 388f/1080f;
        boolean stButtonPress  = realX >= 1351f/1794f && realX <= 1705f/1794f && realY >= 146f/1080f && realY <= 293f/1080f;

        //Gdx.app.log("Selected",selectRyu?"Ryu":selectBlanka?"Blanka":selectChunLi?"ChunLi":"");
        if((selectRyu||selectBlanka||selectChunLi))
        {
            String playerName = selectRyu?"Ryu":selectBlanka?"Blanka":selectChunLi?"ChunLi":"";
            if(player1 == null && (selectRyu||selectBlanka||selectChunLi)) {
                player1 = new SelectedPlayer(0.08f*WIDTH, 0.5f*HEIGHT, st,playerName,1);
                float player1Ratio = player1.getHeight()/player1.getWidth();
                player1.setSize(0.13f*WIDTH, 0.13f*WIDTH*player1Ratio);
                Gdx.app.log("Selected","Setting Player 1: " + playerName);
                Preferences prefs = Gdx.app.getPreferences("FightGamePreferences");
                prefs.putString("Player", playerName);
                prefs.flush();
                finalCoordnates = new Vector2();
                if(playerName=="Ryu"){
                    finalCoordnates.x = 1246f/1794f*WIDTH;
                    finalCoordnates.y = 720f/1080f*HEIGHT;
                }
                else if(playerName=="Blanka"){
                    finalCoordnates.x = 1506f/1794f*WIDTH;
                    finalCoordnates.y = 520f/1080f*HEIGHT;
                }
                else if(playerName=="ChunLi"){
                    finalCoordnates.x = 1042f/1794f*WIDTH;
                    finalCoordnates.y = 770f/1080f*HEIGHT;
                }
            }
            else if(player2 == null && (selectRyu||selectBlanka||selectChunLi)){
                player2 = new SelectedPlayer(0.08f*WIDTH, 0.1f*HEIGHT, st,playerName,2);
                float player2Ratio = player2.getHeight()/player2.getWidth();
                player2.setSize(0.13f*WIDTH, 0.13f*WIDTH*player2Ratio);
                Gdx.app.log("Selected","Setting Player 2: " + playerName);
                Preferences prefs = Gdx.app.getPreferences("FightGamePreferences");
                prefs.putString("Enemy", playerName);
                prefs.flush();
            }
            if (player1 != null && player2 != null && !startButton.isVisible())
            {
                startButton.setVisible(true);
                startButton.addAction(
                        Actions.forever(Actions.sequence(Actions.scaleTo(1.1f, 1.1f, 0.5f), Actions.scaleTo(1.0f, 1.0f, 0.5f))));
            }
            return true;
        }

        if(stButtonPress && startButton.isVisible())
        {
            plane = new Plane(0.14f*WIDTH, 0.65f*HEIGHT, st);
            plane.addAction(Actions.sequence(
                    Actions.scaleTo(0,0,0),
                    Actions.scaleTo(1,1,0.5f),
                    Actions.moveTo(finalCoordnates.x,finalCoordnates.y,2),
                    Actions.scaleTo(0,0,0.5f),
                    switchScreenAction
            ));
            //
        }
        // if anything else depends on this...
        return true;
    }

}

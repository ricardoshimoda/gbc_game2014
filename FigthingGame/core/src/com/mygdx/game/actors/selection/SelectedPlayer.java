package com.mygdx.game.actors.selection;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.base.ActorBeta;

public final class SelectedPlayer extends ActorBeta {
    String playerName;
    int playerSlot;
    public String getPlayerName(){return playerName;}
    public SelectedPlayer (float x, float y, Stage s, String selectedPlayerName, int slot) {
        // Setup basic stuff
        super(x, y, s);
        loadTexture("Selection/" + selectedPlayerName + slot+"P.png");
        playerName = selectedPlayerName;
        playerSlot = slot;
    }
}

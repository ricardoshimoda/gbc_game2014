package com.mygdx.game;

public class MyGame extends GameBeta {

    //Screens
    RegisterScreen registerScreen;

    @Override
    public void create() {
        super.create();

        registerScreen = new RegisterScreen();

        setActiveScreen(registerScreen);
    }
}

package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class RegisterScreen extends ScreenBeta {

    //Labels

    Label titleLbl;

    Label usernameLbl;
    Label passwordLbl;
    Label emailLbl;

    //Text fields
    TextField usernameField;
    TextField passwordField;
    TextField emailField;

    //Buttons
    TextButton registerBtn;
    TextButton loadBtn;

    //Skin
    Skin formSkin;

    //Table
    Table formTable;

    //Preferences;
    Preferences pref;

    //Sound
    Sound btnClick;
    Music bgMusic;

    CheckBox signUp;
    Slider progress;
    Touchpad touch;

    @Override
    public void initialize() {
        //skin
        formSkin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        //labels
        titleLbl = new Label("Register", formSkin);
        titleLbl.setFontScale(4.0f);
        titleLbl.setPosition(Gdx.graphics.getWidth() / 2 - (titleLbl.getWidth()), Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 15));

        usernameLbl = new Label("Username", formSkin);
        usernameLbl.setFontScale(2.0f);

        passwordLbl = new Label("Password", formSkin);
        passwordLbl.setFontScale(2.0f);

        //text fields
        usernameField = new TextField("", formSkin);
        passwordField = new TextField("", formSkin);
        passwordField.setPasswordCharacter('*');
        passwordField.setPasswordMode(true);

        signUp = new CheckBox("", formSkin);
        progress = new Slider(0,100,1,false,formSkin);
        touch = new Touchpad(10.0f, formSkin);

        //button
        registerBtn = new TextButton("Register", formSkin);
        loadBtn = new TextButton("Load", formSkin);

        //tables
        formTable = new Table();
        formTable.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - (Gdx.graphics.getHeight() / 3));
        formTable.add(usernameLbl).pad(50);
        formTable.add(usernameField).width(500);

        formTable.row();

        formTable.add(passwordLbl).pad(50);
        formTable.add(passwordField).width(500);

        formTable.row();
        formTable.add(signUp).pad(50);
        formTable.add(progress).pad(50);

        formTable.row();
        formTable.add(touch).pad(50);


        formTable.row();
        formTable.add(registerBtn);
        formTable.add(loadBtn);

      //  registerBtn.addListener(new ClickListener() {

     //   });

        //preferences
        pref = Gdx.app.getPreferences("My Preferences");

        //music
        bgMusic = Gdx.audio.newMusic((Gdx.files.internal("bensound-clearday.mp3")));
        bgMusic.play();

        btnClick = Gdx.audio.newSound(Gdx.files.internal("button-4.mp3"));

        //stage
        mainStage.addActor(formTable);
        mainStage.addActor(titleLbl);

        formTable.setDebug(false);
    }

    @Override
    public void update(float dt) {

        if(registerBtn.isPressed()) {
            pref.putString("Username", usernameField.getText());
            pref.flush();

            pref.putString("Password", passwordField.getText());
            pref.flush();

            btnClick.play();
        }

        if(loadBtn.isPressed()) {
            usernameField.setText(pref.getString("Username"));
            passwordField.setText(pref.getString("Password"));

            btnClick.play();
        }

    }
}

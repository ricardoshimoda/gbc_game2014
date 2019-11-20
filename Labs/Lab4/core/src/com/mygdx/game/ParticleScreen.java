package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.base.ParticleActor;
import com.mygdx.game.base.ScreenBeta;

public class ParticleScreen extends ScreenBeta {

    ParticleActor particleActor;
    @Override
    public void initialize(){
        particleActor = new ParticleActor("BlueConfusion.pfx","");
        st.addActor(particleActor);
        particleActor.setScale(4);
        particleActor.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        particleActor.start();
    }

    @Override
    public void update(float dt){



    }

}

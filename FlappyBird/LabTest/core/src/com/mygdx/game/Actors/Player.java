package com.mygdx.game.Actors;

import com.mygdx.game.base.ActorBeta;

public final class Player extends ActorBeta {
    private boolean moving = false;
    private boolean alive = true;

    private float groundY;
    public Player(float minY)
    {
        groundY = minY;
        String[] birdAnim = {"Bird1.png", "Bird2.png", "Bird3.png"};
        loadAnimationFromFiles(birdAnim, 0.1f, true);
        this.setBoundaryRectangle();
        setMaxSpeed(800);
    }

    public void Move(){moving = true;}

    public void Death(){
        alive = false;
        setRotation(180);
        setAnimationPaused(true);
    }

    public boolean IsAlive(){return alive;}

    @Override
    public void act(float dt) {
        super.act(dt);
        if(moving){
            setAcceleration(800);
            accelerateAtAngle(270);
            applyPhysics(dt);
            boundToWorld();
        }
        if(getY() < groundY - getHeight()){
            setY(groundY-getHeight());
        }
    }

    public void boost() {
        if(moving)
        {
            setSpeed(300);
            setMotionAngle(90);
        }
    }
}

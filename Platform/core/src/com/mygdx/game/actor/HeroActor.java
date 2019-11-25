package com.mygdx.game.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.base.ActorBeta;

import java.util.ArrayList;


public final class HeroActor extends ActorBeta {

    /* Hero Animation */
    private Animation<TextureRegion> idleR;
    private Animation<TextureRegion> walkR;
    private Animation<TextureRegion> jumpR;
    private Animation<TextureRegion> fallR;
    private Animation<TextureRegion> deadR;

    private Animation<TextureRegion> idleL;
    private Animation<TextureRegion> walkL;
    private Animation<TextureRegion> jumpL;
    private Animation<TextureRegion> fallL;
    private Animation<TextureRegion> deadL;

    /* Constant values for animation load */
    private static final int IDLE_FRAMES = 10;
    private static final int WALK_FRAMES = 10;
    private static final int JUMP_FRAMES = 8;
    private static final int FALL_FRAMES = 8;
    private static final int DEAD_FRAMES = 10;

    /* Constant values for hero movement */
    private static final float SCREEN_HEIGHT_RATIO = 0.25f;
    private static final float UPWARD_TIME = 1.0f;
    private static final float HURT_TIME = 2.5f;
    private static final float SPEED = 200.0f;
    private static final float VERTICAL_SPEED = 200.0f;
    private static final int MAX_HEALTH = 10;

    /* Hero movement */
    private float verticalTimer = 0.0f;
    private ActorMovement currentMovement = ActorMovement.IDLE_RIGHT;
    private boolean grounded = false;
    public boolean goingUp = false;
    private boolean jumping = false;
    private float widthChange;
    private ArrayList<ActorBeta> moveAlong;
    private ArrayList<ZombieActor> moveZombiesAlong;

    /* Hero life */
    public int currentHealth = MAX_HEALTH;
    public float hurtTimer = HURT_TIME;
    public boolean win = false;

    public HeroActor(float screenHeight, float screenWidth)
    {
        moveAlong = new ArrayList<ActorBeta>();
        moveZombiesAlong = new ArrayList<ZombieActor>();
        widthChange = 0.5f * screenWidth;
        idleR = LoadAnimation(IDLE_FRAMES, "MovingRight", "Idle");
        idleL = LoadAnimation(IDLE_FRAMES, "MovingLeft", "Idle" );

        walkR = LoadAnimation(WALK_FRAMES, "MovingRight", "Walk");
        walkL = LoadAnimation(WALK_FRAMES, "MovingLeft", "Walk");

        /* NEVER IN LOOP */
        jumpR = LoadAnimation(JUMP_FRAMES, "MovingRight", "Jump");
        jumpL = LoadAnimation(JUMP_FRAMES, "MovingLeft", "Jump");

        fallR = LoadAnimation(FALL_FRAMES, "MovingRight", "Fall");
        fallL = LoadAnimation(FALL_FRAMES, "MovingLeft", "Fall");

        deadR = LoadAnimation(DEAD_FRAMES, "MovingRight", "Dead");
        deadL = LoadAnimation(DEAD_FRAMES, "MovingLeft", "Dead");

        factor = SCREEN_HEIGHT_RATIO * screenHeight/getHeight();
        // Sets First Animation;
        setAnimation(idleR, true);
    }

    public void setMoveAlong(ArrayList<ActorBeta> otherObjects)
    {
        moveAlong = otherObjects;
    }

    public void setZombieMoveAlong(ArrayList<ZombieActor> myZombies ){
        moveZombiesAlong = myZombies;
    }

    @Override
    public void act(float dt)
    {
        super.act(dt);
        if(currentHealth > 0 && ! win)
        {
            Move(dt);
            Jump(dt);
            Gravity(dt);
            HurtTimer(dt);
        }
    }

    private void Move(float dt)
    {
        float pX = getX();
        if(pX <= widthChange)
        {
            if(currentMovement == ActorMovement.RIGHT){
                setX(pX+ SPEED *dt);
            }
            else if(currentMovement == ActorMovement.LEFT){
                if(pX >= SPEED*dt){
                    setX(pX - SPEED *dt);
                }
            }
        }else{
            for(int i = 0; i < moveAlong.size(); i++){
                ActorBeta movingObj = moveAlong.get(i);
                if(currentMovement == ActorMovement.RIGHT){
                    movingObj.setX(movingObj.getX() - SPEED * dt);
                }
                else if(currentMovement == ActorMovement.LEFT){
                    if(pX >= SPEED*dt){
                        movingObj.setX(movingObj.getX() + SPEED * dt);
                    }
                }
            }
            for(int z = 0; z < moveZombiesAlong.size(); z++){
                ZombieActor movingZombie = moveZombiesAlong.get(z);
                if(currentMovement == ActorMovement.RIGHT){
                    movingZombie.setX(movingZombie.getX() - SPEED * dt);
                    movingZombie.startX -= SPEED*dt;
                }
                else if(currentMovement == ActorMovement.LEFT){
                    if(pX >= SPEED*dt){
                        movingZombie.setX(movingZombie.getX() + SPEED * dt);
                        movingZombie.startX += SPEED*dt;
                    }
                }
            }
        }
    }

    private void Jump(float dt){
        if(jumping)
        {
            verticalTimer += dt;
            if(verticalTimer <= UPWARD_TIME){
                setY(getY() + VERTICAL_SPEED * dt);
            } else {
                if(goingUp){
                    /* starts falling*/
                    goingUp = false;
                    if(GoingRight()) {
                        setAnimation(fallR, false);
                    }
                    else {
                        setAnimation(fallL, false);
                    }
                }
            }
        }
    }

    private void Gravity(float dt){
        if(!goingUp && !grounded){
            setY(getY() - VERTICAL_SPEED * dt);
            if(getY()< -getHeight()){
                currentHealth = 0; // DEAD
            }
        }
    }

    private void HurtTimer(float dt){
        if(hurtTimer < HURT_TIME) {
            hurtTimer+=dt;
        }
    }

    public void Hurt()
    {
        if(hurtTimer >= HURT_TIME){
            // Oh no, not again!
            hurtTimer = 0.0f;
            currentHealth--;
            if(currentHealth == 0) {
                if(GoingRight()){
                    currentMovement = ActorMovement.IDLE_RIGHT; // Stops Moving
                    setAnimation(deadR,false);
                } else {
                    currentMovement = ActorMovement.IDLE_LEFT; // Stops Moving
                    setAnimation(deadL,false);
                }
            }
        }
    }

    public void Death()
    {
        currentHealth = 0;
        if(GoingRight()){
            currentMovement = ActorMovement.IDLE_RIGHT; // Stops Moving
            setAnimation(deadR,false);
        } else {
            currentMovement = ActorMovement.IDLE_LEFT; // Stops Moving
            setAnimation(deadL,false);
        }

    }

    public void Flying()
    {
        grounded = false;
        if(!jumping){
            if(GoingRight()){
                setAnimation(fallR,false);
            }
            else{
                setAnimation(fallL,false);
            }
        }
    }

    public void Land()
    {
        if(!grounded && !goingUp)
        {
            grounded = true;
            jumping=false;
            if(GoingRight()){
                setAnimation(idleR,true);
                currentMovement = ActorMovement.IDLE_RIGHT;
            }
            else{
                setAnimation(idleL,true);
                currentMovement = ActorMovement.IDLE_LEFT;
            }
        }
    }

    public void StartMoving(ActorMovement movement)
    {
        if(movement == ActorMovement.RIGHT) {
            setAnimation(walkR, true);
        } else if (movement== ActorMovement.LEFT){
            setAnimation(walkL, true);
        }
        currentMovement = movement;
    }

    public void StopMoving()
    {
        if(GoingRight()){
            setAnimation(idleR,true);
            currentMovement = ActorMovement.IDLE_RIGHT;
        } else {
            setAnimation(idleL, true);
            currentMovement = ActorMovement.IDLE_LEFT;
        }
    }

    public void StartJump()
    {
        if(grounded){
            grounded = false;
            jumping = true;
            goingUp = true;
            verticalTimer = 0.0f;
            if(GoingRight()){
                setAnimation(jumpR, false);
            } else {
                setAnimation(jumpL, false);
            }
        }
    }

    private Animation<TextureRegion> LoadAnimation(int frameQtd, String orientation, String situation)
    {
        String[] animationFramePathList = new String[frameQtd];
        for (int i = 1; i <= frameQtd; i++){
            animationFramePathList[i-1] = "Hero/"+ orientation + "/" + situation + " ("+i+").png";
        }
        return loadAnimationFromFiles(animationFramePathList, 0.07f, true);
    }

    private boolean GoingRight()
    {
        return currentMovement== ActorMovement.RIGHT || currentMovement== ActorMovement.IDLE_RIGHT;
    }
}

package com.flyw1ng.ultragame.anim;

import com.badlogic.gdx.Gdx;

public class AnimationPlayer {

    public enum Direction{
        FORWARD,
        BACKWARD
    }
    public enum ANIMSTATUS{
        NONE,
        ANIMATING,
        DELAY
    }
    private Animation animation;

    private float timer = 0f;
    private int currentFrame = 0;
    private boolean finished = false;

    private ANIMSTATUS status = ANIMSTATUS.NONE;
    private Direction direction = Direction.FORWARD;

    public  AnimationPlayer(Animation animation){
        this.animation = animation;
    }

    public void start(){

        timer = 0f;
        finished = false;
        status = ANIMSTATUS.ANIMATING;

        if (direction == Direction.FORWARD){
            currentFrame = 0;
        }
        else currentFrame = animation.getFrameCount() - 1;
    }
    public void stop(){
        timer = 0f;
        finished = false;
        status = ANIMSTATUS.NONE;
    }
    public void update(){
        if (animation == null || finished || status == ANIMSTATUS.NONE) return;

        timer += Gdx.graphics.getDeltaTime();

        while (timer >= animation.getFrameDuration()){
            timer -= animation.getFrameDuration();
            step();
        }
    }
    private void step(){
        if (direction == Direction.FORWARD){
            currentFrame++;

            if (currentFrame >= animation.getFrameCount()){
                if (animation.isLoop())
                    currentFrame = 0;
                else {
                    currentFrame = animation.getFrameCount() - 1;
                    finished = true;
                    status = ANIMSTATUS.NONE;
                }
            }
        } else {
            currentFrame--;

            if (currentFrame < 0){
                if (animation.isLoop())
                    currentFrame = animation.getFrameCount() - 1;
                else {
                    currentFrame = 0;
                    finished = true;
                    status = ANIMSTATUS.NONE;
                }
            }
        }
    }
    public int getFrame(){
        return currentFrame;
    }
    public ANIMSTATUS getStatus(){
        return status;
    }
    public boolean isFinished(){
        if (finished){
            finished = false;
            return true;
        }
        return false;
    }
    public boolean isAnimating() {
        return status.equals(ANIMSTATUS.ANIMATING);
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }
}

package com.flyw1ng.ultragame.anim;

public class Animation {

    private final int frameCount;
    private final float frameDuration;
    private final boolean loop;

    public Animation(int frameCount, float frameDuration, boolean loop){
        this.frameCount = frameCount;
        this.frameDuration = frameDuration;
        this.loop = loop;
    }
    public int getFrameCount(){
        return frameCount;
    }

    public float getFrameDuration() {
        return frameDuration;
    }

    public boolean isLoop() {
        return loop;
    }
}

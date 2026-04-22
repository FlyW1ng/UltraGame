package com.flyw1ng.ultragame.anim;

public interface AnimationProvider {
    void start();
    void update();
    void stop();
    boolean isAnimating();
    boolean isDelay();
    boolean hasJustFinished();
    int getCurrentFrame();

    enum ANIM{
        NONE, ANIMATING, DELAY
    }
}

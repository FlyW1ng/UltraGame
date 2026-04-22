package com.flyw1ng.ultragame.anim;

public class TimerAnimation implements AnimationProvider {

    private int maxAnimationTime;
    private int animationTime;
    private ANIM status;
    private boolean justFinished;
    private boolean delayTimeAfterAnimating;
    private int delayTime;
    private int maxDelayTime;

    public TimerAnimation(int maxAnimationTime, boolean delayTimeAfterAnimating){
        this.maxAnimationTime = maxAnimationTime;
        status = ANIM.NONE;
        this.delayTimeAfterAnimating = delayTimeAfterAnimating;
        maxDelayTime = maxAnimationTime;
    }
    @Override
    public void start(){
        animationTime = 0;
        status = ANIM.ANIMATING;
        justFinished = false;
    }
    @Override
    public boolean isAnimating(){
        return status.equals(ANIM.ANIMATING);
    }
    @Override
    public boolean isDelay(){
        return status.equals(ANIM.DELAY);
    }
    @Override
    public boolean hasJustFinished(){
        if (justFinished) {
            justFinished = false; // Сбрасываем флаг после проверки
            return true;
        }
        return false;
    }

    @Override
    public int getCurrentFrame() {
        return animationTime;
    }
    @Override
    public void update() {
        switch (status) {
            case NONE:
                return;
            case ANIMATING:
                animationTime++;
                if (animationTime >= maxAnimationTime) {
                    if (delayTimeAfterAnimating) {
                        status = ANIM.DELAY;
                        delayTime = 0;           // начинаем отсчёт задержки
                    } else {
                        animationTime = 0;
                        justFinished = true;
                        status = ANIM.NONE;
                    }
                }
                break;
            case DELAY:
                delayTime++;
                if (delayTime >= maxDelayTime) {
                    animationTime = 0;
                    delayTime = 0;
                    justFinished = true;
                    status = ANIM.NONE;
                }
                break;
        }
    }

    @Override
    public void stop() {
        status = ANIM.NONE;
        justFinished = false;
        animationTime = 0;
        delayTime = 0;
    }
    public ANIM getStatus(){
        return status;
    }
    public int getDelayTime(){
        return delayTime;
    }
}

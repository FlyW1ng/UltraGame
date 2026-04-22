package com.flyw1ng.ultragame.anim;

public class FrameAnimation implements AnimationProvider {

    private int totalFrames;
    private int frameDuration;
    private boolean loop;
    private boolean delayAfterAnimating;
    private int maxDelayTime;
    private int maxAnimationTime;
    private ANIM status;
    private boolean justFinished;
    private int repeatCount;
    private int currentRepeat;
    private int elapsedTicks;
    private int currentFrame;
    private int delayTime;
    private boolean reverse; // новое поле: направление анимации

    public FrameAnimation(int totalFrames, int frameDuration, boolean loop, boolean delayAfterAnimating) {
        this.totalFrames = totalFrames;
        this.frameDuration = frameDuration;
        this.loop = loop;
        this.delayAfterAnimating = delayAfterAnimating;
        this.maxDelayTime = frameDuration; // для задержки используем длительность кадра
        this.status = ANIM.NONE;
        this.justFinished = false;
        this.maxAnimationTime = totalFrames * frameDuration;
        this.reverse = false; // по умолчанию прямое направление
    }

    @Override
    public void start() {
        elapsedTicks = 0;
        currentFrame = reverse ? totalFrames - 1 : 0; // первый кадр зависит от направления
        currentRepeat = 0;
        delayTime = 0;
        status = ANIM.ANIMATING;
        justFinished = false;
    }

    @Override
    public void stop() {
        status = ANIM.NONE;
        justFinished = false;
        elapsedTicks = 0;
        currentFrame = reverse ? totalFrames - 1 : 0; // сброс к начальному кадру
        delayTime = 0;
    }

    @Override
    public void update() {
        if (status.equals(ANIM.NONE)) {
            justFinished = false;
            return;
        }
        if (status.equals(ANIM.ANIMATING)) {
            elapsedTicks++;
            int forwardFrame = elapsedTicks / frameDuration; // прогресс в прямом направлении

            if (forwardFrame >= totalFrames) {
                // достигнут конец последовательности
                if (loop) {
                    // зацикливание
                    elapsedTicks = 0;
                    currentRepeat++;
                    if (repeatCount > 0 && currentRepeat >= repeatCount) {
                        status = ANIM.NONE;
                        justFinished = true;
                        return;
                    }
                    if (delayAfterAnimating) {
                        status = ANIM.DELAY;
                        delayTime = 0;
                    }
                    // после сброса первый кадр зависит от направления
                    currentFrame = reverse ? totalFrames - 1 : 0;
                } else {
                    // без зацикливания
                    if (delayAfterAnimating) {
                        status = ANIM.DELAY;
                        delayTime = 0;
                    } else {
                        status = ANIM.NONE;
                        justFinished = true;
                    }
                    // оставляем последний кадр с учётом направления
                    if (reverse) {
                        currentFrame = 0; // последний кадр при обратной анимации — это 0
                    } else {
                        currentFrame = totalFrames - 1;
                    }
                }
            } else {
                // вычисляем текущий кадр в зависимости от направления
                if (reverse) {
                    currentFrame = totalFrames - 1 - forwardFrame;
                } else {
                    currentFrame = forwardFrame;
                }
            }
        } else if (status.equals(ANIM.DELAY)) {
            delayTime++;
            if (delayTime >= maxDelayTime) {
                delayTime = 0;
                status = ANIM.NONE;
                justFinished = true;
            }
        }
    }

    @Override
    public boolean isAnimating() {
        return status.equals(ANIM.ANIMATING);
    }

    @Override
    public boolean isDelay() {
        return false;
    }

    @Override
    public boolean hasJustFinished() {
        if (justFinished) {
            justFinished = false;
            return true;
        }
        return false;
    }

    @Override
    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    // Новый метод: установка направления (true — обратное, false — прямое)
    public void setReverse(boolean reverse) {
        this.reverse = reverse;
        if (status == ANIM.NONE) {
            currentFrame = reverse ? totalFrames - 1 : 0;
        }
        // если анимация активна, currentFrame остаётся прежним,
        // хотя направление изменилось — это ошибка.
    }
}

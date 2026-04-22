package com.flyw1ng.ultragame.sound;

public enum SoundState {
    ON,
    MIDDLE,
    OFF;

    public static SoundState get(int currentFrame) {
        switch (currentFrame){
            case 0: return ON;
            case 1: return MIDDLE;
            case 2: return OFF;
        }
        return ON;
    }

}


package com.flyw1ng.ultragame.gui.lamp;

public enum LampState {

    LAMP_FRAME_0,
    LAMP_FRAME_1,
    LAMP_FRAME_2,
    LAMP_FRAME_3,
    LAMP_FRAME_4,
    LAMP_FRAME_5;

    public static LampState get(int currentFrame) {
        switch (currentFrame){
            case 1: return LAMP_FRAME_0;
            case 2: return LAMP_FRAME_1;
            case 3: return LAMP_FRAME_2;
            case 4: return LAMP_FRAME_3;
            case 5: return LAMP_FRAME_4;
            case 6: return LAMP_FRAME_5;
        }
        return LAMP_FRAME_0;
    }
}

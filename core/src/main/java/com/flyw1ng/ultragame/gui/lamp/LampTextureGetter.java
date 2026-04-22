package com.flyw1ng.ultragame.gui.lamp;

import com.badlogic.gdx.graphics.Texture;

public interface LampTextureGetter {
    Texture get(LampType lampType, LampState lampState);
    void dispose();
}

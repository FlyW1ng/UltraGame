package com.flyw1ng.ultragame.sound;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

public interface SoundTextureGetter {

    Texture get(SoundType soundType, ThemeType themeType, SoundState soundState);

    void dispose();
}

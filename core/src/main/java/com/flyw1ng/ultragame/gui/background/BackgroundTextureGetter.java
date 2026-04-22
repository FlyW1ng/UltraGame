package com.flyw1ng.ultragame.gui.background;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

public interface BackgroundTextureGetter {
    Texture get(BackgroundType type, ThemeType theme);
    Texture get(BackgroundType type);
    void dispose();
}

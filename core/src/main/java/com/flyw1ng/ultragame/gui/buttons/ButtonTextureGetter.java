package com.flyw1ng.ultragame.gui.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

public interface ButtonTextureGetter {
    Texture get(ButtonType type, ButtonVisualState state, ThemeType themeType);
    void dispose();
}

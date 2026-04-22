package com.flyw1ng.ultragame.gui.panels;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

public interface PanelTextureGetter {
    Texture get(PanelType type, ThemeType themeType);
    void dispose();
}

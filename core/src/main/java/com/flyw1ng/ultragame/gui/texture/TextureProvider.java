package com.flyw1ng.ultragame.gui.texture;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.buttons.ButtonType;
import com.flyw1ng.ultragame.gui.buttons.ButtonVisualState;
import com.flyw1ng.ultragame.gui.background.BackgroundType;
import com.flyw1ng.ultragame.gui.lamp.LampState;
import com.flyw1ng.ultragame.gui.lamp.LampType;
import com.flyw1ng.ultragame.gui.panels.PanelType;
import com.flyw1ng.ultragame.sound.SoundState;
import com.flyw1ng.ultragame.sound.SoundType;

import java.util.Map;

public interface TextureProvider {
    Map<BackgroundType, Map<ThemeType, Texture>> loadBackgroundTextures();
    Map<ButtonType, Map<ButtonVisualState, Map<ThemeType, Texture>>> loadButtonTextures();
    Map<LampType, Map<LampState, Texture>> loadLampTextures();
    Map<PanelType, Map<ThemeType, Texture>> loadPanelTextures();
    Map<SoundType, Map<ThemeType, Map<SoundState, Texture>>>loadSoundTextures();
}

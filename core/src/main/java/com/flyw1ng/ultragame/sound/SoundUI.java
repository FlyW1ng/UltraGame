package com.flyw1ng.ultragame.sound;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.buttons.ButtonVisualState;
import com.flyw1ng.ultragame.gui.texture.TextureLoader;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

import java.util.Map;

public class SoundUI implements SoundTextureGetter{
    Map<SoundType, Map<ThemeType, Map<SoundState, Texture>>> textures;

    public SoundUI(TextureLoader textureLoader){
        textures = textureLoader.loadSoundTextures();
    }

    @Override
    public Texture get(SoundType soundType,ThemeType themeType, SoundState soundState) {
        return textures.get(soundType).get(themeType).get(soundState);
    }

    @Override
    public void dispose() {
        for (Map<ThemeType, Map<SoundState, Texture>> themeMap : textures.values()){
            for (Map<SoundState, Texture> stateMap : themeMap.values()){
                for(Texture texture : stateMap.values()){
                    texture.dispose();
                }
            }
        }
    }
}

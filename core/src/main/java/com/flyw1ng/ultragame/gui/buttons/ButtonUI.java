package com.flyw1ng.ultragame.gui.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.texture.ThemeType;
import com.flyw1ng.ultragame.gui.texture.TextureLoader;
import java.util.Map;

public class ButtonUI implements ButtonTextureGetter {
    private final Map<ButtonType, Map<ButtonVisualState, Map<ThemeType, Texture>>> textures;

    public ButtonUI(TextureLoader textureLoader){
        textures = textureLoader.loadButtonTextures();
    }

    @Override
    public Texture get(ButtonType type, ButtonVisualState state, ThemeType themeType) {
        return textures.get(type).get(state).get(themeType);
    }

    public void dispose(){
        for (Map<ButtonVisualState, Map<ThemeType, Texture>> themeMap : textures.values()){
            for (Map<ThemeType, Texture> stateMap : themeMap.values()){
                for(Texture texture : stateMap.values()){
                    texture.dispose();
                }
            }
        }
    }
}

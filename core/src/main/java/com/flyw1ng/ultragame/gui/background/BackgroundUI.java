package com.flyw1ng.ultragame.gui.background;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.texture.ThemeType;
import com.flyw1ng.ultragame.gui.texture.TextureLoader;

import java.util.Map;

public class BackgroundUI implements BackgroundTextureGetter {

    private Map<BackgroundType, Map<ThemeType, Texture>> textures;

    public BackgroundUI(TextureLoader textureLoader){
        textures = textureLoader.loadBackgroundTextures();
    }

    @Override
    public Texture get(BackgroundType type, ThemeType theme) {
        return textures.get(type).get(theme);
    }
    @Override
    public Texture get(BackgroundType type){
        return textures.get(type).get(ThemeType.LIGHT);
    }
    public void dispose(){
        for (Map<ThemeType, Texture> stateMap : textures.values()){
            for (Texture texture : stateMap.values()){
                texture.dispose();
            }
        }
    }
}

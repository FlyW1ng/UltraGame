package com.flyw1ng.ultragame.gui.panels;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.texture.TextureLoader;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

import java.util.Map;

public class PanelUI implements PanelTextureGetter {

    Map<PanelType, Map<ThemeType, Texture>> textures;

    public PanelUI(TextureLoader textureLoader){
        textures = textureLoader.loadPanelTextures();
    }

    @Override
    public Texture get(PanelType type, ThemeType themeType) {
        return textures.get(type).get(themeType);
    }

    @Override
    public void dispose() {
        for (Map<ThemeType, Texture> panelTypeMap : textures.values()){
            for (Texture texture : panelTypeMap.values()){
                texture.dispose();
            }
        }
    }
}

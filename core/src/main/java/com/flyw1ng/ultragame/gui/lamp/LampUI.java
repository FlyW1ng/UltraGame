package com.flyw1ng.ultragame.gui.lamp;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.texture.TextureLoader;

import java.util.Map;

public class LampUI implements LampTextureGetter {

    Map<LampType, Map<LampState, Texture>> textures;

    public LampUI(TextureLoader textureLoader){
        textures = textureLoader.loadLampTextures();
    }

    @Override
    public Texture get(LampType lampType, LampState lampState) {
        return textures.get(lampType).get(lampState);
    }

    @Override
    public void dispose(){
        for (Map<LampState, Texture> lampType : textures.values()) {
            for (Texture texture : lampType.values()){
                texture.dispose();
            }
        }
    }
}

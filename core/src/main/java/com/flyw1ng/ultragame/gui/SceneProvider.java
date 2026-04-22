package com.flyw1ng.ultragame.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public interface SceneProvider {

        void update();
        void render();
        void touch(Vector3 touchPos);
        Texture updateStaticTexture();
        Texture getStaticTexture();
        void dispose();
}

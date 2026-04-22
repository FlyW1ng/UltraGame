package com.flyw1ng.ultragame.gui;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public interface UIElement {
    void update();
    void renderPixmap(Pixmap pixmap);
    void render(SpriteBatch spriteBatch);
    default boolean touch(Vector3 touchPos){
        return false;
    };
}

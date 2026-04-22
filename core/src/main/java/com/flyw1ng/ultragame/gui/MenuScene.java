package com.flyw1ng.ultragame.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.flyw1ng.ultragame.gui.background.Background;
import com.flyw1ng.ultragame.gui.texture.StaticTextureMenuBuilder;
import com.flyw1ng.ultragame.gui.texture.ThemeManager;

import java.util.List;

/**
 * Управляет набором UI-элементов: обновление, рендеринг, ввод, освобождение ресурсов.
 */
public class MenuScene implements SceneProvider, ThemeChangeListener {
    private final List<UIElement> uiElements;
    private final SpriteBatch spriteBatch;
    private final StaticTextureMenuBuilder staticTextureMenuBuilder;
    private Texture staticTexture;
    private boolean needRebuild = true;

    public MenuScene(List<UIElement> uiElements, SpriteBatch spriteBatch, ThemeManager themeManager) {
        this.uiElements = uiElements;
        this.spriteBatch = spriteBatch;
        staticTextureMenuBuilder = new StaticTextureMenuBuilder((int)getCoordinatesBackground().x, (int)getCoordinatesBackground().y);
        staticTexture = updateStaticTexture();
        themeManager.addListener(this);
    }
    @Override
    public void touch(Vector3 touchPos) {
        for (UIElement ui : uiElements) {
            ui.touch(touchPos);
        }
    }
    @Override
    public void update() {
        for (UIElement ui : uiElements) {
            ui.update();
        }
    }
    @Override
    public void render() {
        for (UIElement ui : uiElements) {
            ui.render(spriteBatch);
        }
    }
    private Vector2 getCoordinatesBackground(){
        for (UIElement ui : uiElements) {
            if (ui instanceof Background){
                return new Vector2(((Background) ui).getWidth(((Background) ui).getType()),
                    ((Background) ui).getHeight(((Background) ui).getType()));
            }
        }
        return new Vector2(0, 0);
    }
    @Override
    public Texture getStaticTexture(){
        if (needRebuild) {
            staticTexture = updateStaticTexture();
            needRebuild = false;
        }
        return staticTexture;
    }
    @Override
    public Texture updateStaticTexture(){
        return new Texture(staticTextureMenuBuilder.createPixmapMenu(uiElements));
    }

    @Override
    public void dispose() {
        for (UIElement ui : uiElements) {
            if (ui instanceof com.badlogic.gdx.utils.Disposable) {
                ((com.badlogic.gdx.utils.Disposable) ui).dispose();
            }
        }
    }

    @Override
    public void onThemeChanged() {
        needRebuild = true;
    }
    public void setNeedRebuild(boolean needRebuild){
        this.needRebuild = needRebuild;
    }
}

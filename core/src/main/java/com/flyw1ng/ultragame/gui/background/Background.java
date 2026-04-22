package com.flyw1ng.ultragame.gui.background;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.flyw1ng.ultragame.GameManager;
import com.flyw1ng.ultragame.gui.UIElement;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

public class Background implements UIElement, Disposable {
    private BackgroundType backgroundType;
    BackgroundTextureGetter backgroundTextureGetter;
    ThemeType themeType;

    public Background(BackgroundType backgroundType,
                      BackgroundTextureGetter backgroundTextureGetter){

        this.backgroundType = backgroundType;
        this.backgroundTextureGetter = backgroundTextureGetter;
        themeType = ThemeType.LIGHT;
    }


    @Override
    public void renderPixmap(Pixmap pixmap) {

        if (GameManager.ThemeTypeNow.equals(ThemeType.LIGHT)){
            themeType = ThemeType.LIGHT;
        } else themeType = ThemeType.DARK;

        Texture texture = backgroundTextureGetter.get(backgroundType, themeType);

        if (!texture.getTextureData().isPrepared()){
            texture.getTextureData().prepare();
        }

        pixmap.drawPixmap(texture.getTextureData().consumePixmap(), 0, 0);
    }
    @Override
    public void render(SpriteBatch spriteBatch){

        if (GameManager.ThemeTypeNow.equals(ThemeType.LIGHT)){
            themeType = ThemeType.LIGHT;
        } else themeType = ThemeType.DARK;

        Texture texture = backgroundTextureGetter.get(backgroundType, themeType);

        spriteBatch.draw(texture, 0, 0);
    }

    @Override
    public void update() {

    }
    public int getWidth(BackgroundType backgroundType){
        return backgroundTextureGetter.get(backgroundType).getWidth();
    }
    public int getHeight(BackgroundType backgroundType){
        return backgroundTextureGetter.get(backgroundType).getHeight();
    }
    public BackgroundType getType(){
        return backgroundType;
    }
    @Override
    public void dispose(){
        backgroundTextureGetter.dispose();
    }

}

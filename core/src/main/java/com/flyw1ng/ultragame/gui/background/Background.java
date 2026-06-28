package com.flyw1ng.ultragame.gui.background;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.flyw1ng.ultragame.gui.UIElement;
import com.flyw1ng.ultragame.gui.texture.ThemeManager;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

public class Background implements UIElement, Disposable {
    private BackgroundType backgroundType;
    BackgroundTextureGetter backgroundTextureGetter;
    private final ThemeManager themeManager;

    public Background(BackgroundType backgroundType,
                      BackgroundTextureGetter backgroundTextureGetter,
                      ThemeManager themeManager){

        this.backgroundType = backgroundType;
        this.backgroundTextureGetter = backgroundTextureGetter;
        this.themeManager = themeManager;
    }


    @Override
    public void renderPixmap(Pixmap pixmap) {

        Texture texture = backgroundTextureGetter.get(backgroundType, themeManager.getCurrentTheme());

        if (!texture.getTextureData().isPrepared()){
            texture.getTextureData().prepare();
        }

        pixmap.drawPixmap(texture.getTextureData().consumePixmap(), 0, 0);
    }
    @Override
    public void render(SpriteBatch spriteBatch){

        Texture texture = backgroundTextureGetter.get(backgroundType, themeManager.getCurrentTheme());

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

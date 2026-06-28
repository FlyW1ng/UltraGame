package com.flyw1ng.ultragame.gui.panels;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.flyw1ng.ultragame.gui.UIElement;
import com.flyw1ng.ultragame.gui.texture.ThemeManager;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

public class Panel implements UIElement, Disposable {

    private final PanelType panelType;
    PanelTextureGetter panelTextureGetter;
    Vector2 min;
    Vector2 max;
    private final ThemeManager themeManager;

    public Panel(Vector2 minCoordinates,
                 Vector2 maxCoordinates,
                 PanelType panelType,
                 PanelTextureGetter panelTextureGetter,
                 ThemeManager themeManager){
        this.panelType = panelType;
        this.panelTextureGetter = panelTextureGetter;
        min = minCoordinates;
        max = maxCoordinates;
        this.themeManager = themeManager;
    }

    @Override
    public void dispose() {
        panelTextureGetter.dispose();
    }

    @Override
    public void update() {

    }

    @Override
    public void renderPixmap(Pixmap pixmap) {

        Texture texture = panelTextureGetter.get(panelType, themeManager.getCurrentTheme());

        if (!texture.getTextureData().isPrepared()){
            texture.getTextureData().prepare();
        }

        pixmap.drawPixmap(texture.getTextureData().consumePixmap(),
            (int) (min.x),
            (int) (pixmap.getHeight() - max.y));
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        Texture texture = panelTextureGetter.get(panelType, themeManager.getCurrentTheme());

        spriteBatch.draw(texture, (int) min.x, (int) min.y);
    }
}

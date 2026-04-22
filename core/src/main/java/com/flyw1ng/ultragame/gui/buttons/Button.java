package com.flyw1ng.ultragame.gui.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.flyw1ng.ultragame.GameManager;
import com.flyw1ng.ultragame.anim.AnimationPlayer;
import com.flyw1ng.ultragame.anim.TimerAnimation;
import com.flyw1ng.ultragame.gui.Action;
import com.flyw1ng.ultragame.gui.UIElement;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

public class Button implements UIElement, Disposable {
    private Vector3 min, max;
    private ButtonType type;
    private Action action;
    private AnimationPlayer animationPlayer;
    private ButtonTextureGetter buttonTextureGetter;

    private ButtonVisualState state = ButtonVisualState.IDLE;
    private ThemeType themeType;

    public Button(float minX, float minY, float maxX, float maxY,
                  ButtonType type,
                  Action action,
                  AnimationPlayer animationPlayer,
                  ButtonTextureGetter buttonTextureGetter){

        this.min = new Vector3(minX, minY, 0);
        this.max = new Vector3(maxX, maxY, 0);
        this.type = type;
        this.action = action;
        this.animationPlayer = animationPlayer;
        this.buttonTextureGetter = buttonTextureGetter;
        themeType = ThemeType.LIGHT;
    }
    @Override
    public void update(){
        animationPlayer.update();
        if (GameManager.ThemeTypeNow.equals(ThemeType.LIGHT)){
            themeType = ThemeType.LIGHT;
        } else {
            themeType = ThemeType.DARK;
        }

        if (!animationPlayer.isAnimating()){
            state = ButtonVisualState.IDLE;
        }
        if (animationPlayer.isFinished()){
            action.execute();
        }
    }

    @Override
    public void renderPixmap(Pixmap pixmap) {
        Texture texture = buttonTextureGetter.get(type, state, themeType);

        if (!texture.getTextureData().isPrepared()){
            texture.getTextureData().prepare();
        }

        pixmap.drawPixmap(texture.getTextureData().consumePixmap(),
            (int) (min.x),
            (int)(pixmap.getHeight() - max.y));
    }
    @Override
    public void render(SpriteBatch spriteBatch){
        Texture texture = buttonTextureGetter.get(type, state, themeType);
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        spriteBatch.draw(texture, min.x, min.y);
    }

    @Override
    public boolean touch(Vector3 touchPos) {
        if (animationPlayer.isAnimating()) return false;

        if (contains(touchPos)){
            state = ButtonVisualState.CLICKED;
            animationPlayer.start();
            return true;
        }
        return false;
    }

    private boolean contains(Vector3 point){
        if (type.equals(ButtonType.START)){
            return point.x > min.x + 5 && point.x < max.x - 3 &&
                point.y > min.y + 2 && point.y < max.y - 15;
        }
        return point.x > min.x + 2 && point.x < max.x &&
            point.y > min.y + 2 && point.y < max.y - 6;
    }

    @Override
    public void dispose() {
        buttonTextureGetter.dispose();
    }
}

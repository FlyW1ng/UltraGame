package com.flyw1ng.ultragame.gui.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.flyw1ng.ultragame.GameManager;
import com.flyw1ng.ultragame.anim.AnimationPlayer;
import com.flyw1ng.ultragame.anim.TimerAnimation;
import com.flyw1ng.ultragame.gui.Action;
import com.flyw1ng.ultragame.gui.UIElement;
import com.flyw1ng.ultragame.gui.texture.ThemeManager;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

public class Button implements UIElement, Disposable {
    private Vector3 position;
    private Rectangle hitbox;
    private ButtonType type;
    private Action action;
    private AnimationPlayer animationPlayer;
    private ButtonTextureGetter buttonTextureGetter;
    private final ThemeManager themeManager;
    private ButtonVisualState state = ButtonVisualState.IDLE;

    public Button(float x, float y, Rectangle hitbox,
                  ButtonType type,
                  Action action,
                  AnimationPlayer animationPlayer,
                  ButtonTextureGetter buttonTextureGetter,
                  ThemeManager themeManager){

        this.position = new Vector3(x, y, 0);
        this.hitbox = hitbox;
        this.type = type;
        this.action = action;
        this.animationPlayer = animationPlayer;
        this.buttonTextureGetter = buttonTextureGetter;
        this.themeManager = themeManager;
    }
    @Override
    public void update(){
        animationPlayer.update();

        if (!animationPlayer.isAnimating()){
            state = ButtonVisualState.IDLE;
        }
        if (animationPlayer.isFinished()){
            action.execute();
        }
    }
    @Override
    public void renderPixmap(Pixmap pixmap) {
        Texture texture = buttonTextureGetter.get(type, state, themeManager.getCurrentTheme());

        if (!texture.getTextureData().isPrepared()){
            texture.getTextureData().prepare();
        }

        pixmap.drawPixmap(texture.getTextureData().consumePixmap(),
            (int) (position.x),
            (int)(pixmap.getHeight() - position.y - texture.getHeight()));
    }
    @Override
    public void render(SpriteBatch spriteBatch){
        Texture texture = buttonTextureGetter.get(type, state, themeManager.getCurrentTheme());
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        spriteBatch.draw(texture, position.x, position.y);
    }

    @Override
    public boolean touch(Vector3 touchPos) {
        if (animationPlayer.isAnimating()) return false;

        if (hitbox.contains(touchPos.x, touchPos.y)){
            state = ButtonVisualState.CLICKED;
            animationPlayer.start();
            return true;
        }
        return false;
    }

    @Override
    public void dispose() {
        buttonTextureGetter.dispose();
    }
}

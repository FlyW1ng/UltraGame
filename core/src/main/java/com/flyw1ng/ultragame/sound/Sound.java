package com.flyw1ng.ultragame.sound;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.flyw1ng.ultragame.GameManager;
import com.flyw1ng.ultragame.anim.AnimationPlayer;
import com.flyw1ng.ultragame.gui.Action;
import com.flyw1ng.ultragame.gui.UIElement;
import com.flyw1ng.ultragame.gui.texture.ThemeType;
import com.flyw1ng.ultragame.settings.GameSettings;

public class Sound implements UIElement, Disposable {

    private final Vector3 min;
    private final Vector3 max;
    private final SoundType type;
    private AnimationPlayer animationPlayer;
    SoundTextureGetter soundTextureGetter;
    private ThemeType themeType;
    private Action action;
    private GameSettings gameSettings;



    public Sound(Vector2 min, Vector2 max,
                 SoundType type,
                 Action action,
                 GameSettings gameSettings,
                 AnimationPlayer animationPlayer,
                 SoundTextureGetter soundTextureGetter){
        this.min = new Vector3(min.x, min.y, 0);
        this.max = new Vector3(max.x, max.y, 0);
        this.type = type;
        this.animationPlayer = animationPlayer;
        this.soundTextureGetter = soundTextureGetter;
        this.action = action;
        this.gameSettings = gameSettings;
        themeType = ThemeType.LIGHT;
    }

    @Override
    public void update() {
//            animationPlayer.setReverse(gameSettings.getMusicState().equals(GameSettings.STATE.OFF.toString()));

        animationPlayer.update();

        if (GameManager.ThemeTypeNow.equals(ThemeType.LIGHT)){
            themeType = ThemeType.LIGHT;
        } else {
            themeType = ThemeType.DARK;
        }

        if (animationPlayer.isFinished()){
            action.execute();
            animationPlayer.stop();
        }

    }

    @Override
    public void renderPixmap(Pixmap pixmap) {
        Texture texture = soundTextureGetter.get(type, themeType, SoundState.get(animationPlayer.getFrame()));

        if (!texture.getTextureData().isPrepared()){
            texture.getTextureData().prepare();
        }
        pixmap.drawPixmap(texture.getTextureData().consumePixmap(),
            (int) (min.x),
            (int) (pixmap.getHeight() - max.y));

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        Texture texture = soundTextureGetter.get(type, themeType, SoundState.get(animationPlayer.getFrame()));

        spriteBatch.draw(texture, min.x, min.y);
    }
    @Override
    public boolean touch(Vector3 touchPos){
        if (animationPlayer.isAnimating()) return false;

        if (type == SoundType.MUSIC) {
            if (gameSettings.getMusicState().equals(GameSettings.STATE.OFF.toString()))
                animationPlayer.setDirection(AnimationPlayer.Direction.BACKWARD);
            else
                animationPlayer.setDirection(AnimationPlayer.Direction.FORWARD);
        } else { // SoundType.SOUND
            if (gameSettings.getSoundState().equals(GameSettings.STATE.OFF.toString()))
                animationPlayer.setDirection(AnimationPlayer.Direction.BACKWARD);
            else
                animationPlayer.setDirection(AnimationPlayer.Direction.FORWARD);
        }


        if (contains(touchPos)){
            animationPlayer.start();
            return true;
        }
        return false;
    }

    private boolean contains(Vector3 point){
        return point.x > min.x && point.x < max.x &&
            point.y > min.y + 6 && point.y < max.y;
    }

    @Override
    public void dispose() {
        soundTextureGetter.dispose();
    }
}

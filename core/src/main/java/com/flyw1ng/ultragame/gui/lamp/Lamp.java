package com.flyw1ng.ultragame.gui.lamp;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.flyw1ng.ultragame.anim.AnimationPlayer;
import com.flyw1ng.ultragame.gui.Action;
import com.flyw1ng.ultragame.gui.UIElement;
import com.flyw1ng.ultragame.gui.texture.ThemeManager;
import com.flyw1ng.ultragame.gui.texture.ThemeType;

public class Lamp implements UIElement, Disposable {
    private LampType lampType;
    private AnimationPlayer animationPlayer;
    private LampTextureGetter lampTextureGetter;
    private Action lampAction;
    private Vector3 min;
    private Vector3 max;
    private final ThemeManager themeManager;

    public Lamp(Vector2 minV2, Vector2 maxV2, LampType lampType,
                AnimationPlayer animationPlayer,
                Action action,
                LampTextureGetter lampTextureGetter,
                ThemeManager themeManager){

        this.animationPlayer = animationPlayer;
        this.lampTextureGetter = lampTextureGetter;
        this.lampType = lampType;
        lampAction = action;

        min = new Vector3(minV2.x, minV2.y, 0);
        max = new Vector3(maxV2.x, maxV2.y, 0);

        this.themeManager = themeManager;

    }
    @Override
    public void update(){
//        animationPlayer.setReverse(GameManager.ThemeTypeNow == ThemeType.DARK);

        animationPlayer.update();

        if (animationPlayer.isFinished()){
            lampAction.execute();
        }

        if (!animationPlayer.isAnimating()){
            if (themeManager.getCurrentTheme() == ThemeType.DARK){
                animationPlayer.setCurrentFrame(5);
            }
            else animationPlayer.setCurrentFrame(0);
        }
    }

    @Override
    public void renderPixmap(Pixmap pixmap) {

        Texture texture = lampTextureGetter.get(lampType, LampState.get(animationPlayer.getFrame()));

        if (!texture.getTextureData().isPrepared()){
            texture.getTextureData().prepare();
        }
        pixmap.drawPixmap(texture.getTextureData().consumePixmap(),
            (int) (min.x),
            (int) (pixmap.getHeight() - max.y));
    }
    @Override
    public void render(SpriteBatch spriteBatch){
        Texture texture = lampTextureGetter.get(lampType, LampState.get(animationPlayer.getFrame()));

        spriteBatch.draw(texture, min.x, min.y);
    }

    @Override
    public boolean touch(Vector3 touchPos) {
        if (animationPlayer.isAnimating()) return false;

        if (themeManager.getCurrentTheme() == ThemeType.DARK){
            animationPlayer.setDirection(AnimationPlayer.Direction.BACKWARD);
        }
        else animationPlayer.setDirection(AnimationPlayer.Direction.FORWARD);
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
        lampTextureGetter.dispose();
    }
}

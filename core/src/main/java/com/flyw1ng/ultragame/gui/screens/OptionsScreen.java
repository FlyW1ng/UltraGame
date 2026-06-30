package com.flyw1ng.ultragame.gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.flyw1ng.ultragame.Main;
import com.flyw1ng.ultragame.gui.MenuScene;
import com.flyw1ng.ultragame.gui.builders.OptionsSoundBuilder;

public class OptionsScreen extends ScreenAdapter {
    private final Main game;
    private final FitViewport viewport;
    private final MenuScene optionsScene;
    private final MainMenuScreen previousScreen;
    private float progress = 0f;
    private boolean isExiting = false;

    public OptionsScreen(Main game, MainMenuScreen previousScreen){
        this.game = game;
        this.viewport = new FitViewport(96, 192);
        this.previousScreen = previousScreen;

        OptionsSoundBuilder builder = new OptionsSoundBuilder(game.textureLoader, game.spriteBatch, game.themeManager, game.gameSettings, game);
        this.optionsScene = builder.build();

    }
    public void close(){
        isExiting = true;
    }

    @Override
    public void show() {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                if (progress < 1f || isExiting) return false;

                Vector3 touchPos = new Vector3(screenX, screenY, 0);
                viewport.unproject(touchPos);
                optionsScene.touch(touchPos);
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {

        if (isExiting){
            progress -= delta * 3f; //Скорость закрытия (1/3 сек)
            if (progress <= 0){
                game.setScreen(previousScreen);
                return;
            }
        } else {
            progress += delta * 3f; // Скорость открытия
            if (progress > 1f) progress = 1f;
        }

        ScreenUtils.clear(Color.SALMON);

        if (previousScreen != null){
            previousScreen.draw();
        }
        if (progress == 1f && !isExiting){
            optionsScene.update();
        }

        viewport.apply();

        float offset = 96f * (1f - progress);
        Matrix4 transform = viewport.getCamera().combined.cpy();
        transform.translate(offset, 0, 0);

        game.spriteBatch.setProjectionMatrix(transform);
        game.spriteBatch.begin();
        optionsScene.render();
        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        optionsScene.dispose();
    }
}

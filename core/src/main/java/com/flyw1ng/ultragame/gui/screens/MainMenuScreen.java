package com.flyw1ng.ultragame.gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.flyw1ng.ultragame.Main;
import com.flyw1ng.ultragame.gui.MenuScene;
import com.flyw1ng.ultragame.gui.builders.MainMenuBuilder;

public class MainMenuScreen extends ScreenAdapter {
    private final Main game;
    private final FitViewport viewport;
    private final MenuScene menuScene;

    public MainMenuScreen(Main game){
        this.game = game;
        this.viewport = new FitViewport(96, 192);

        MainMenuBuilder builder = new MainMenuBuilder(game.textureLoader,
            game.spriteBatch,
            game.themeManager,
            game.gameSettings,
            game);
        this.menuScene = builder.build();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.SALMON);

        menuScene.update();
        draw();

    }
    public void draw(){
        viewport.apply();
        game.spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        game.spriteBatch.begin();
        menuScene.render();
        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void show() {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button){
                Vector3 touchPos = new Vector3(screenX, screenY,0);
                viewport.unproject(touchPos);
                menuScene.touch(touchPos);
                return true;
            }
        });
    }

    @Override
    public void dispose() {
        menuScene.dispose();
    }
}

package com.flyw1ng.ultragame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import com.flyw1ng.ultragame.gui.MainGUI;
import com.flyw1ng.ultragame.gui.texture.TextureLoader;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch spriteBatch;
    private FitViewport viewport;
    private MainGUI mainGUI;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(96,192);
        GameManager.GameStateNow = GameManager.GameState.MAIN_MENU;
        mainGUI = new MainGUI(new TextureLoader(), spriteBatch);

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.SALMON);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();

        renderClass();

        spriteBatch.end();
    }
    private void renderClass(){
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        mainGUI.setGameState(GameManager.GameStateNow);

        switch (GameManager.GameStateNow){
            case MAIN_MENU:
            {
                mainGUI.update();
                mainGUI.render();
                break;
            }
            case OPTIONS:
            {
                mainGUI.update();
                mainGUI.render();
                break;
            }
            case GAME:
            {break;}
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        mainGUI.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3(screenX,
             screenY, 0);
        viewport.unproject(touchPos);

        mainGUI.touch(touchPos);

        return true; // true означает, что событие обработано
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

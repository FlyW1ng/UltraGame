package com.flyw1ng.ultragame;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flyw1ng.ultragame.gui.MainMenuScreen;
import com.flyw1ng.ultragame.gui.texture.TextureLoader;
import com.flyw1ng.ultragame.gui.texture.ThemeManager;
import com.flyw1ng.ultragame.settings.GameSettings;
import com.flyw1ng.ultragame.sound.SoundManager;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch spriteBatch;
    public TextureLoader textureLoader;
    public ThemeManager themeManager;
    public GameSettings gameSettings;
    public SoundManager soundManager;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        textureLoader = new TextureLoader();
        themeManager = new ThemeManager();
        gameSettings = new GameSettings();

        soundManager = new SoundManager();
        soundManager.loadSound().setLoop();
        SoundManager.backgroundMusic.setVolume(gameSettings.getMusicVolume());
        SoundManager.backgroundMusic.play();

        this.setScreen(new MainMenuScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}

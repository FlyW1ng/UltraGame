package com.flyw1ng.ultragame.gui.builders;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.flyw1ng.ultragame.Main;
import com.flyw1ng.ultragame.anim.Animation;
import com.flyw1ng.ultragame.anim.AnimationPlayer;
import com.flyw1ng.ultragame.gui.MenuScene;
import com.flyw1ng.ultragame.gui.UIElement;
import com.flyw1ng.ultragame.gui.background.Background;
import com.flyw1ng.ultragame.gui.background.BackgroundType;
import com.flyw1ng.ultragame.gui.background.BackgroundUI;
import com.flyw1ng.ultragame.gui.buttons.Button;
import com.flyw1ng.ultragame.gui.buttons.ButtonType;
import com.flyw1ng.ultragame.gui.buttons.ButtonUI;
import com.flyw1ng.ultragame.gui.lamp.Lamp;
import com.flyw1ng.ultragame.gui.lamp.LampType;
import com.flyw1ng.ultragame.gui.lamp.LampUI;
import com.flyw1ng.ultragame.gui.panels.Panel;
import com.flyw1ng.ultragame.gui.panels.PanelType;
import com.flyw1ng.ultragame.gui.panels.PanelUI;
import com.flyw1ng.ultragame.gui.screens.MainMenuScreen;
import com.flyw1ng.ultragame.gui.screens.OptionsScreen;
import com.flyw1ng.ultragame.gui.texture.TextureLoader;
import com.flyw1ng.ultragame.gui.texture.ThemeManager;
import com.flyw1ng.ultragame.settings.GameSettings;
import com.flyw1ng.ultragame.sound.Sound;
import com.flyw1ng.ultragame.sound.SoundManager;
import com.flyw1ng.ultragame.sound.SoundType;
import com.flyw1ng.ultragame.sound.SoundUI;

import java.util.ArrayList;
import java.util.List;

public class OptionsSoundBuilder {
    private final Main game;
    private final TextureLoader textureLoader;
    private final SpriteBatch spriteBatch;
    private final ThemeManager themeManager;
    private final GameSettings gameSettings;

    public OptionsSoundBuilder(TextureLoader textureLoader,
                               SpriteBatch spriteBatch,
                               ThemeManager themeManager,
                               GameSettings gameSettings,
                               Main game){
        this.textureLoader = textureLoader;
        this.spriteBatch = spriteBatch;
        this.themeManager = themeManager;
        this.gameSettings = gameSettings;
        this.game = game;
    }

    public MenuScene build(){
        List<UIElement> elements = new ArrayList<>();

        BackgroundUI backgroundUI = new BackgroundUI(textureLoader);
        Background background = new Background(BackgroundType.OPTIONS,
            backgroundUI,
            themeManager);
        elements.add(background);

        LampUI lampUI = new LampUI(textureLoader);

        Lamp lamp = new Lamp(new Vector2(41, 175),
            new Vector2(53, 190),
            LampType.MAIN_MENU,
            new AnimationPlayer(new Animation(6, 0.2f, false)),
            this::clickLamp,
            lampUI,
            themeManager);
        elements.add(lamp);

        PanelUI panelUI = new PanelUI(textureLoader);
        Panel panel = new Panel(new Vector2(11, 54),
            new Vector2(85, 143),
            PanelType.OPTIONS,
            panelUI,
            themeManager);
        elements.add(panel);

        SoundUI soundUI = new SoundUI(textureLoader);

        Sound sound = new Sound(new Vector2(27, 100),
            new Vector2(71, 115),
            SoundType.SOUND,
            this::sound,
            gameSettings,
            new AnimationPlayer(new Animation(3, 0.1f, false)),
            soundUI,
            themeManager);
        Sound music = new Sound(new Vector2(27, 74),
            new Vector2(71, 89),
            SoundType.MUSIC,
            this::music,
            gameSettings,
            new AnimationPlayer(new Animation(3, 0.1f, false)),
            soundUI,
            themeManager);
        elements.add(sound);
        elements.add(music);


        ButtonUI buttonUI = new ButtonUI(textureLoader);
        Button buttonOK = new Button(31, 19, new Rectangle(31, 19, 34, 37),
            ButtonType.OK,
            this::executeOk,
            new AnimationPlayer(new Animation(2, 0.07f, false)),
            buttonUI,
            themeManager);
        elements.add(buttonOK);
        sortElements(elements);

        return new MenuScene(elements, spriteBatch, themeManager);
    }

    private void sortElements(List<UIElement> uiElements){
        uiElements.sort((a, b) -> {
            if (a instanceof Background && !(b instanceof Background)) {
                return -1; // a — Background, b — нет → a должен быть раньше b
            }
            if (!(a instanceof Background) && b instanceof Background) {
                return 1;  // b — Background, a — нет → a должен быть позже b
            }
            return 0;      // оба Background или оба не Background → порядок не меняется
        });
    }
    private void executeOk(){
        SoundManager.click.play(getSoundVolume());
        if (game.getScreen() instanceof OptionsScreen){
            ((OptionsScreen) game.getScreen()).close();
        }
    }
    private void clickLamp(){
        SoundManager.clickLamp.play(getSoundVolume());
        themeManager.toggleTheme();
    }
    private void music(){
        SoundManager.click.play();
        if (gameSettings.getMusicState().equals(GameSettings.STATE.OFF.toString())) {
            gameSettings.setMusicState(GameSettings.STATE.ON);
            SoundManager.backgroundMusic.play();
        }
        else{
            gameSettings.setMusicState(GameSettings.STATE.OFF);
            SoundManager.backgroundMusic.pause();
        }
    }
    private void sound(){
        SoundManager.click.play();
        if (gameSettings.getSoundState().equals(GameSettings.STATE.OFF.toString())) {
            gameSettings.setSoundState(GameSettings.STATE.ON);
        }
        else {
            gameSettings.setSoundState(GameSettings.STATE.OFF);
        }

    }
    private float getSoundVolume() {
        return gameSettings.getSoundState().equals(GameSettings.STATE.ON.toString()) ? 1.0f : 0.0f;
    }
}
class OptionsVolumeConstants{

}

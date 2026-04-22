package com.flyw1ng.ultragame.gui.builders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.flyw1ng.ultragame.GameManager;
import com.flyw1ng.ultragame.anim.Animation;
import com.flyw1ng.ultragame.anim.AnimationPlayer;
import com.flyw1ng.ultragame.anim.FrameAnimation;
import com.flyw1ng.ultragame.anim.TimerAnimation;
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
import com.flyw1ng.ultragame.gui.texture.TextureLoader;
import com.flyw1ng.ultragame.gui.texture.ThemeManager;
import com.flyw1ng.ultragame.settings.GameSettings;
import com.flyw1ng.ultragame.sound.SoundManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Строитель главного меню. Создаёт и конфигурирует все UI-элементы.
 */
public class MainMenuBuilder {
    private final TextureLoader textureLoader;
    private final SpriteBatch spriteBatch;
    private final ThemeManager themeManager;
    private final GameSettings gameSettings;

    public MainMenuBuilder(TextureLoader textureLoader, SpriteBatch spriteBatch, ThemeManager themeManager, GameSettings gameSettings) {
        this.textureLoader = textureLoader;
        this.spriteBatch = spriteBatch;
        this.themeManager = themeManager;
        this.gameSettings = gameSettings;
    }

    public MenuScene build() {
        List<UIElement> elements = new ArrayList<>();

        // Фон
        BackgroundUI backgroundUI = new BackgroundUI(textureLoader);
        Background background = new Background(BackgroundType.MAIN_MENU, backgroundUI);
        elements.add(background);

        // Лампа с переключением темы
        LampUI lampUI = new LampUI(textureLoader);
//        FrameAnimation lampAnimation = new FrameAnimation(6, 7, false, false);
        AnimationPlayer animationPlayer = new AnimationPlayer(new Animation(6, 0.2f, false));
        Lamp lamp = new Lamp(new Vector2(41, 175),
            new Vector2(53, 190),
            LampType.MAIN_MENU,
            animationPlayer,
            this::clickLamp,
            lampUI);
        elements.add(lamp);

        // Кнопки
        ButtonUI buttonUI = new ButtonUI(textureLoader);

        Button startButton = new Button(11, MainMenuConstants.START_BUTTON_Y, 83, 143,
            ButtonType.START,
            this::executePlay,
            new AnimationPlayer(new Animation(2, 0.07f,false)),
            buttonUI);

        Button optionsButton = new Button(14, MainMenuConstants.OPTIONS_BUTTON_Y, 80, 100,
            ButtonType.OPTIONS,
            this::executeOptions,
            new AnimationPlayer(new Animation(2, 0.07f,false)),
            buttonUI);

        Button exitButton = new Button(14, MainMenuConstants.EXIT_BUTTON_Y, 80, 66,
            ButtonType.EXIT,
            this::executeExit,
            new AnimationPlayer(new Animation(2, 0.07f,false)),
            buttonUI);

        elements.add(startButton);
        elements.add(optionsButton);
        elements.add(exitButton);

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
    private void executeOptions(){
        SoundManager.click.play(getSoundVolume());
        GameManager.GameStateNow = GameManager.GameState.OPTIONS;
    }
    private void executePlay(){
        SoundManager.click.play(getSoundVolume());
    }
    private void executeExit() {
        SoundManager.click.play(getSoundVolume());
        Gdx.app.exit();
    }
    private void clickLamp(){
//        SoundManager.clickLamp.play();
        SoundManager.click.play(getSoundVolume());
        themeManager.toggleTheme();
    }
    private float getSoundVolume() {
        return gameSettings.getSoundState().equals(GameSettings.STATE.ON.toString()) ? 1.0f : 0.0f;
    }

}
/**
 * Константы позиций элементов меню.
 */
class MainMenuConstants{
    public static final int START_BUTTON_Y = 96;
    public static final int OPTIONS_BUTTON_Y = 62;
    public static final int EXIT_BUTTON_Y = 28;
}

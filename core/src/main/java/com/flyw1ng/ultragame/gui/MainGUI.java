//package com.flyw1ng.ultragame.gui;
//
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.math.Vector3;
//import com.flyw1ng.ultragame.anim.Animation;
//import com.flyw1ng.ultragame.anim.AnimationPlayer;
//import com.flyw1ng.ultragame.gui.builders.MainMenuBuilder;
//import com.flyw1ng.ultragame.gui.builders.OptionsSoundBuilder;
//import com.flyw1ng.ultragame.gui.texture.TextureLoader;
//import com.flyw1ng.ultragame.gui.texture.ThemeManager;
//import com.flyw1ng.ultragame.settings.GameSettings;
//import com.flyw1ng.ultragame.sound.SoundManager;
//
//
///**
// * Фасад главного меню. Делегирует управление UI-элементами MenuScene.
// */
//public class MainGUI  {
//    private final MenuScene mainMenuScene;
//    private final MenuScene optionsVolumeScene;
//    private AnimationPlayer animationPlayer;
//    private SpriteBatch spriteBatch;
//
//    public MainGUI(TextureLoader textureLoader, SpriteBatch spriteBatch) {
//        this.spriteBatch = spriteBatch;
//        ThemeManager themeManager = new ThemeManager();
//        GameSettings gameSettings = new GameSettings();
//        MainMenuBuilder mainMenuBuilder = new MainMenuBuilder(textureLoader, spriteBatch, themeManager, gameSettings);
//        OptionsSoundBuilder optionsSoundBuilder = new OptionsSoundBuilder(textureLoader, spriteBatch, themeManager, gameSettings);
//        this.mainMenuScene = mainMenuBuilder.build();
//        this.optionsVolumeScene = optionsSoundBuilder.build();
//        gameState = GameManager.GameState.MAIN_MENU;
//        lastGameState = GameManager.GameState.MAIN_MENU;
//        animationPlayer = new AnimationPlayer(new Animation(mainMenuScene.getStaticTexture().getWidth() / 3, 0.015f , false));
//        SoundManager soundManager = new SoundManager();
//
//        soundManager.loadSound().setLoop();
//        SoundManager.backgroundMusic.setVolume(gameSettings.getMusicVolume());
//        SoundManager.backgroundMusic.play();
//    }
//
//    public void touch(Vector3 touchPos) {
//        if (animationPlayer.isAnimating()) return;
//
//        if (checkGameState()){
//            mainMenuScene.touch(touchPos);
//        }else optionsVolumeScene.touch(touchPos);
//    }
//
//    public void update() {
//        animationPlayer.update();
//        if (checkGameState()){
//            mainMenuScene.update();
//        } else optionsVolumeScene.update();
//    }
//
//    public void render() {
//        if (checkGameState()){
//            if (lastGameState.equals(GameManager.GameState.MAIN_MENU)){
//                if (!animationPlayer.isAnimating()){
//                    mainMenuScene.render();
//                }
//                if (animationPlayer.isAnimating()){
//                    spriteBatch.draw(mainMenuScene.getStaticTexture(),0, 0);
//                    spriteBatch.draw(optionsVolumeScene.getStaticTexture(),
//                        animationPlayer.getFrame() * 3, 0);
//                }
//            }
//            else {
//                if (animationPlayer.getStatus() == AnimationPlayer.ANIMSTATUS.NONE){
//                    animationPlayer.start();
//                    optionsVolumeScene.render();
//                }
//                else {
//                    spriteBatch.draw(mainMenuScene.getStaticTexture(),0, 0);
//                    spriteBatch.draw(optionsVolumeScene.getStaticTexture(),
//                        animationPlayer.getFrame() * 3, 0);
//                }
//            }
//
//        } else {
//            if (lastGameState.equals(GameManager.GameState.OPTIONS)){
//                if (!animationPlayer.isAnimating()){
//                    optionsVolumeScene.render();
//                }
//                if (animationPlayer.isAnimating()){
//                    spriteBatch.draw(mainMenuScene.getStaticTexture(),0, 0);
//                    spriteBatch.draw(optionsVolumeScene.getStaticTexture(),
//                        optionsVolumeScene.getStaticTexture().getWidth() - animationPlayer.getFrame() * 3, 0);
//                }
//            }
//            else {
//                if (animationPlayer.getStatus().equals(AnimationPlayer.ANIMSTATUS.NONE)){
//                    animationPlayer.start();
//                    mainMenuScene.render();
//                }
//                else {
//                    spriteBatch.draw(mainMenuScene.getStaticTexture(),0, 0);
//                    spriteBatch.draw(optionsVolumeScene.getStaticTexture(),
//                        optionsVolumeScene.getStaticTexture().getWidth() - animationPlayer.getFrame() * 3, 0);
//                }
//            }
//
//        }
////        spriteBatch.draw(optionsVolumeScene.getStaticTexture(), 0, 0);
//        lastGameState = gameState;
//    }
//    public void setGameState(GameManager.GameState gameState){
//        this.gameState = gameState;
//    }
//    private boolean checkGameState(){
//        return gameState.equals(GameManager.GameState.MAIN_MENU);
//    }
//
//    public void dispose() {
//        mainMenuScene.dispose();
//        optionsVolumeScene.dispose();
//    }
//}

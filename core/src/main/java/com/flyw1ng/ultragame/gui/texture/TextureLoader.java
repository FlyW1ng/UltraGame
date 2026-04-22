package com.flyw1ng.ultragame.gui.texture;

import com.badlogic.gdx.graphics.Texture;
import com.flyw1ng.ultragame.gui.buttons.ButtonType;
import com.flyw1ng.ultragame.gui.buttons.ButtonVisualState;
import com.flyw1ng.ultragame.gui.background.BackgroundType;
import com.flyw1ng.ultragame.gui.lamp.LampState;
import com.flyw1ng.ultragame.gui.lamp.LampType;
import com.flyw1ng.ultragame.gui.panels.PanelType;
import com.flyw1ng.ultragame.sound.SoundState;
import com.flyw1ng.ultragame.sound.SoundType;

import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

public class TextureLoader implements TextureProvider{

    @Override

    public Map<BackgroundType, Map<ThemeType, Texture>> loadBackgroundTextures(){
        Map<BackgroundType, Map<ThemeType, Texture>> map = new EnumMap<>(BackgroundType.class);

        for (BackgroundType type : BackgroundType.values()){

            Map<ThemeType, Texture> themeTextureMap = new EnumMap<>(ThemeType.class);

            String base = "gui/" + type.name().toLowerCase() + "_background";

            themeTextureMap.put(ThemeType.LIGHT,
                new Texture(base + "_light.png"));
            themeTextureMap.put(ThemeType.DARK,
                new Texture(base + "_dark.png"));

            map.put(type,themeTextureMap);
        }
        return map;
    }
    @Override
    public Map<ButtonType, Map<ButtonVisualState, Map<ThemeType, Texture>>> loadButtonTextures() {

        Map<ButtonType, Map<ButtonVisualState, Map<ThemeType, Texture>>> map = new EnumMap<>(ButtonType.class);

            for (ButtonType type : ButtonType.values()) {

                Map<ButtonVisualState, Map<ThemeType, Texture>> stateMap = new EnumMap<>(ButtonVisualState.class);

                for (ButtonVisualState state : ButtonVisualState.values()){

                    Map<ThemeType, Texture> themeMap = new EnumMap<>(ThemeType.class);

                    for (ThemeType themeType : ThemeType.values()){

                        themeMap.put(themeType,
                            new Texture(buildButtonTexturePath(type, state, themeType)));
                    }

                    stateMap.put(state,
                        themeMap);
                }
                map.put(type, stateMap);
            }
        return map;
    }
    private String buildButtonTexturePath(ButtonType buttonType, ButtonVisualState state, ThemeType themeType){

        String theme = themeType.name().toLowerCase(Locale.ROOT);
        String button = buttonType.name().toLowerCase(Locale.ROOT);
        String buttonState = state.name().toLowerCase(Locale.ROOT);

        return "buttons/" + theme + "/button_"
            + button +"_" + buttonState +
            "_" + theme + ".png";

    }
    @Override
    public Map<LampType, Map<LampState, Texture>> loadLampTextures(){
        Map<LampType, Map<LampState, Texture>> map = new EnumMap<>(LampType.class);

        for (LampType type : LampType.values()){
            Map<LampState, Texture> stateMap = new EnumMap<>(LampState.class);


            for (LampState state: LampState.values()) {
                String base = "lamp/" + type.name().toLowerCase() + "_" +state.name().toLowerCase() + ".png";
                stateMap.put(state, new Texture(base));
            }
            map.put(type, stateMap);
        }
        return map;
    }

    @Override
    public Map<PanelType, Map<ThemeType, Texture>> loadPanelTextures() {
        Map<PanelType, Map<ThemeType, Texture>> map = new EnumMap<>(PanelType.class);

        for (PanelType type : PanelType.values()){
            Map<ThemeType, Texture> themeTextureMap = new EnumMap<>(ThemeType.class);

            String base = "gui/panels/" + type.name().toLowerCase() + "_panel";

            for (ThemeType themeType : ThemeType.values()){
                themeTextureMap.put(themeType, new Texture(base + "_" + themeType + ".png"));
            }
            map.put(type, themeTextureMap);
        }
        return map;
    }
    @Override
    public Map<SoundType, Map<ThemeType, Map<SoundState, Texture>>> loadSoundTextures(){
        Map<SoundType, Map<ThemeType, Map<SoundState, Texture>>> map = new EnumMap<>(SoundType.class);

        for (SoundType type : SoundType.values()){
            Map<ThemeType, Map<SoundState, Texture>> stateMap = new EnumMap<>(ThemeType.class);

            for (ThemeType theme : ThemeType.values()){
                Map<SoundState, Texture> themeMap = new EnumMap<>(SoundState.class);

                for (SoundState state : SoundState.values()){

                    themeMap.put(state,
                        new Texture(buildSoundTexturePath(type, theme,state)));
                }
                stateMap.put(theme, themeMap);
            }
            map.put(type, stateMap);
        }
        return map;
    }
    private String buildSoundTexturePath(SoundType type, ThemeType themeType, SoundState state){

        return "gui/sound/" + themeType.name().toLowerCase(Locale.ROOT) +
            "/" + type.name().toLowerCase(Locale.ROOT) + "_" +
            state.name().toLowerCase(Locale.ROOT) + "_" +
            themeType.name().toLowerCase(Locale.ROOT) + ".png";

    }


}

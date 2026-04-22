package com.flyw1ng.ultragame.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameSettings {
    private static final String PREFS_NAME = "MyGameSettings";
    private static final String MUSIC_VOLUME_KEY = "music_volume";
    private static final String MUSIC_STATE_KEY = "music_state";
    private static final String SOUND_STATE_KEY = "sound_state";



    // Значения по умолчанию
    private static final float DEFAULT_MUSIC_VOLUME = 0.2f;
    private static final STATE DEFAULT_SOUND_STATE = STATE.ON;
    public static float musicVolume = DEFAULT_MUSIC_VOLUME;
    public static STATE musicState = DEFAULT_SOUND_STATE;
    public static STATE soundState = DEFAULT_SOUND_STATE;

    private final Preferences prefs;

    public GameSettings() {
        prefs = Gdx.app.getPreferences(PREFS_NAME);
        musicVolume = DEFAULT_MUSIC_VOLUME;
        musicState = DEFAULT_SOUND_STATE;
        soundState = DEFAULT_SOUND_STATE;
        setMusicVolume(DEFAULT_MUSIC_VOLUME);
        setSoundState(soundState);
        setMusicState(musicState);
    }

    public float getMusicVolume() {
        return prefs.getFloat(MUSIC_VOLUME_KEY);
    }

    public void setMusicVolume(float volume) {
        prefs.putFloat(MUSIC_VOLUME_KEY, volume);
        prefs.flush();
    }
    public String getMusicState(){
        return prefs.getString(MUSIC_STATE_KEY);
    }
    public void setMusicState(STATE state){
        musicState = state;
        prefs.putString(MUSIC_STATE_KEY, state.name());
        prefs.flush();
    }
    public String getSoundState(){
        return prefs.getString(SOUND_STATE_KEY);
    }
    public void setSoundState(STATE state){
        soundState = state;
        prefs.putString(SOUND_STATE_KEY, state.name());
        prefs.flush();
    }

    public enum STATE{
        ON,
        OFF
    }
}



package com.flyw1ng.ultragame.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
    public static Music backgroundMusic;
    public static Sound click;
    public static Sound clickLamp;

    public SoundManager(){

    }
    public SoundManager loadSound(){
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.local("sound/music/background_music.mp3"));
        click = Gdx.audio.newSound(Gdx.files.local("sound/click/click1.wav"));
        clickLamp = Gdx.audio.newSound(Gdx.files.local("sound/click/tone.wav"));
        return this;
    }
    public void setLoop(){
       backgroundMusic.setLooping(true);
    }

}

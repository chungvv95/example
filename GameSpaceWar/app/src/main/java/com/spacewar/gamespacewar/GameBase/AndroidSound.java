package com.spacewar.gamespacewar.GameBase;

import android.media.SoundPool;

import com.spacewar.gamespacewar.GameInterface.Sound;

/**
 * Created by acer on 10/01/2018.
 */

public class AndroidSound implements Sound {
    int soundId;
    SoundPool soundPool;
    public AndroidSound(SoundPool soundPool, int soundId) {
        this.soundId = soundId;
        this.soundPool = soundPool;
    }
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void setLooping(boolean looping) {

    }

    @Override
    public void setVolume(float volume) {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public boolean isStopped() {
        return false;
    }

    @Override
    public boolean isLooping() {
        return false;
    }

    public void dispose() {
        soundPool.unload(soundId);
    }
}

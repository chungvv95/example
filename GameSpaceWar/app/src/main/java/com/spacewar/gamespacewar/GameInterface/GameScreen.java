package com.spacewar.gamespacewar.GameInterface;

import android.graphics.Canvas;

/**
 * Created by acer on 09/01/2018.
 */

public interface GameScreen {
    public enum SCREEN_TYPE {
        SCREEN_NONE,
        SCREEN_LOADING,
        SCEEN_MENU,
        SCREEN_OPTION,
        SCREEN_HIGHSCORE,
        SCREEN_PLAYGAME,

        SCREEN_MAX,
    }
    public enum GameState {
        Ready,
        Running,
        Paused,
        GameOver
    }


    public GameState getGameState();
    public int getWidth();
    public int getHeigh();
    public SCREEN_TYPE getScreenID();
    public String getScreenName();

    public void Draw(Canvas canvas);
    public void Update(float deltaTime);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}

package com.spacewar.gamespacewar.Screens;

import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.GameInterface.GameScreen;

/**
 * Created by acer on 09/01/2018.
 */

public abstract class ScreenBase implements GameScreen {
    protected GameActivityInterface game;
    protected SCREEN_TYPE type;
    public ScreenBase(GameActivityInterface game, SCREEN_TYPE type) {
        this.game = game;
        this.type = type;
    }

    @Override
    public int getWidth() {
        return this.game.getWidth();
    }

    @Override
    public int getHeigh() {
        return this.game.getHeigh();
    }

    @Override
    public SCREEN_TYPE getScreenID() {
        return this.type;
    }

    @Override
    public String getScreenName() {
        String screenName = "";
        switch (this.type) {
            case SCEEN_MENU:
                screenName = "SCREEN MENU";
                break;
            case SCREEN_LOADING:
                screenName = "SCREEN LOADING";
                break;
            default:
                break;
        }
        return screenName;
    }
}

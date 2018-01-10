package com.spacewar.gamespacewar.Screens;

import android.graphics.Rect;
import android.util.Log;

import com.spacewar.gamespacewar.GameBase.BaseActivity;
import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.GameInterface.GameScreen;
import com.spacewar.gamespacewar.GameInterface.Input;
import com.spacewar.gamespacewar.Stores.GameStatic;
import com.spacewar.gamespacewar.Stores.GameUtils;

/**
 * Created by acer on 09/01/2018.
 */

public abstract class ScreenBase implements GameScreen {
    protected BaseActivity game;
    protected SCREEN_TYPE type;
    public ScreenBase(BaseActivity game, SCREEN_TYPE type) {
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
    public static boolean inBounds(Input.TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    public static boolean inBounds(Input.TouchEvent event, Rect rect) {
        if(event.x > rect.left && event.x < rect.right
                && event.y > rect.top && event.y < rect.bottom) {
            return true;
        }else {
            return false;
        }
    }


    public void LogInfo(String str) {
        if(GameStatic.isLogInfo) {
            str += " [ " + GameUtils.getThreadSignature() + "]";
            Log.d(this.getScreenName(), str);
        }
    }
}

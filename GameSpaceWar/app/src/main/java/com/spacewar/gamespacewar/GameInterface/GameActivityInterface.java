package com.spacewar.gamespacewar.GameInterface;

import android.graphics.Canvas;

/**
 * Created by acer on 09/01/2018.
 */

public interface GameActivityInterface {
    public enum ACTIVITY_TYPE {
        NONE_ACTIVITY,
        LOADING_ACTIVITY,
        MENU_ACTIVITY
    }

    public int getWidth();
    public int getHeigh();
    public void Draw(Canvas canvas);
    public void Update(float deltaTime);

    public ACTIVITY_TYPE getTypeId();
    public String getTypeName();

    public int goToActivity(ACTIVITY_TYPE type);
    public void goToScreen(GameScreen.SCREEN_TYPE type);
}

package com.spacewar.gamespacewar;

import android.os.Bundle;

import com.spacewar.gamespacewar.GameBase.BaseActivity;
import com.spacewar.gamespacewar.GameInterface.GameScreen;

public class GameActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goToScreen(GameScreen.SCREEN_TYPE.SCEEN_MENU);
    }

    @Override
    public void onBackPressed() {
        GameScreen.SCREEN_TYPE sType = this.getCurrentScreen().getScreenID();
        if(sType == GameScreen.SCREEN_TYPE.SCEEN_MENU ||
                sType == GameScreen.SCREEN_TYPE.SCREEN_LOADING) {
            super.onBackPressed();
        }else {
            goToScreen(GameScreen.SCREEN_TYPE.SCEEN_MENU);
        }

    }


}

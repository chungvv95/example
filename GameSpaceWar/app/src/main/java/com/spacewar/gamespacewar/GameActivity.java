package com.spacewar.gamespacewar;

import android.os.Bundle;

import com.spacewar.gamespacewar.GameBase.BaseActivity;
import com.spacewar.gamespacewar.GameInterface.GameScreen;

public class GameActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goToScreen(GameScreen.SCREEN_TYPE.SCREEN_LOADING);
    }

}

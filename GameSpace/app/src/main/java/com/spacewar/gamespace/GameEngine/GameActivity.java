package com.spacewar.gamespace.GameEngine;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by acer on 27/12/2017.
 */

public class GameActivity extends Activity {
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Loại bỏ tiêu đề.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        gameView = new GameView(this, size.x, size.y);
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}

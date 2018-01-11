package com.spacewar.gamespacewar.Screens.HighScoreScreen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.spacewar.gamespacewar.GameBase.BaseActivity;
import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.Screens.ScreenBase;
import com.spacewar.gamespacewar.Stores.GameBitmap;

/**
 * Created by DELL on 1/10/2018.
 */

public class HighScoreScreen extends ScreenBase{
    private static final String highScoreFolder = "highScore";
    private Bitmap bmHighScorebg;

    private Rect rect = new Rect();
    private Paint paint = new Paint();


    public HighScoreScreen(BaseActivity game, SCREEN_TYPE type) {
        super(game, type);

        GameBitmap gb = GameBitmap.getInstance();
        gb.addImgFormFolder(highScoreFolder);
        bmHighScorebg = gb.getBitmap(highScoreFolder +"/highscore_bg.png");
    }

    @Override
    public void Draw(Canvas canvas) {
        rect.set(0,0,getWidth(),getHeigh());
        canvas.drawBitmap(bmHighScorebg,null, rect,null);

    }

    @Override
    public void Update(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

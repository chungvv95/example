package com.spacewar.gamespacewar.Screens.LoadingScreen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.spacewar.gamespacewar.GameBase.BaseActivity;
import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.Screens.ScreenBase;
import com.spacewar.gamespacewar.Stores.GameBitmap;
import com.spacewar.gamespacewar.Stores.GameStatic;
import com.spacewar.gamespacewar.Stores.Settings;

/**
 * Created by acer on 09/01/2018.
 */

public class LoadingScreen extends ScreenBase {
    private Bitmap bmLoadingBg;
    private Bitmap bmProgressBg;
    private Bitmap bmProgressPoint;

    private static final String loadingFolder = "loadingScreen";

    Paint paint = new Paint();
    private int X = 0;
    private int Y = 0;
    private int R = 200;
    Rect rect = new Rect();
    Rect recProgressBarBG = new Rect();
    private int proLenght = 0;
    private  boolean initMenuScreen = false;
    private  boolean initOptionScreen = false;
    private  boolean initPlayGameScreen = false;
    private  boolean initHighScoreScreen = false;
    private  final float timeMin = 100.0f;
    private  float timeTick = 0;
    private  float progressing = 0;


    public LoadingScreen(BaseActivity game, SCREEN_TYPE type) {
        super(game, type);
        GameBitmap gb = GameBitmap.getInstance();
        gb.addImgFormFolder(loadingFolder);
        bmLoadingBg = gb.getBitmap(loadingFolder + "/loading_bg.png");
        bmProgressBg = gb.getBitmap(loadingFolder + "/loading_ps.png");
        bmProgressPoint = gb.getBitmap(loadingFolder + "/loading_point.png");
    }

    @Override
    public void Draw(Canvas canvas) {
        GameBitmap gb = GameBitmap.getInstance();
        paint.setColor(Color.BLUE);
        rect.set(0,0,this.getWidth(), this.getHeigh());
        canvas.drawBitmap(bmLoadingBg, null, rect,null);

        recProgressBarBG.set(getWidth() / 4 , getHeigh() * 4 / 5, getWidth() / 4  + proLenght, getHeigh() * 4 / 5 + bmProgressBg.getHeight());
        canvas.drawBitmap(bmProgressBg, null, recProgressBarBG, null);

        canvas.drawBitmap(bmProgressPoint, recProgressBarBG.right - (bmProgressPoint.getWidth() /4), recProgressBarBG.top, null);
    }

    @Override
    public void Update(float deltaTime) {
        Resources rs = GameStatic.context.getResources();
        timeTick += deltaTime;
        int max = getWidth() / 2;
        if(initMenuScreen == false) {
            initMenuScreen = true;
            // init menu game
            addMenuFolder();
            proLenght += max / 8;

        }else if (initHighScoreScreen == false) {
            initHighScoreScreen = true;
            proLenght += max / 8;

        } else if(initOptionScreen == false) {
            initOptionScreen = true;
            proLenght += max / 8;

        } else if(initPlayGameScreen == false) {
            initPlayGameScreen = true;
            proLenght += max / 8;
            Settings.load(game.getFileIO());
        }

        if(initPlayGameScreen &&
                initOptionScreen &&
                initHighScoreScreen &&
                initMenuScreen )
        {
            progressing += deltaTime;
            if(progressing > 0.3){
                progressing = 0;
                proLenght += 10;
                if(proLenght >= getWidth() / 2 )
                    proLenght = getWidth() / 2;
            }
        }
        if(proLenght == getWidth() / 2) {
            this.game.goToScreen(SCREEN_TYPE.SCEEN_MENU);
        }
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

    private void addMenuFolder(){
        GameBitmap.getInstance().addImgFormFolder("menu");
    }

}

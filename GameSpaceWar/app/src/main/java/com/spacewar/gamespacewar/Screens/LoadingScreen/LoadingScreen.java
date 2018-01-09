package com.spacewar.gamespacewar.Screens.LoadingScreen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.Screens.ScreenBase;
import com.spacewar.gamespacewar.Stores.GameBitmap;
import com.spacewar.gamespacewar.Stores.GameStatic;

/**
 * Created by acer on 09/01/2018.
 */

public class LoadingScreen extends ScreenBase {
    Paint paint = new Paint();
    private int X = 0;
    private int Y = 0;
    private int R = 200;
    Rect rect = new Rect();
    private  boolean initMenuScreen = false;
    private  boolean initOptionScreen = false;
    private  boolean initPlayGameScreen = false;
    private  boolean initHighScoreScreen = false;
    private  final float timeMin = 2.0f;
    private  float timeTick = 0;
    public LoadingScreen(GameActivityInterface game, SCREEN_TYPE type) {
        super(game, type);
        GameBitmap.getInstance().addImgFormFolder("loadingScreen");
    }

    @Override
    public void Draw(Canvas canvas) {
        GameBitmap gb = GameBitmap.getInstance();
        paint.setColor(Color.BLUE);

        Bitmap bm = gb.getBitmap("loadingScreen/menu_bg.png");
//            canvas.drawBitmap(bm, null, new Rect(0,0,GameStatic.ScreenSize.x, GameStatic.ScreenSize.y),null);
        canvas.drawRect(new Rect(this.X,this.Y,X + 10,Y +10), paint);
    }

    @Override
    public void Update(float deltaTime) {
        Resources rs = GameStatic.context.getResources();
        timeTick += deltaTime;
        X = (int) (this.R * Math.sin(timeTick)) + 300;
        Y = (int) (this.R * Math.cos(timeTick)) + 100;
        if(initMenuScreen == false) {
            initMenuScreen = true;
            // init menu game
            addMenuFolder();

        }else if (initHighScoreScreen == false) {
            initHighScoreScreen = true;

        } else if(initOptionScreen == false) {
            initOptionScreen = true;

        } else if(initPlayGameScreen == false) {
            initPlayGameScreen = true;
        }

        if(initPlayGameScreen &&
                initOptionScreen &&
                initHighScoreScreen &&
                initMenuScreen &&
                timeTick > timeMin)
        {
            game.goToScreen(SCREEN_TYPE.SCEEN_MENU);
        }
    }
    private void addMenuFolder(){
        GameBitmap.getInstance().addImgFormFolder("menu");
    }

}

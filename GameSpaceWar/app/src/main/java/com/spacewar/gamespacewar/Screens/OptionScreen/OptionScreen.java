package com.spacewar.gamespacewar.Screens.OptionScreen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.spacewar.gamespacewar.GameBase.BaseActivity;
import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.GameInterface.Input;
import com.spacewar.gamespacewar.Screens.ScreenBase;
import com.spacewar.gamespacewar.Stores.GameBitmap;
import com.spacewar.gamespacewar.Stores.GameStatic;

import java.util.List;

/**
 * Created by DELL on 1/10/2018.
 */

public class OptionScreen extends ScreenBase {

    private static final String optionFolder = "optionScreen";

    private Bitmap bmOptionBg;
    private Bitmap bmround1;
    private Bitmap bmround2;
    private Bitmap bmround3;
    private Bitmap bmsprite;

    private Rect rect = new Rect();
    private Paint paint = new Paint();
    public OptionScreen(BaseActivity game, SCREEN_TYPE type) {
        super(game, type);
        GameBitmap gb = GameBitmap.getInstance();
        gb.addImgFormFolder(optionFolder);
        bmOptionBg = gb.getBitmap(optionFolder + "/option_bg.png");
        bmround1   = gb.getBitmap(optionFolder + "/vongtron1.png");
        bmround2   = gb.getBitmap(optionFolder + "/vongtron2.png");
        bmround3   = gb.getBitmap(optionFolder + "/vongtron3.png");
        bmsprite   = gb.getBitmap(optionFolder + "/sprite.png");
    }

    @Override
    public void Draw(Canvas canvas) {
        rect.set(0,0,getWidth(),getHeigh());
        canvas.drawBitmap(bmOptionBg,null, rect, null);
        canvas.drawBitmap(bmround1,255 *GameStatic.ratio_x_screen,170 *GameStatic.ratio_y_screen,null);
        canvas.drawBitmap(bmround2,231 *GameStatic.ratio_x_screen,145 *GameStatic.ratio_y_screen,null);
        canvas.drawBitmap(bmround3,207 *GameStatic.ratio_x_screen,120 *GameStatic.ratio_y_screen,null);
        canvas.drawBitmap(bmsprite,232 *GameStatic.ratio_x_screen,102 *GameStatic.ratio_y_screen,null);

    }

    @Override
    public void Update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);



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
}

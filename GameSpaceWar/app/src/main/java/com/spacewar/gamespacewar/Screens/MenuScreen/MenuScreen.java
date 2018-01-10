package com.spacewar.gamespacewar.Screens.MenuScreen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import com.spacewar.gamespacewar.GameBase.BaseActivity;
import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.GameInterface.Input;
import com.spacewar.gamespacewar.Screens.ScreenBase;
import com.spacewar.gamespacewar.Stores.GameBitmap;
import com.spacewar.gamespacewar.Stores.GameStatic;

import java.util.List;

/**
 * Created by acer on 09/01/2018.
 */

public class MenuScreen extends ScreenBase {

    private static final String menuFolder = "menu/";
    private Bitmap bmMenuBg;
    private Bitmap bmMenuPlay;
    private Bitmap bmMenuOption;
    private Bitmap bmMenuExit;
    private Bitmap bmMenuTopScore;
    private Rect rect = new Rect();
    private Paint paint = new Paint();

    private Point posBtnPlay = new Point();


    public MenuScreen(BaseActivity game, SCREEN_TYPE type) {
        super(game, type);
        bmMenuBg = this.getBitmap("menu_bg.png");
        bmMenuPlay = this.getBitmap("play.png");
        bmMenuExit = this.getBitmap("exit.png");
        bmMenuOption = this.getBitmap("option.png");
        bmMenuTopScore = this.getBitmap("top_scrore.png");
        float ratioX = GameStatic.ratio_x_screen;
        float ratioY = GameStatic.ratio_y_screen;
        posBtnPlay.set(460*(int)ratioX, 100*(int)ratioY);
    }
    private Bitmap getBitmap(String filename) {
        GameBitmap gb = GameBitmap.getInstance();
        return gb.getBitmap(menuFolder + filename);
    }

    @Override
    public void Draw(Canvas canvas) {
        rect.set(0,0,getWidth(),getHeigh());
        canvas.drawBitmap(bmMenuBg, null,rect , null);
        canvas.drawBitmap(bmMenuPlay, posBtnPlay.x, posBtnPlay.y, null);
        canvas.drawBitmap(bmMenuTopScore,460* GameStatic.ratio_x_screen,170* GameStatic.ratio_y_screen,null);
        canvas.drawBitmap(bmMenuOption, 460*GameStatic.ratio_x_screen, 240* GameStatic.ratio_y_screen,null);
        canvas.drawBitmap(bmMenuExit, 460 *GameStatic.ratio_x_screen, 310* GameStatic.ratio_y_screen, null);

    }

    @Override
    public void Update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP){
                if(inBounds(event, posBtnPlay.x,posBtnPlay.y, bmMenuPlay.getWidth(),bmMenuPlay.getHeight())) {
                    this.LogInfo("Play button clicked.");
                }
                this.LogInfo("Touch Up");
            }else if(event.type == Input.TouchEvent.TOUCH_DOWN){
                this.LogInfo("Touch Down");

            }

        }
    }
}

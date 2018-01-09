package com.spacewar.gamespacewar.GameInput;

import android.content.Context;
import android.os.Build;
import android.view.View;

import com.spacewar.gamespacewar.GameInterface.Input;
import com.spacewar.gamespacewar.GameInterface.TouchHandler;

import java.util.List;

/**
 * Created by acer on 07/01/2018.
 */

public class GameInput implements Input  {
//    AccelerometerHandler accelHandler;
    TouchHandler touchHandler;
    public GameInput(Context context, View view, float scaleX, float scaleY) {
//        if (Integer.parseInt(Build.VERSION.SDK) < 5)
//            touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
//        else {
//            touchHandler = new MultiTouchHandler(view, scaleX, scaleY);
//        }
        touchHandler = new SingleTouchHandler(view, scaleX, scaleY);
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return false;
    }

    public boolean isTouchDown(int pointer) {
        return touchHandler.isTouchDown(pointer);
    }
    public int getTouchX(int pointer) {
        return touchHandler.getTouchX(pointer);
    }
    public int getTouchY(int pointer) {
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public float getAccelX() {
        return 0;
    }

    @Override
    public float getAccelY() {
        return 0;
    }

    @Override
    public float getAccelZ() {
        return 0;
    }

    @Override
    public List<KeyEvent> getKeyEvents() {
        return null;
    }

    public List<Input.TouchEvent> getTouchEvents() {
        return touchHandler.getTouchEvents();
    }
}

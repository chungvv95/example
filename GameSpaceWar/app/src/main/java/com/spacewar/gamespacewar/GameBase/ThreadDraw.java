package com.spacewar.gamespacewar.GameBase;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;

import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.GameInterface.GameObject;
import com.spacewar.gamespacewar.Stores.GameStatic;
import com.spacewar.gamespacewar.Stores.GameUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 08/01/2018.
 */

public class ThreadDraw extends SurfaceView implements Runnable{
    private volatile boolean isRunning = false;
    private Thread thread = null;
    GameActivityInterface game;

    public ThreadDraw(GameActivityInterface game) {
        super(GameStatic.context);
        this.game = game;
    }

    @Override
    public void run() {
        Canvas canvas = null;
        long startTime = System.nanoTime();
        float deltaTime = 0;
        while (isRunning){
            try {
                deltaTime = (System.nanoTime() - startTime) / 1000000000.0f * 2;
                startTime = System.nanoTime();
                canvas = this.getHolder().lockCanvas();
                synchronized (canvas) {
                    this.Draw(canvas);
                }
            } catch (Exception e) {
            } finally {
                if (canvas != null) {
                    this.getHolder().unlockCanvasAndPost(canvas);
                }
            }

            if(deltaTime*1000 < 25)
            {
                deltaTime = 2.5f/100;
            }

            GameUtils.sleepForInMiliSec((int)deltaTime*1000);
        }
    }

    private void Draw(Canvas canvas) {
        this.game.Draw(canvas);
    }

    public void resume() {
        if(thread == null) {
            thread = new Thread(this);
            isRunning = true;
            thread.start();
            logClass(" ====> Resume ");
        }
    }

    public void onPause() {
        if(thread != null) {
            logClass(" ====> Stop ");
            try {
                isRunning = false;
                thread.join();
                this.thread = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    protected void logClass(String str) {
        if(GameStatic.isLog) {
            str += " [ " + GameUtils.getThreadSignature() + "]";
            Log.d(this.game.getTypeName(), str);
        }
    }
}

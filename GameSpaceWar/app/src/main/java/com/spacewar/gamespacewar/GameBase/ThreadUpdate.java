package com.spacewar.gamespacewar.GameBase;

import android.util.Log;

import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.GameInterface.GameObject;
import com.spacewar.gamespacewar.Stores.GameStatic;
import com.spacewar.gamespacewar.Stores.GameUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 08/01/2018.
 */

public class ThreadUpdate implements Runnable {
    private Thread thread = null;
    private volatile boolean isRunning = false;
    private GameActivityInterface game;

    public ThreadUpdate(GameActivityInterface game) {
        this.game = game;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        float deltaTime = 0;
        while (isRunning) {
            FPScounter.StartCounter();
            deltaTime = (System.nanoTime() - startTime) / 1000000000.0f * 2;
            startTime = System.nanoTime();
            this.Update(deltaTime);

            if(deltaTime*1000 < 25)
            {
                deltaTime = 2.5f/100;
            }
            GameUtils.sleepForInMiliSec((int)deltaTime*1000);
            FPScounter.StopAndPost();
        }
    }

    private void Update(float deltaTime) {
        this.game.Update(deltaTime);
    }

    public void resume() {
        if(thread == null) {
            logClass(" ====> Resume ");
            thread = new Thread(this);
            isRunning = true;
            thread.start();
        }
    }

    public void stop() {
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

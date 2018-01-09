package com.spacewar.gamespacewar.GameBase;

import com.spacewar.gamespacewar.GameInterface.SpriteControllerInterface;
import com.spacewar.gamespacewar.Stores.GameUtils;

/**
 * Created by acer on 09/01/2018.
 */

public class ThreadCollision implements Runnable {
    private volatile boolean isRunning = false;
    private Thread thread = null;
    SpriteControllerInterface controller;

    public ThreadCollision(SpriteControllerInterface controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        while (isRunning){
            this.controller.onCheckCollision();
            GameUtils.sleepForInMiliSec(25);
        }
    }

    public void resume(){
        if(thread == null){
            thread = new Thread(this);
            this.isRunning = true;
            thread.start();
        }
    }
    public void stop() {
        if(thread != null) {
            this.isRunning = false;
            try {
                this.thread.join();
                this.thread = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

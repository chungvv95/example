package com.spacewar.gamespace.GameEngine;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.spacewar.gamespace.GameEngine.FPSCounter.FPScounter;

/**
 * Created by acer on 13/01/2018.
 */

public class GameView extends SurfaceView implements Runnable{

    // boolean variable to track if the game is playing or not
    volatile boolean isRunning = false;

    //the game thread
    private Thread gameThread = null;

    private Canvas canvas = null;
    private GameManager gm;

    public int getDefaultWidth() {
        return defaultWidth;
    }

    public int getDefaultHeight() {
        return defaultHeight;
    }

    private int defaultWidth = 0;
    private int defaultHeight = 0;

    public GameView(Context context, int width, int height) {
        super(context);
        this.gm = new GameManager(this);
        this.defaultHeight = height;
        this.defaultWidth = width;

    }

    public void pause() {
        gm.pause();
        isRunning = false;
        try {
            gameThread.join();
            gameThread = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void resume() {
        gm.resume();
        isRunning = true;
        if(gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        float deltaTime = 0;
        while (isRunning) {
            FPScounter.StartCounter();
            try {
                FPScounter.StartCounter();
                deltaTime = (System.nanoTime() - startTime) / 1000000000.0f * 2;
                startTime = System.nanoTime();
                canvas = this.getHolder().lockCanvas();
                synchronized (canvas) {
                    gm.draw(canvas);
                    this.update(deltaTime);
                }
            } catch (Exception e) {
            } finally {
                if (canvas != null) {
                    this.getHolder().unlockCanvasAndPost(canvas);
                }
            }
            try {
                control(deltaTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FPScounter.StopAndPost();

        }
    }

    private void control(float deltaTime) throws InterruptedException {
        if(deltaTime*1000 < 25)
        {
            deltaTime = 2.5f/100;
        }
        Thread.sleep((long) deltaTime);
    }

    private void update(float deltaTime) {
        gm.update(deltaTime);
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        gm.onTouchEvent(motionEvent);
        return true;
    }
}

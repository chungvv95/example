package com.spacewar.gamespace.GameEngine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.spacewar.gamespace.GameEngine.GInterface.GObject;
import com.spacewar.gamespace.GameEngine.Screens.Screen;
import com.spacewar.gamespace.GameEngine.Screens.ScreenLevel1;
import com.spacewar.gamespace.GameEngine.Spriters.SpritePlayer;

/**
 * Created by acer on 13/01/2018.
 */

public class GameManager implements GObject {
    public enum GAME_STATE {
        GameReady,
        GameRunning,
        GamePause,
        GameOver
    }

    private Screen currentScreen = null;
    private GAME_STATE state = GAME_STATE.GameReady;
    private GameView gv;
    private SpritePlayer player;
    private Rect rect = new Rect();
    private Paint paint = new Paint();

    public GameManager(GameView gv) {
        this.gv = gv;
        player = new SpritePlayer(gv);
        rect.set(0,0,gv.getWidth(), gv.getHeight());
        currentScreen = new ScreenLevel1(this, player);
    }

    private void gameReady(float deltaTime){
        state = GAME_STATE.GameRunning;
    }


    public GameView getGameView(){
        return this.gv;
    }
    @Override
    public void update(float deltaTime) {
        if(state != GAME_STATE.GameRunning)
            return;
        player.update(deltaTime);
        this.currentScreen.update(deltaTime);



    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        player.draw(canvas);
        this.currentScreen.draw(canvas);
    }

    @Override
    public void pause() {
        state = GAME_STATE.GamePause;
        player.pause();
    }

    @Override
    public void resume() {
        state = GAME_STATE.GameReady;
        player.resume();
    }

    @Override
    public void dispose() {
        player.dispose();
    }


    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_UP: {
                if(state == GAME_STATE.GamePause ||
                        state == GAME_STATE.GameReady){
                    gameReady(0);
                }
                if(this.state == GAME_STATE.GameRunning){
                    player.goTo(motionEvent.getX(), motionEvent.getY());
                }

            }
                Log.d(this.getClass().getName(), "Action Up");
                break;
            case MotionEvent.ACTION_MOVE:
                if(this.state == GAME_STATE.GameRunning){
                    player.goTo(motionEvent.getX(), motionEvent.getY());
                }
                break;
            case MotionEvent.ACTION_DOWN:
                Log.d(this.getClass().getName(), "Action Down");
                break;
        }
        return true;
    }

    public Screen getCurrentScreen(){
        return currentScreen;
    }

    public void goToScreen(Screen screen) {
        if(screen == null)
            throw new IllegalArgumentException("Screen must not be null");
        if(currentScreen == null) {
            currentScreen.pause();
            currentScreen.dispose();
        }
        screen.resume();
        screen.update(0);
        currentScreen = screen;
    }


}

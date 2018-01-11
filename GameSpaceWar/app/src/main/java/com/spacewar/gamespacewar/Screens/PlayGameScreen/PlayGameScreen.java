package com.spacewar.gamespacewar.Screens.PlayGameScreen;

import android.graphics.Canvas;

import com.spacewar.gamespacewar.GameBase.BaseActivity;
import com.spacewar.gamespacewar.GameInterface.Input;
import com.spacewar.gamespacewar.Screens.ScreenBase;

import java.util.List;

/**
 * Created by acer on 10/01/2018.
 */

public class PlayGameScreen extends ScreenBase {
    private BackgroundScreen bg;
    private int pointChangedState = 0;

    public PlayGameScreen(BaseActivity game, SCREEN_TYPE type) {
        super(game, type);
        this.gameState = GameState.Ready;
        bg = new BackgroundScreen(this);
    }

    @Override
    public void Draw(Canvas canvas) {
        bg.Draw(canvas);
    }

    @Override
    public void Update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();
        if(this.gameState == GameState.Ready)
            updateReady(touchEvents);
        if(this.gameState == GameState.Running)
            updateRunning(touchEvents, deltaTime);
        if(this.gameState == GameState.Paused)
            updatePaused(touchEvents);
        if(this.gameState == GameState.GameOver)
            updateGameOver(touchEvents);


    }

    private void updateReady(List <Input.TouchEvent> touchEvents) {
        if(touchEvents.size() > 0){
            this.pointChangedState++;
            if(this.pointChangedState > 5)
                this.gameState = GameState.Running;
        }
    }

    private void updatePaused(List <Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if (event.type == Input.TouchEvent.TOUCH_UP) {
                if (event.x > 80 && event.x <= 240) {
                    if (event.y > 100 && event.y <= 148) {
//                        if(Settings.soundEnabled)
//                            Assets.click.play(1);
                        this.gameState = GameState.Running;
                        return;
                    }
                    if (event.y > 148 && event.y < 196) {
//                        if(Settings.soundEnabled)
//                            Assets.click.play(1);
                        this.game.goToScreen(SCREEN_TYPE.SCEEN_MENU);
                        return;
                    }
                }
            }
        }
    }

    private void updateGameOver(List <Input.TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP) {
                if(event.x >= 128 && event.x <= 192 &&
                        event.y >= 200 && event.y <= 264) {
//                    if(Settings.soundEnabled)
//                        Assets.click.play(1);
                    this.game.goToScreen(SCREEN_TYPE.SCEEN_MENU);
                    return;
                }
            }
        }
    }
    private void updateRunning(List <Input.TouchEvent> touchEvents, float deltaTime) {
        bg.Update(deltaTime);
        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            Input.TouchEvent event = touchEvents.get(i);
        }

    }

    @Override
    public void pause() {
        if(this.gameState == GameState.Running)
            this.gameState = GameState.Paused;
    }

    @Override
    public void resume() {
//        if(this.gameState != GameState.Running)
        this.pointChangedState = 0;
        this.gameState = GameState.Ready;
    }

    @Override
    public void dispose() {

    }
}

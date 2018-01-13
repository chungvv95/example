package com.spacewar.gamespace.GameEngine.Screens;
import android.graphics.Canvas;
import com.spacewar.gamespace.GameEngine.GameManager;

/**
 * Created by acer on 07/01/2018.
 */

public abstract class Screen {
    protected final GameManager game;
    public Screen(GameManager game) {
        this.game = game;
    }
    public abstract void update(float deltaTime);
    public abstract void draw(Canvas canvas);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
    public abstract void checkCollision();
}

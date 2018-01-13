package com.spacewar.gamespace.GameEngine.GInterface;

import android.graphics.Canvas;

/**
 * Created by acer on 13/01/2018.
 */

public interface GObject {
    public void update(float deltaTime);
    public void draw(Canvas canvas);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}

package com.spacewar.gamespace.GameEngine.Spriters;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

import com.spacewar.gamespace.GameEngine.GInterface.GObject;

    /**
 * Created by acer on 13/01/2018.
 */

public abstract class Sprite implements GObject {

    protected float x;
    protected float y;
    protected Rect rectBody = new Rect();
    protected Bitmap image = null;
    protected Point offset = new Point(0,0);
    protected float speed = 100f;


    public float getSpeed() {
        return speed;
    }

    @Override
    public void resume() {

    }

    @Override
    public void update(float deltaTime) {
        updateRectBody();
    }

    @Override
    public void dispose() {

    }

    public float getWidth(){
        return this.image.getWidth();
    }

    public float getHeight() {
        return this.image.getHeight();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Rect getRectBody() {
        return rectBody;
    }

    public void updateRectBody(){
        rectBody.top = (int)this.y + offset.y;
        rectBody.bottom = (int)this.y + this.image.getHeight() - offset.y;

        rectBody.left = (int)this.x + offset.x;
        rectBody.right = (int)this.x + this.image.getWidth() - offset.x;
    }

    public float distance(float x1, float y1, float x2, float y2) {
        return (float)Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
    public void onCollision(Sprite other) {

    }
}

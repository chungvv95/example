package com.spacewar.gamespace.GameEngine.Spriters.Bullets;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import com.spacewar.gamespace.GameEngine.GInterface.GObject;
import com.spacewar.gamespace.GameEngine.Spriters.Sprite;

import java.util.Random;

/**
 * Created by acer on 13/01/2018.
 */

public class Bullet implements GObject{
    Sprite player;
    private float x;
    private float y;
    private float speed = 60f;
    private Paint paint;

    public Rect getRectBody() {
        return rectBody;
    }

    private Rect rectBody = new Rect();
    public Bullet(Sprite player) {
        this.player = player;
        speed = speed + player.getSpeed();
        paint = new Paint();
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        paint.setColor(color);
        x = player.getX() + player.getHeight();
        y = player.getY() + player.getHeight() / 2;
    }

    private  void updateRectBody() {
        rectBody.set((int)x,(int)x+8,(int)y,(int)y+8);
    }

    @Override
    public void update(float deltaTime) {
        x += speed*deltaTime;
        updateRectBody();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(getX(), getY(), 5,paint);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getSpeed() {
        return speed;
    }

    public void onCollision(Sprite other) {
        this.x = 1000000000;
    }
}

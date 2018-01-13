package com.spacewar.gamespace.GameEngine.Spriters;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.spacewar.gamespace.GameEngine.GameView;
import com.spacewar.gamespace.GameEngine.Spriters.Bullets.Bullet;
import com.spacewar.gamespace.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 09/01/2018.
 */

public class SpritePlayer extends Sprite{


    private float Tx = 0;
    private float Ty = 0;
    private float deltaX = 0;
    private float deltaY = 0;

    public List<Bullet> getBullets() {
        return bullets;
    }

    private List<Bullet> bullets = new ArrayList<>();
    private float bulletDelay = 0;
    private GameView gv;
    public SpritePlayer(GameView gv) {
        this.gv = gv;
        this.image = BitmapFactory.decodeResource(this.gv.getResources(), R.drawable.blueships1);
    }



    @Override
    public void update(float deltaTime) {
        if(distance(Tx, Ty, this.x, this.y) > 5) {
            this.x += deltaX * deltaTime + 0.5*10 *deltaTime*deltaTime;
            this.y += deltaY * deltaTime + 0.5*10 *deltaTime*deltaTime;

            if(this.x + this.getWidth() > this.gv.getWidth()) {
                this.x = this.gv.getWidth() - this.getWidth();
            }
            if(this.y + this.getHeight() > this.gv.getHeight()){
                this.y = this.gv.getHeight() - this.getHeight();
            }
            if(this.x < 0){
                this.x = 0;
            }
            if (this.y < 0){
                this.y = 0;
            }

        }

        bulletDelay++;
        if(bulletDelay > 4.0) {
            bulletDelay = 0;
            bullets.add(new Bullet(this));
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update(deltaTime);
            if(bullets.get(i).getX() > this.gv.getWidth()){
                bullets.remove(i);
            }
        }

        super.update(deltaTime);
    }

    @Override
    public void draw(Canvas canvas) {
        for (Bullet bl : bullets){
            bl.draw(canvas);
        }


        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(getRectBody(), paint );
        canvas.drawBitmap(this.image, getX(), getY(), null);
    }

    @Override
    public void pause() {

    }

    public void goTo(float x, float y) {
        this.Tx = x;
        this.Ty = y;
        float distance_p = distance(Tx, Ty, this.x, this.y);
        this.deltaX = ((x - this.x) * speed / distance_p) ;
        this.deltaY = ((y - this.y) * speed / distance_p);
    }
}

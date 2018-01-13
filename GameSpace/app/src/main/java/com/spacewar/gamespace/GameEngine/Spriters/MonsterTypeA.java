package com.spacewar.gamespace.GameEngine.Spriters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;

import com.spacewar.gamespace.GameEngine.Explosions.Explosion;
import com.spacewar.gamespace.GameEngine.GameView;
import com.spacewar.gamespace.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by acer on 12/01/2018.
 */

public class MonsterTypeA extends Sprite {

    protected float speed = 100f;
    private int movingVectorX = -10;
    private int movingVectorY = 10;
    private Random generatorRan = new Random();
    Bitmap bm;
    GameView gv;
    private final List<Explosion> explosionList = new ArrayList<>();
    public MonsterTypeA(GameView game){
        this.gv = game;
        this.image = BitmapFactory.decodeResource(game.getResources(), R.drawable.monster_type_a);

        x = 2000;
        y = 300;
        movingVectorY = -generatorRan.nextInt(20);
        speed = 100 + generatorRan.nextInt(50);
        bm = BitmapFactory.decodeResource(this.gv.getResources(), R.drawable.boom);
    }

    public void resetMonter() {
        speed = speed + generatorRan.nextInt(50);
        if(speed > 300)
            speed = 120;
        x = this.gv.getWidth();
        int screenHeight = this.gv.getHeight();
        if(screenHeight <= 0)
            screenHeight = 1000;

        y = generatorRan.nextInt(screenHeight) - image.getHeight();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        for (int i = 0; i < explosionList.size(); i++){
            explosionList.get(i).update(deltaTime);
            if(explosionList.get(i).isFinish()){
                explosionList.remove(i);
            }
        }

        float distance = speed * deltaTime ;
        double movingVectorLength = Math.sqrt(Math.abs(movingVectorX* movingVectorX + movingVectorY*movingVectorY));

        this.x = x +  (int)(distance* movingVectorX / movingVectorLength);
        this.y = y +  (int)(distance* movingVectorY / movingVectorLength);

        if(this.y < 0 )  {
            this.y = 0;
            this.movingVectorY = - this.movingVectorY;
        } else if(this.y > this.gv.getHeight() - this.getHeight())  {
            this.y= this.gv.getHeight() - this.getHeight();
            this.movingVectorY = - this.movingVectorY ;
        }

        if (this.x < ( - image.getWidth())) {
            resetMonter();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawRect(getRectBody(), paint );
        canvas.drawBitmap(this.image, getX(), getY(), null);
        for(Explosion explosion: this.explosionList)  {
            explosion.draw(canvas);
        }

    }

    @Override
    public void pause() {

    }
    @Override
    public void onCollision(Sprite other) {

        Explosion e = new Explosion(this.gv,bm, 5,5,(int) this.x, (int)this.y);
        explosionList.add(e);
        resetMonter();
        Log.d(this.getClass().getName(), "add:" + explosionList.size());
    }

}

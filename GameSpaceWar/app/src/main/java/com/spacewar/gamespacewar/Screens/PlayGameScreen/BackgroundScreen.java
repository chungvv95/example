package com.spacewar.gamespacewar.Screens.PlayGameScreen;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.spacewar.gamespacewar.GameInterface.GameObject;
import com.spacewar.gamespacewar.GameManager.Sprites.Star;
import com.spacewar.gamespacewar.Screens.ScreenBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 11/01/2018.
 */

public class BackgroundScreen implements GameObject {
    private List<Star> stars = new ArrayList<>();
    private ScreenBase screen;
    private Paint paint;

    public BackgroundScreen(ScreenBase screen) {
        this.screen = screen;
        paint = new Paint();
        int starCount = 100;
        for (int i = 0; i < starCount; i++) {
            Star s  = new Star(this.screen.getWidth(), this.screen.getHeigh());
            stars.add(s);
        }

    }

    @Override
    public void Update(float deltaTime) {
        for (Star s : stars) {
            s.update(5, deltaTime);
        }
    }

    @Override
    public void Draw(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.drawRect(new Rect(0,0,this.screen.getWidth(), this.screen.getHeigh()), paint);

        paint.setColor(Color.WHITE);
        for (Star s : stars) {
            paint.setStrokeWidth(s.getStarWidth());
            canvas.drawPoint(s.getX(), s.getY(), paint);
        }
    }
}

package com.spacewar.gamespacewar.GameManager.Sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.spacewar.gamespacewar.GameBase.Sprite;

/**
 * Created by acer on 09/01/2018.
 */

public class SpriteMonster extends Sprite {
    public SpriteMonster(Bitmap image, int rowCount, int colCount, int x, int y) {
        super(image, rowCount, colCount, x, y);
    }

    @Override
    public void Update(float deltaTime) {

    }

    @Override
    public void Draw(Canvas canvas) {

    }

    @Override
    public void onCollision(Sprite other) {

    }
}

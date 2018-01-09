package com.spacewar.gamespacewar.GameBase;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by acer on 09/01/2018.
 */

public abstract class SpriteAni extends Sprite {
    protected int rowIndex = 0 ;
    protected int colIndex = -1 ;

    protected boolean finish= false;
    protected boolean isLoop = false;

    public SpriteAni(Bitmap image, int rowCount, int colCount, int x, int y) {
        super(image, rowCount, colCount, x, y);
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    @Override
    public void Update(float deltaTime) {
        this.colIndex++;
        if(this.colIndex >= this.colCount)  {
            this.colIndex =0;
            this.rowIndex++;

            if(this.rowIndex >= this.rowCount)  {
                this.finish = !isLoop;
                rowIndex = 0;
            }
        }
    }

    @Override
    public void Draw(Canvas canvas) {
        if(!finish)  {
            Bitmap bitmap= this.createSubImageAt(rowIndex,colIndex);
            canvas.drawBitmap(bitmap, this.x, this.y,null);
        }
    }

    @Override
    public void onCollision(Sprite other) {

    }
}

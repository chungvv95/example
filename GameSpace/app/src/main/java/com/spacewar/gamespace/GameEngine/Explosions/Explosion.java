package com.spacewar.gamespace.GameEngine.Explosions;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.spacewar.gamespace.GameEngine.GameView;
import com.spacewar.gamespace.GameEngine.Spriters.GameObject;

/**
 * Created by acer on 13/01/2018.
 */

public class Explosion extends GameObject {

    private int rowIndex = 0 ;
    private int colIndex = 0 ;

    private boolean finish= false;
    private GameView gv;

    public Explosion(GameView gv,Bitmap image, int rowCount, int colCount, int x, int y) {
        super(image, 5, 5, x, y);
        this.gv = gv;
    }

    public void update(float detaTime)  {
        this.colIndex++;

        if(this.colIndex >= this.colCount)  {
            this.colIndex = 0;
            this.rowIndex++;

            if(this.rowIndex>= this.rowCount)  {
                this.finish= true;
            }
        }
    }

    public void draw(Canvas canvas)  {
        if(!finish)  {
            Bitmap bitmap= this.createSubImageAt(rowIndex,colIndex);
            canvas.drawBitmap(bitmap, this.x, this.y,null);
        }
    }

    public boolean isFinish() {
        return finish;
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
}

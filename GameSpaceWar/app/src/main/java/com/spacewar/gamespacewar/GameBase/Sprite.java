package com.spacewar.gamespacewar.GameBase;

import android.graphics.Bitmap;
import android.graphics.Rect;

import com.spacewar.gamespacewar.GameInterface.GameObject;

/**
 * Created by acer on 09/01/2018.
 */

public abstract class Sprite implements GameObject {
    public enum  SPRITE_STATE {
        SPRITE_STATE_ALIVE,
        SPRITE_STATE_DIE
    }
    public enum SPRITE_DIRECTION {
        SPRITE_DIRECTION_UP,
        SPRITE_DIRECTION_DOWN,
        SPRITE_DIRECTION_LEFT,
        SPRITE_DIRECTION_RIGHT,
    }

    public enum SPRITE_TYPE {
        SPRITE_TYPE_NONE,
    }
    protected Bitmap image;

    protected final int rowCount;
    protected final int colCount;

    protected final int WIDTH;
    protected final int HEIGHT;

    protected final int width;


    protected final int height;
    protected int x;
    protected int y;


    protected Rect recBody;

    public Rect getRecBody() {
        return recBody;
    }
    public Sprite(Bitmap image, int rowCount, int colCount, int x, int y)  {

        this.image = image;
        this.rowCount= rowCount;
        this.colCount= colCount;

        this.x= x;
        this.y= y;

        this.WIDTH = image.getWidth();
        this.HEIGHT = image.getHeight();

        this.width = this.WIDTH/ colCount;
        this.height= this.HEIGHT/ rowCount;
        this.recBody = new Rect(this.x, this.y, this.x + getWidth(), this.y + getHeight());
    }



    public SPRITE_TYPE getType() {
        return type;
    }

    protected SPRITE_TYPE type;

    public int getX()  {
        return this.x;
    }

    public int getY()  {
        return this.y;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public abstract void onCollision(Sprite other);



    protected Bitmap createSubImageAt(int row, int col)  {
        // createBitmap(bitmap, x, y, width, height).
        Bitmap subImage = Bitmap.createBitmap(image, col* width, row* height ,width,height);
        return subImage;
    }
}

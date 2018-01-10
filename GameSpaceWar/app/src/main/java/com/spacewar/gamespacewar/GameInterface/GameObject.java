package com.spacewar.gamespacewar.GameInterface;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by acer on 08/01/2018.
 */

public interface GameObject {
    public void Update(float deltaTime);
    public void Draw(Canvas canvas);

}

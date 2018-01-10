package com.spacewar.gamespacewar.GameManager;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.spacewar.gamespacewar.GameBase.Sprite;
import com.spacewar.gamespacewar.GameBase.ThreadCollision;
import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.GameInterface.SpriteControllerInterface;
import com.spacewar.gamespacewar.GameManager.Sprites.SpritePlayer;
import com.spacewar.gamespacewar.GameManager.Sprites.SpritePool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 09/01/2018.
 */

public class SpriteController implements SpriteControllerInterface {
    GameActivityInterface game;
    SpritePlayer player = null;
    ThreadCollision threadCollision;

    List<Sprite> listObj = new ArrayList<>();
    SpritePool spritePool;

    public SpriteController(GameActivityInterface game) {
        this.game = game;
        spritePool = new SpritePool(this);
        threadCollision = new ThreadCollision(this);
//        player = new SpritePlayer()


    }

    public void resume() {
        threadCollision.resume();
    }
    public void onPause() {
        threadCollision.stop();
    }

    public void Update(float deltaTime) {
        spritePool.Update(deltaTime);
    }


    public void Draw(Canvas canvas) {
        spritePool.Draw(canvas);
    }

    @Override
    public void onCheckCollision() {
        for(int i = 0; i < listObj.size(); i++) {
            Sprite sp = listObj.get(i);
            if(Rect.intersects(sp.getRecBody(), player.getRecBody())) {
                sp.onCollision(player);
                player.onCollision(sp);
            }
        }
    }
}

package com.spacewar.gamespacewar.GameManager;

import android.graphics.Canvas;

import com.spacewar.gamespacewar.GameBase.ThreadCollision;
import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.GameInterface.GameObject;
import com.spacewar.gamespacewar.GameInterface.SpriteControllerInterface;
import com.spacewar.gamespacewar.GameManager.Sprites.SpritePlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 09/01/2018.
 */

public class SpriteController implements GameObject, SpriteControllerInterface {
    GameActivityInterface game;
    SpritePlayer player = null;
    ThreadCollision threadCollision;
    List<GameObject> listObj = new ArrayList<>();

    public SpriteController(GameActivityInterface game) {
        this.game = game;
        threadCollision = new ThreadCollision(this);
//        player = new SpritePlayer()

        threadCollision.resume();
    }

    @Override
    public void Update(float deltaTime) {
        for (GameObject obj : listObj)
            obj.Update(deltaTime);
    }

    @Override
    public void Draw(Canvas canvas) {
        for (GameObject obj : listObj)
            obj.Draw(canvas);
    }

    @Override
    public void onCheckCollision() {

    }
}

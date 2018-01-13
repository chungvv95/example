package com.spacewar.gamespace.GameEngine.Screens;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.spacewar.gamespace.GameEngine.GameManager;
import com.spacewar.gamespace.GameEngine.GameUtils;
import com.spacewar.gamespace.GameEngine.Spriters.Bullets.Bullet;
import com.spacewar.gamespace.GameEngine.Spriters.MonsterTypeA;
import com.spacewar.gamespace.GameEngine.Spriters.SpritePlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 13/01/2018.
 */

public class ScreenLevel1 extends Screen {
    List<MonsterTypeA> listMonsterA = new ArrayList<>();
    SpritePlayer player;
    public ScreenLevel1(GameManager game, SpritePlayer player) {
        super(game);
        this.player = player;
        for(int i = 0; i < 10 ; i++){
            MonsterTypeA a = new MonsterTypeA(game.getGameView());
            listMonsterA.add(a);
        }
    }

    @Override
    public void update(float deltaTime) {
        for (int i = 0; i < listMonsterA.size(); i++){
            listMonsterA.get(i).update(deltaTime);
            if(Rect.intersects(player.getRectBody(), listMonsterA.get(i).getRectBody())){
//                player.onCollision(listMonsterA.get(i));
                listMonsterA.get(i).onCollision(player);
            }
            List<Bullet> list = player.getBullets();
            for(int idx = 0; idx < list.size(); i++) {
                if(Rect.intersects(list.get(idx).getRectBody(), listMonsterA.get(i).getRectBody())){
//                    list.get(i).onCollision(listMonsterA.get(i));
                    listMonsterA.get(i).onCollision(player);
                }
            }
        }
        Log.d("ScreenLevel1", "update # " + GameUtils.getThreadId());
    }

    @Override
    public void draw(Canvas canvas) {
        for (int i = 0; i < listMonsterA.size(); i++){
            listMonsterA.get(i).draw(canvas);
        }
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

    @Override
    public void checkCollision() {

    }
}

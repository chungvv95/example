package com.spacewar.gamespacewar.GameManager.Sprites;

import android.graphics.Canvas;

import com.spacewar.gamespacewar.GameBase.Sprite;
import com.spacewar.gamespacewar.GameInterface.GameObject;
import com.spacewar.gamespacewar.GameManager.SpriteController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by acer on 10/01/2018.
 */

public class SpritePool implements GameObject {

    Map<Sprite.SPRITE_TYPE, List<Sprite>> mapObj = new HashMap<>();
    SpriteController parent;

    public SpritePool(SpriteController parent) {
        this.parent = parent;
    }

    public void addListSprite(Sprite.SPRITE_TYPE type, List<Sprite> list) {
        mapObj.put(type, list);
    }

    public void removeListSprite(Sprite.SPRITE_TYPE type, List<Sprite> list) {
        mapObj.remove(type);
    }

    public boolean addSpriteInMap(Sprite.SPRITE_TYPE type, Sprite sprite) {
        List<Sprite> list = mapObj.get(type);
        if(list != null){
            list.add(sprite);
            return true;
        }
        return false;
    }

    public List<Sprite> getListSprite(Sprite.SPRITE_TYPE type) {
        return mapObj.get(type);
    }

    public int getCount(Sprite.SPRITE_TYPE type) {
        List<Sprite> list = mapObj.get(type);
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public boolean removeSpriteInMap(Sprite.SPRITE_TYPE type, Sprite sprite) {
        List<Sprite> list = mapObj.get(type);
        if(list != null){
            list.remove(sprite);
            return true;
        }
        return false;
    }

    @Override
    public void Update(float deltaTime) {
        for(Map.Entry<Sprite.SPRITE_TYPE, List<Sprite>> lObj : mapObj.entrySet()) {
            for (GameObject obj : lObj.getValue()) {
                obj.Update(deltaTime);
            }
        }
    }

    @Override
    public void Draw(Canvas canvas) {
        for(Map.Entry<Sprite.SPRITE_TYPE, List<Sprite>> lObj : mapObj.entrySet()) {
            for (GameObject obj : lObj.getValue()) {
                obj.Draw(canvas);
            }
        }
    }
}

package com.spacewar.gamespacewar.Stores;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Point;

import com.spacewar.gamespacewar.GameInterface.Sound;

import java.io.IOException;

/**
 * Created by acer on 08/01/2018.
 */

public class GameStatic {
    public static final boolean isLog = false;
    public static final boolean isLogInfo = true;
    public static Context context;
    public static AssetManager assets;
    public static float ratio_x_screen;
    public static float ratio_y_screen;
    public static Point ScreenSize = new Point();

    public static Sound click;
    public static Sound eat;
    public static Sound bitten;

    public static String[] listAssetFiles(String path) {
        String [] list;
        try {
            list = assets.list(path);
            if(list.length > 0)
                return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

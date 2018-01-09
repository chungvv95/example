package com.spacewar.gamespacewar.Stores;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import junit.framework.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by acer on 08/01/2018.
 */

public class GameBitmap {
    private static GameBitmap instance = null;
    private Map<String, Bitmap> mapBitmap = new HashMap<>();
    public static enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }
    public static GameBitmap getInstance(){
        if (instance == null)
            instance = new GameBitmap();
        return instance;
    }

    private Bitmap getBitmap(String fileName, PixmapFormat format) {
        Bitmap.Config config = null;
        if (format == PixmapFormat.RGB565)
            config = Bitmap.Config.RGB_565;
        else if (format == PixmapFormat.ARGB4444)
            config = Bitmap.Config.ARGB_4444;
        else
            config = Bitmap.Config.ARGB_8888;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;
        InputStream in = null;
        Bitmap bitmap = null;
        AssetManager assets = GameStatic.assets;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                        + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        int newWidth = (int)(bitmap.getWidth() * GameStatic.ratio_x_screen);
        int newHeight = (int)(bitmap.getHeight() * GameStatic.ratio_y_screen);
        Bitmap resizeBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
        return resizeBitmap;
    }

    public void createBitmap(String fileName, PixmapFormat format) {
        if(!mapBitmap.containsKey(fileName)) {
            Bitmap bm = getBitmap(fileName, format);
            mapBitmap.put(fileName, bm);
        }
    }

    public Bitmap getBitmap(String fileName) {
        if(mapBitmap.containsKey(fileName))
            return mapBitmap.get(fileName);
        else {
            createBitmap(fileName, PixmapFormat.ARGB8888);
            return mapBitmap.get(fileName);
        }
    }

    public void addImgFormFolder(String folder) {
        String[] myfilesfolderlist = GameStatic.listAssetFiles(folder);
        for(String str : myfilesfolderlist) {
            if(str.contains(".")){
                str = folder + "/"+ str;
                createBitmap(str, PixmapFormat.ARGB4444);
            }
        }
    }
}

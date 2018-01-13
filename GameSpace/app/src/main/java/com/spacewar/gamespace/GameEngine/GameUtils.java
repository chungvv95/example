package com.spacewar.gamespace.GameEngine;

import android.util.Log;

/**
 * Created by acer on 08/01/2018.
 */

public class GameUtils {
    public static long getThreadId() {
        Thread t = Thread.currentThread();
        return t.getId();
    }

    public static String getThreadSignature() {
        Thread t = Thread.currentThread();
        long l = t.getId();
        long p = t.getPriority();
        String name = t.getName();
        String gname = t.getThreadGroup().getName();
        return (name + " : (Id) " + l + " :(priority)" + p + " :(group)" + gname);
    }

    public static void logThreadSignature() {
        Log.d("ThreadUtils", getThreadSignature());
    }

    public static void sleepForInMiliSec(int secs) {
        try {
            Thread.sleep(secs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

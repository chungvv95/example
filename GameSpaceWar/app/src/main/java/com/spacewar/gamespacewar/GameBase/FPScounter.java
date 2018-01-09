package com.spacewar.gamespacewar.GameBase;

import android.util.Log;

/**
 * Created by acer on 06/01/2018.
 */

final public class FPScounter {
    private static int startTime;
    private static int endTime;
    private static int frameTimes = 0;
    private static short frames = 0;
    public static long deltaTime = 0;

    /** Start counting the fps**/
    public final static void StartCounter()
    {
        //get the current time
        startTime = (int) System.currentTimeMillis();
    }

    /**stop counting the fps and display it at the console*/
    public final static void StopAndPost()
    {
        //get the current time
        endTime = (int) System.currentTimeMillis();
        deltaTime = endTime - startTime;
        //the difference between start and end times
        frameTimes = frameTimes + (int)deltaTime;
        //count one frame
        ++frames;
        //if the difference is greater than 1 second (or 1000ms) post the results
        if(frameTimes >= 1000)
        {
            //post results at the console
            Log.i("FPS", Long.toString(frames));
            //reset time differences and number of counted frames
            frames = 0;
            frameTimes = 0;
        }
    }
}

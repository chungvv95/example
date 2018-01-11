package com.spacewar.gamespacewar.GameBase;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.spacewar.gamespacewar.GameInput.GameInput;
import com.spacewar.gamespacewar.GameInterface.Audio;
import com.spacewar.gamespacewar.GameInterface.FileIO;
import com.spacewar.gamespacewar.GameInterface.GameActivityInterface;
import com.spacewar.gamespacewar.GameInterface.GameObject;
import com.spacewar.gamespacewar.GameInterface.GameScreen;
import com.spacewar.gamespacewar.GameInterface.Input;
import com.spacewar.gamespacewar.R;
import com.spacewar.gamespacewar.Screens.HighScoreScreen.HighScoreScreen;
import com.spacewar.gamespacewar.Screens.LoadingScreen.LoadingScreen;
import com.spacewar.gamespacewar.Screens.MenuScreen.MenuScreen;
import com.spacewar.gamespacewar.Screens.OptionScreen.OptionScreen;
import com.spacewar.gamespacewar.Screens.PlayGameScreen.PlayGameScreen;
import com.spacewar.gamespacewar.Screens.ScreenBase;
import com.spacewar.gamespacewar.Stores.GameStatic;
import com.spacewar.gamespacewar.Stores.GameUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.spacewar.gamespacewar.GameInterface.GameActivityInterface.ACTIVITY_TYPE.NONE_ACTIVITY;

public abstract class BaseActivity extends Activity implements GameActivityInterface {
    protected ThreadUpdate threadUpdate;
    protected ThreadDraw threadDraw;
    protected List<GameObject> listObj = new ArrayList<>();
    protected Input input;
    protected FileIO fileIO;
    protected Audio audio;
    protected int screenWidth;
    protected int screenHeigh;
    protected ACTIVITY_TYPE type = NONE_ACTIVITY;
    PowerManager.WakeLock wakeLock;
    protected ScreenBase currentScreen = null;
    protected Map<GameScreen.SCREEN_TYPE, ScreenBase> MapScreen = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        GameStatic.context = this;
        GameStatic.assets = this.getAssets();

        this.screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        this.screenHeigh = getWindowManager().getDefaultDisplay().getHeight();

        GameStatic.ratio_x_screen = (float) this.screenWidth / 800;
        GameStatic.ratio_y_screen = (float)this.screenHeigh / 480;

        float scaleX = (float) 800
                / getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) 480
                / getWindowManager().getDefaultDisplay().getHeight();

        // should add list in here.
        logClass("==> Create Start");

        FrameLayout gameLayout = new FrameLayout(this);
        LinearLayout gameWidgets = new LinearLayout(this);

        TextView tx = new TextView(this);
        tx.setText("Here you are.!!!!");
        tx.setTextColor(Color.RED);

        EditText et = new EditText(this);
        et.setX(100);
        et.setY(100);
        et.setWidth(200);

        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = mInflater.inflate(R.layout.game_layout,gameLayout, false);

        threadDraw = new ThreadDraw(this);
        threadUpdate = new ThreadUpdate(this);
        fileIO = new AndroidFileIO(this);
        audio = new AndroidAudio(this);
        input = (Input) new GameInput(this, threadDraw, 1, 1);


        gameWidgets.addView(tx);
        gameLayout.addView(threadDraw);
        gameLayout.addView(gameWidgets);
        gameLayout.addView(v);
//        gameWidgets.removeView(tx);
        gameWidgets.addView(et);
//        gameLayout.addView(gameWidgets);



        setContentView(gameLayout);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
        logClass("==> Create End");
    }

    public ScreenBase getScreen(GameScreen.SCREEN_TYPE type){
        if(MapScreen.containsKey(type)) {
            return MapScreen.get(type);
        }else {
            ScreenBase screen = null;
            switch (type) {
                case SCEEN_MENU:
                    screen = new MenuScreen(this, GameScreen.SCREEN_TYPE.SCEEN_MENU);
                    break;
                case SCREEN_LOADING:
                    screen = new LoadingScreen(this, GameScreen.SCREEN_TYPE.SCREEN_LOADING);
                    break;
                case SCREEN_OPTION:
                    screen = new OptionScreen(this, GameScreen.SCREEN_TYPE.SCREEN_OPTION);
                    break;
                case SCREEN_HIGHSCORE:
                    screen = new HighScoreScreen(this, GameScreen.SCREEN_TYPE.SCREEN_HIGHSCORE);
                    break;
                case SCREEN_PLAYGAME:
                    screen = new PlayGameScreen(this, GameScreen.SCREEN_TYPE.SCREEN_PLAYGAME);
                    break;
                default:
                    break;
            }
            MapScreen.put(type, screen);
            return screen;
        }
    }


    //pausing the game when activity is paused
    @Override
    protected void onPause() {
        super.onPause();
        wakeLock.release();
        this.getCurrentScreen().pause();
        threadDraw.onPause();
        threadUpdate.stop();
    }

    //running the game when activity is resumed
    @Override
    protected void onResume() {
        super.onResume();
        wakeLock.acquire();
        this.getCurrentScreen().resume();
        threadDraw.resume();
        threadUpdate.resume();

//        if (isFinishing())
//            screen.dispose();
    }


    public Input getInput() {
        return input;
    }

    public FileIO getFileIO() {
        return fileIO;
    }

    public Audio getAudio() {
        return audio;
    }

    @Override
    public int getWidth() {
        return this.screenWidth;
    }

    @Override
    public int getHeigh() {
        return this.screenHeigh;
    }

    @Override
    public void Draw(Canvas canvas) {
        if(getCurrentScreen() != null) {
            this.getCurrentScreen().Draw(canvas);
        }
    }

    @Override
    public void Update(float deltaTime) {
        if(getCurrentScreen() != null) {
            this.getCurrentScreen().Update(deltaTime);
        }
    }

    @Override
    public ACTIVITY_TYPE getTypeId() {
        return this.type;
    }

    @Override
    public String getTypeName() {
        String typeName = "";
        switch (type) {
            case LOADING_ACTIVITY:
                typeName = "Loading Activity";
                break;
            case MENU_ACTIVITY:
                typeName = "Menu Activity";
                break;
            default:
                break;
        }
        return typeName;
    }

    @Override
    public int goToActivity(ACTIVITY_TYPE type) {
        switch (type) {
            case LOADING_ACTIVITY:
                break;
            case MENU_ACTIVITY:
                break;
            default:
                break;
        }
        return 0;
    }

    public ScreenBase getCurrentScreen() {
        return currentScreen;
    }

    public void goToScreen(GameScreen.SCREEN_TYPE type) {
        ScreenBase screen = getScreen(type);

        if(screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        if(this.currentScreen != null) {
            this.currentScreen.pause();
            this.currentScreen.dispose();
        }

        screen.resume();
        screen.Update(0);
        this.currentScreen = screen;
    }

    protected void logClass(String str) {
        if(GameStatic.isLog) {
            str += " [ " + GameUtils.getThreadSignature() + "]";
            Log.d(this.getClass().getName(), str);
        }
    }

    public void onXMLCallback(View view) {
        Toast.makeText(this, "here you are.", Toast.LENGTH_LONG).show();
    }
}

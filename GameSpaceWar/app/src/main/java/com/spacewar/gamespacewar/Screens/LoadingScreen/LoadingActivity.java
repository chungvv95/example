package com.spacewar.gamespacewar.Screens.LoadingScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.spacewar.gamespacewar.R;
import com.spacewar.gamespacewar.Screens.MenuScreen.MenuActivity;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
    }

    public void onLoadingViewClicked(View v){

        startActivity(new Intent(this, MenuActivity.class));
    }
}

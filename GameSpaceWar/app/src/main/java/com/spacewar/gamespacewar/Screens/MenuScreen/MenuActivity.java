package com.spacewar.gamespacewar.Screens.MenuScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.spacewar.gamespacewar.GameActivity;
import com.spacewar.gamespacewar.R;

/**
 * Created by acer on 11/01/2018.
 */

public class MenuActivity extends Activity implements View.OnClickListener {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button = (Button) findViewById(R.id.btnPlay);

        button.setOnClickListener( this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, GameActivity.class));
    }
}

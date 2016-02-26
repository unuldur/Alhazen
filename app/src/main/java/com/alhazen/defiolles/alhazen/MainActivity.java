package com.alhazen.defiolles.alhazen;

import android.app.ActionBar;
import android.app.NotificationManager;
import android.content.Context;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alhazen.defiolles.alhazen.Game.GameView;

public class MainActivity extends AppCompatActivity {

    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this,savedInstanceState);
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        gameView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        if(actionBar != null) actionBar.hide();
        setContentView(gameView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        gameView.saveInstanceState(savedInstanceState);

        super.onSaveInstanceState(savedInstanceState);
    }


}

package com.alhazen.defiolles.alhazen;

import android.app.ActionBar;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SurfaceView;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import com.alhazen.defiolles.alhazen.Game.GameView;

import static android.support.v7.appcompat.R.layout.abc_action_menu_layout;

public class MainActivity extends AppCompatActivity{


    int level;
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        level = getIntent().getIntExtra("NewLevel",1);
        gameView = new GameView(this,savedInstanceState,level);
        setContentView(gameView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        gameView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = getActionBar();
        if(actionBar != null) actionBar.hide();
        gameView.resume();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,PauseActivity.class);
        intent.putExtra("LevelCours",level);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }



    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        gameView.saveInstanceState(savedInstanceState);
        super.onSaveInstanceState(savedInstanceState);
    }

}

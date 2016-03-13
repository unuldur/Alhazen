package com.alhazen.defiolles.alhazen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by vadoreau on 01/03/16.
 */
public class MenuActivity extends AppCompatActivity {

    public static final int MAXLEVEL = 2;

    Button newGameButton;
    Button multiplayerButton;
    Button quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null)
        {
            Log.d("coucou",String.valueOf(savedInstanceState.getInt("LevelFini")));
        }
        int level = getSharedPreferences("LevelFini",MODE_PRIVATE).getInt("LevelFini",1);

        newGameButton = (Button) findViewById(R.id.newGameButton);
        multiplayerButton = (Button) findViewById(R.id.multiplayerButton);
        quitButton = (Button) findViewById(R.id.quitButton);


        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SelectionLevelActivity.class);
                startActivity(intent);
            }
        });

        multiplayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MultiplayerActivity.class);
                startActivity(intent);
            }
        });

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

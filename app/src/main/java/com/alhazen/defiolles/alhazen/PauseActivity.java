package com.alhazen.defiolles.alhazen;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alhazen.defiolles.alhazen.Game.GameView;

/**
 * Created by PAYS on 09/03/2016.
 */
public class PauseActivity extends AppCompatActivity{

        int level;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.pause_layout);

            level = getIntent().getIntExtra("LevelCours",0);


            Button button = (Button)findViewById(R.id.Continuer);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

            Button button2 = (Button)findViewById(R.id.Quit);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PauseActivity.this,MenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });

            Button button3 = (Button)findViewById(R.id.RestartPause);
            System.out.println(button3);
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PauseActivity.this,MainActivity.class);
                    intent.putExtra("NewLevel",level);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            });


        }
}

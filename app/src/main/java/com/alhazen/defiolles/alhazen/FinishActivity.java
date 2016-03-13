package com.alhazen.defiolles.alhazen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by PAYS on 10/03/2016.
 */
public class FinishActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int level = getIntent().getIntExtra("LevelCour", 999);


        setContentView(R.layout.level_suivant_layout);
        Button button = (Button)findViewById(R.id.Level_suivant);


        if(level == MenuActivity.MAXLEVEL)
        {
            ((TextView)findViewById(R.id.tvFinish)).setText(R.string.GameFinish);
            button.setEnabled(false);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishActivity.this, MainActivity.class);
                intent.putExtra("NewLevel", level + 1);
                startActivity(intent);
                finish();
            }
        });

        Button button2 = (Button)findViewById(R.id.Restart);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishActivity.this, MainActivity.class);
                intent.putExtra("NewLevel", level);
                startActivity(intent);
                finish();
            }
        });

        Button button3 = (Button)findViewById(R.id.QuitFinish);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}

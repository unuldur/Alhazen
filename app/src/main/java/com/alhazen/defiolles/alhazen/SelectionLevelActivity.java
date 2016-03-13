package com.alhazen.defiolles.alhazen;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;

/**
 * Created by PAYS on 12/03/2016.
 */
public class SelectionLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_level);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rvSelectionLevel);
        ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<String>(this, R.layout.list_level);
        ArrayAdapter<Boolean> mArrayAdapter2 = new ArrayAdapter<>(this, R.layout.list_level);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        int level=  getSharedPreferences("LevelFini", Context.MODE_PRIVATE).getInt("LevelFini",1);

        for(int i = 1 ; i <= MenuActivity.MAXLEVEL ; i++)
        {
            mArrayAdapter.add(String.valueOf(i));
            mArrayAdapter2.add( i <= level);
        }

        AdapteurLevel mAdapter = new AdapteurLevel(mArrayAdapter,mArrayAdapter2);
        mRecyclerView.setAdapter(mAdapter);
    }
}

package com.alhazen.defiolles.alhazen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by vadoreau on 02/03/16.
 */
public class MultiplayerActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 1;


    private RecyclerView mRecyclerViewBluetooth;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> mArrayAdapter;

    private BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mRecyclerViewBluetooth = (RecyclerView) findViewById(R.id.RVBluetoothDevices);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        mArrayAdapter = new ArrayAdapter<String>(this, R.layout.layout_my_list_item);


        mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                // When discovery finds a device
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // Add the name and address to an array adapter to show in a ListView
                    mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                }
            }
        };

        setContentView(R.layout.activity_multiplayer);

        mRecyclerViewBluetooth = (RecyclerView) findViewById(R.id.RVBluetoothDevices);

        mRecyclerViewBluetooth.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerViewBluetooth.setLayoutManager(mLayoutManager);

        startBluetooth();

        mAdapter = new MyAdapter(mArrayAdapter);
        mRecyclerViewBluetooth.setAdapter(mAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case REQUEST_ENABLE_BT :
                if(resultCode == RESULT_CANCELED) {
                    Toast toast = Toast.makeText(this, R.string.erreurBluetooth, Toast.LENGTH_LONG);
                    toast.show();

                    finish();
                }
                break; // Code non executé
        }

    }

    private void startBluetooth(){

        if (mBluetoothAdapter == null) {
            finish();
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }



        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }

        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter);


        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivity(discoverableIntent);


    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}

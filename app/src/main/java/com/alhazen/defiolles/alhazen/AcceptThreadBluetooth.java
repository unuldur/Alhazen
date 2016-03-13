package com.alhazen.defiolles.alhazen;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by vadoreau on 12/03/16.
 */
public class AcceptThreadBluetooth extends Thread {
    private BluetoothServerSocket mServerSocket ;
    private final String NAME = "test";
    private final UUID  MY_UUID = UUID.randomUUID();

    public void acceptThread(){
        BluetoothServerSocket tmp = null;
        BluetoothAdapter mBluetoothAdapter=null;



        try {
            // MY_UUID is the app's UUID string, also used by the client code

            tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(NAME, MY_UUID);
        } catch (IOException e) { }
        mServerSocket = tmp;

    }
}

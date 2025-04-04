package com.example.mobile;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import java.io.IOException;
import java.util.UUID;

public class BlueControl {
    private BluetoothSocket btSocket;
    private final BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
    private final Context context;

    public BlueControl(Context context) {
        this.context = context;
    }

    public boolean connectToDevice(String deviceAddress) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, handle accordingly
            return false;
        }

        try {
            BluetoothDevice device = btAdapter.getRemoteDevice(deviceAddress);
            btSocket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            btSocket.connect();
            return true;
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }
}
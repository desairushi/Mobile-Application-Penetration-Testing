package com.example.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.util.ArrayList;

//Set up bluetooth &nbsp;: &nbsp; android developers (no date) Android Developers.
// Available at: https://developer.android.com/guide/topics/connectivity/bluetooth/setup (Accessed: January 13, 2023).

public class bluetooth extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private TextView mBluetoothStatusTextView;
    private ListView mDevicesListView;
    private ArrayAdapter<String> mDevicesArrayAdapter;
    private ArrayList arrayList;
    private Button bttoggle,btdis;

    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @SuppressLint("MissingPermission")
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                BluetoothDevice device = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                mDevicesArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothStatusTextView = findViewById(R.id.text_view_bluetooth_status);
        mDevicesListView = findViewById(R.id.list_view_devices);
        arrayList = new ArrayList();
        mDevicesArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,arrayList);
        mDevicesListView.setAdapter(mDevicesArrayAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothStatusTextView.setText("Bluetooth is enabled");
        } else {
            mBluetoothStatusTextView.setText("Bluetooth is disabled");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mBroadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(mBroadcastReceiver);
    }

    @SuppressLint("MissingPermission")
    public void toggleBluetooth(View view) {
        if (mBluetoothAdapter.isEnabled()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mBluetoothAdapter.disable();
            mBluetoothStatusTextView.setText("Bluetooth is disabled");
            mDevicesArrayAdapter.clear();
        } else {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }
    }

    @SuppressLint("MissingPermission")
    public void discoverDevices(View view) {
        mDevicesArrayAdapter.clear();
        if (mBluetoothAdapter.isDiscovering()) {
            mBluetoothAdapter.cancelDiscovery();
        }
        mBluetoothAdapter.startDiscovery();
    }
}

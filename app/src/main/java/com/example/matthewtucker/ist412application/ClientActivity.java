package com.example.matthewtucker.ist412application;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.content.Context.BLUETOOTH_SERVICE;
import static com.example.matthewtucker.ist412application.R.styleable.Toolbar;

public class ClientActivity extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Button startScan;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_FINE_LOCATION =2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);

        mBluetoothAdapter = bluetoothManager.getAdapter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startScan = (Button) findViewById(R.id.start_scan_button);
        startScan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startScan();
            }
        });
    }


    protected void onResume(){
        super.onResume();
        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            finish();
        }
    }


    private void startScan(){
        if(!hasPermissions()|| mScanning){
            return;
        }
    }

    private boolean hasPermissions(){
        if(mBluetoothAdapter == null|| !mBluetoothAdapter.isEnabled()){
            requestBluetoothEnable();
            return false;
        }
        else if (!hasLocationPermissions()){
            requestLocationPermission();
            return false;
        }

        return true;
    }

    private void requestBluetoothEnable(){
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        Log.d("ClientActivity", "Requested user enables Bluetooth. Try again");
    }

    private boolean hasLocationPermissions(){
        return checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission(){
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_FINE_LOCATION);
    }



}

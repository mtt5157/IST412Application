package com.example.matthewtucker.ist412application;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ParcelUuid;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static android.content.Context.BLUETOOTH_SERVICE;
import static com.example.matthewtucker.ist412application.R.styleable.Toolbar;

public class ClientActivity extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;
    private BtleScanCallback mScanCallback;
    private boolean mScanning;
    private Button startScan;
    private Button stopScan;
    private Handler handler;
    private String uuid;
    private UUID serviceUuid;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_FINE_LOCATION =2;
    private Map<String, BluetoothDevice> mScanResults;

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

        stopScan = (Button) findViewById(R.id.stop_scan_button);
        stopScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopScan();
            }
        });

        uuid = "5154474C-9000-0101-0001-000000000001";
        serviceUuid = UUID.fromString(uuid);
    }


    protected void onResume(){
        super.onResume();

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            finish();
            return;
        }

    }


    private void startScan(){
        if(!hasPermissions()|| mScanning){
            return;
        }
        List<ScanFilter> filters = new ArrayList<>();
        ScanFilter scanFilter = new ScanFilter.Builder().setServiceUuid(new ParcelUuid(serviceUuid)).build();
        filters.add(scanFilter);
        ScanSettings settings  = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_POWER).build();
        mScanResults = new HashMap<>();
        mScanCallback = new BtleScanCallback(mScanResults);
        mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
        mBluetoothLeScanner.startScan(filters, settings, mScanCallback);
        mScanning = true;
        handler = new Handler();
        handler.postDelayed(this::stopScan,5000);
    }

    private void stopScan(){
         if(mScanning && mBluetoothAdapter != null && mBluetoothAdapter.isEnabled()&& mBluetoothAdapter!=null) {
             mBluetoothLeScanner.stopScan(mScanCallback);
             scanComplete();
         }

         mScanCallback = null;
         mScanning = false;
         handler = null;
    }

    private void scanComplete(){
        if(mScanResults.isEmpty()){
            return;
        }

        for(String deviceAddress: mScanResults.keySet()){
            Log.d("ClientActivity", "Found Device: "+ deviceAddress);
        }
    }

    private class BtleScanCallback extends ScanCallback {
        private Map<String, BluetoothDevice> mScanResults;

        BtleScanCallback(Map<String, BluetoothDevice>scanResults){
            this.mScanResults = scanResults;
        }
        @Override
        public void onScanResult(int callbackType, ScanResult result){
            addScanResult(result);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results){
            for(ScanResult result: results){
                addScanResult(result);
            }
        }

        @Override
        public void onScanFailed(int errorCode){
            Log.e("ClientActivity", "BLE scan failed with code"+ errorCode);
        }

        private void addScanResult(ScanResult result){
            BluetoothDevice device = result.getDevice();
            String deviceAddress = device.getAddress();
            mScanResults.put(deviceAddress, device);
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

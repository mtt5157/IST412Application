package com.example.matthewtucker.ist412application.Activities;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.matthewtucker.ist412application.R;
import com.example.matthewtucker.ist412application.Util.RunDataParser;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ClientActivity extends AppCompatActivity {
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothLeScanner bluetoothLeScanner;
    private BtleScanCallback btleScanCallback;
    private boolean scanning;
    private Button startScan;
    private Button stopScan;
    private Handler handler;
    private String uuid;
    private UUID serviceUuid;
    private UUID characteristicUuid;
    private boolean connected;
    private BluetoothGatt gatt;
    private Button disconnect;
    private String uuid2;
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_FINE_LOCATION =2;
    private Map<String, BluetoothDevice> mScanResults;
    private boolean mInitialized;
    private boolean echoInitialized;
    private RunDataParser parser;

    // For this class, Matt Tucker, Matt Peron, and Adam Warfield worked collaboriatively. It is nearly impossible to attribute specific lines of code to one individual as we all sat with our
    // computers open looking at the BigNerdRanch tutorial making suggestions for edits and debugging. We feel it would be unfair and quite frankly untrue to comment each indivudual
    // method as if one person wrote it. This class took time up on every sprint

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);

        bluetoothAdapter = bluetoothManager.getAdapter();

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

        disconnect = (Button) findViewById(R.id.disconnect_button);
        disconnect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                disconnectGattServer();
            }
        });

        uuid = "5154474C-9000-0101-0001-000000000001";
        uuid2 ="5154474C-9000-0101-0004-000000000000";
        serviceUuid = UUID.fromString(uuid);
        characteristicUuid = UUID.fromString(uuid2);
        parser = new RunDataParser();
    }


    protected void onResume(){
        super.onResume();

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)){
            finish();
            return;
        }

    }


    private void startScan(){
        if(!hasPermissions()|| scanning){
            return;
        }
        disconnectGattServer();

        mScanResults = new HashMap<>();
        btleScanCallback = new BtleScanCallback(mScanResults);
        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();

        //change BLE5 to whatever your device name in order for this app to be able to connect
        ScanFilter scanFilter = new ScanFilter.Builder().setDeviceName("BLE5").build();
        List<ScanFilter> filters = new ArrayList<>();
        filters.add(scanFilter);
        ScanSettings settings  = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_POWER).build();


        bluetoothLeScanner.startScan(filters, settings, btleScanCallback);

        handler = new Handler();
        scanning = true;

    }

    private void stopScan(){

         if(scanning && bluetoothAdapter != null && bluetoothAdapter.isEnabled()&& bluetoothLeScanner !=null) {
             bluetoothLeScanner.stopScan(btleScanCallback);
             scanComplete();
         }

         btleScanCallback = null;
         scanning = false;
         handler = null;
    }

    private void scanComplete(){
        if(mScanResults.isEmpty()){
            System.out.println("empty");
            return;

        }
        else{
            System.out.print("Full");
        }

        for(String deviceAddress: mScanResults.keySet()){
            System.out.println("Device Name: "+mScanResults.get(deviceAddress).getName());
            System.out.println("UUID: "+mScanResults.get(deviceAddress).getUuids());
            System.out.println("DEVICE ADDRESS: "+deviceAddress);

            BluetoothDevice device = mScanResults.get(deviceAddress);
            connectDevice(device);

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

    private void connectDevice(BluetoothDevice device){
        GattClientCallback clientCallback = new GattClientCallback();
        gatt = device.connectGatt(this, false, clientCallback);
    }



    private class GattClientCallback extends BluetoothGattCallback{
        @Override
      public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState){
            super.onConnectionStateChange(gatt, status, newState);

            if(status == BluetoothGatt.GATT_FAILURE){
                disconnectGattServer();
                return;
            }

            if(newState == BluetoothProfile.STATE_CONNECTED){
                connected = true;
                System.out.println("Connected to "+gatt.getDevice().getName());
                gatt.discoverServices();

            }

            else if(newState == BluetoothProfile.STATE_DISCONNECTED){
                disconnectGattServer();
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status){
            super.onServicesDiscovered(gatt,status);

            if(status!= BluetoothGatt.GATT_SUCCESS){
                System.out.println("GATT Failure");
                return;
            }

            if(status == BluetoothGatt.GATT_SUCCESS){
                System.out.println("Success");

            }

            BluetoothGattService service = gatt.getService(serviceUuid);
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(characteristicUuid);
            if(characteristic == null){
                System.out.println("Characteristic null");
            }
            characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
            mInitialized = gatt.setCharacteristicNotification(characteristic,true);
            gatt.readCharacteristic(characteristic);

        }


        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic){
            super.onCharacteristicChanged(gatt, characteristic);
            readCharacteristic(characteristic);
            System.out.println(characteristic.getValue());
        }



       @Override
       public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic,int status){
            super.onCharacteristicRead(gatt, characteristic, status);

            if(status == BluetoothGatt.GATT_SUCCESS){
                if(characteristic == null){
                    System.out.println("Characterisitic null");
                }

                byte[] bytes =characteristic.getValue();
                if(bytes !=null){
                    System.out.println(bytes);
                    parser.parseData(bytes);
                }
                }
        }

        private void readCharacteristic(BluetoothGattCharacteristic characteristic){
            byte[] messageBytes = characteristic.getValue();
            String message  = " "+ messageBytes;
            if(message ==null){
                return;
            }
        }


















    }



    public void disconnectGattServer(){
        connected = false;
        if(gatt != null){
            gatt.disconnect();
            gatt.close();
        }
    }

    private boolean hasPermissions(){
        if(bluetoothAdapter == null|| !bluetoothAdapter.isEnabled()){
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

package com.example.bluetooth2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

public class Baglanti extends AppCompatActivity {
    private String address;
    private Context context = this;
    private BluetoothAdapter myBluetooth=null;
    static BluetoothSocket btSocket;
    private Button cizgiİzleyen,kontrol,kontrolSensor;

    private boolean isBtConnected = false;
    private static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baglanti);
        cizgiİzleyen = findViewById(R.id.cizgiİzle);
        kontrol = findViewById(R.id.kontrol);
        kontrolSensor = findViewById(R.id.sensorKontrol);

        /*cizgiİzleyen.setVisibility(View.GONE);
        kontrolSensor.setVisibility(View.GONE);
        kontrol.setVisibility(View.GONE);*/

        /*address = getIntent().getExtras().getString("device_address");
        new BTbaglanti().execute();*/

        cizgiİzleyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Baglanti.this,Cizgiİzleyen.class));
            }
        });

        kontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Baglanti.this,Kontrol.class));
            }
        });

        kontrolSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Baglanti.this,SensorKontrol.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        disconnect();
    }


    public void disconnect()    {
        if (btSocket != null) {
            try {
                btSocket.close();
            } catch (IOException e) {
            }

        }
        finish();
    }


    public class BTbaglanti extends AsyncTask<Void,Void,Void>{

        private boolean ConnectSuccess =true;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(context,"Baglaniliyor..",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                if (btSocket == null || !isBtConnected){
                    myBluetooth =BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice cihaz =myBluetooth.getRemoteDevice(address);
                    btSocket = cihaz.createRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            }catch (Exception e){
                try {
                    BluetoothDevice cihaz = myBluetooth.getRemoteDevice(address);
                    btSocket = cihaz.createRfcommSocketToServiceRecord(myUUID);

                    Class<?> clazz = btSocket.getRemoteDevice().getClass();
                    Class<?>[] paramTypes = new Class<?>[] {Integer.TYPE};
                    Method m = clazz.getMethod("createRfcommSocket", paramTypes);
                    Object[] params = new Object[] {Integer.valueOf(1)};

                    btSocket= (BluetoothSocket) m.invoke(btSocket.getRemoteDevice(), params);
                    btSocket.connect();
                    Log.d("1. HATA OLUŞTU AMA","ÇÖZÜLDÜ");
                } catch (Exception e1) {
                    ConnectSuccess = false;
                    Log.d("HATA",e.toString());
                    Log.d("HATA",e1.toString());
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (!ConnectSuccess) {
                Toast.makeText(context,"Baglanti Hatasi",Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(context,"Baglanti Olustu",Toast.LENGTH_SHORT).show();
                cizgiİzleyen.setVisibility(View.VISIBLE);
                kontrolSensor.setVisibility(View.VISIBLE);
                kontrol.setVisibility(View.VISIBLE);
                isBtConnected = true;
            }
        }
    }
}

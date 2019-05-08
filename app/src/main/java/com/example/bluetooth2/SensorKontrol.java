package com.example.bluetooth2;

import android.bluetooth.BluetoothSocket;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class SensorKontrol extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private BluetoothSocket btSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_kontrol);

        btSocket = Baglanti.btSocket;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

        /*S GİDERSE SENSOR KONTROLU BASLAR*/
        try {
            btSocket.getOutputStream().write("s".toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        if (Math.abs(x) > Math.abs(y)) {
            if (x < -3) {
                try {
                    Log.e("Yusufali","sag");
                    btSocket.getOutputStream().write("r".toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (x > 3) {
                try {
                    Log.e("Hasan Cerit","sol");
                    btSocket.getOutputStream().write("l".toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else {
            if (y < -3) {
                try {
                    Log.e("Yusufali","İleri");
                    btSocket.getOutputStream().write("i".toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
            if (y > 3) {
                try {
                    Log.e("Yusufali","geri");
                    btSocket.getOutputStream().write("g".toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        if (x > (-2) && x < (2) && y > (-2) && y < (2)) {
            Log.e("Yusufali","İşlem yok");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            btSocket.getOutputStream().write("s".toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        geriDön();
        finish();
    }

    public void geriDön(){
        try {
            btSocket.getOutputStream().write("b".toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

package com.example.bluetooth2;

import android.bluetooth.BluetoothSocket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

public class Cizgiİzleyen extends AppCompatActivity {
    private BluetoothSocket btSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cizgi_izleyen);

        /*btSocket = Baglanti.btSocket;

        //o GİDERSE CİZGİ İZLEYEN BASLAR
        try {
            btSocket.getOutputStream().write("o".toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        //o GİDERSE CİZGİ İZLEYEN BASLAR
        /*try {
            btSocket.getOutputStream().write("o".toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
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

package com.example.bluetooth2;

import android.bluetooth.BluetoothSocket;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import javax.security.auth.login.LoginException;

public class Kontrol extends AppCompatActivity  {
    private BluetoothSocket btSocket;
    private Button ileri,geri,sag,sol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrol);

        ileri = findViewById(R.id.ileri);
        geri = findViewById(R.id.geri);
        sag = findViewById(R.id.sag);
        sol = findViewById(R.id.sol);

        //C GİDERSE KONTROL BSALAR
        btSocket = Baglanti.btSocket;
        try {
            btSocket.getOutputStream().write("c".toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*//ONCLİCKLER
        ileri.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                veriGonder("i","d",motionEvent);
                return false;
            }
        });
        geri.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                veriGonder("g","d",motionEvent);
                return false;
            }
        });
        sag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                veriGonder("r","d",motionEvent);
                return false;
            }
        });*/

        /*sol.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                veriGonder("l","d",motionEvent);
                return false;
            }
        });*/

        //onclicks2
        ileri.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (btSocket!=null){
                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            try {
                                Log.d("HAREKET","İLERİ");
                                btSocket.getOutputStream().write("i".toString().getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            try {
                                Log.d("HAREKET","DUR");
                                btSocket.getOutputStream().write("d".toString().getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
                return false;
            }
        });
        sag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (btSocket!=null){
                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            try {
                                Log.d("HAREKET","SAG");
                                btSocket.getOutputStream().write("r".toString().getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            try {

                                Log.d("HAREKET","DUR");
                                btSocket.getOutputStream().write("d".toString().getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
                return false;
            }
        });
        sol.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (btSocket!=null){
                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            try {
                                btSocket.getOutputStream().write("l".toString().getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            try {
                                btSocket.getOutputStream().write("d".toString().getBytes());
                                Log.d("HAREKET","DUR");

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
                return false;
            }
        });
        geri.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (btSocket!=null){
                    switch (motionEvent.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            try {
                                Log.d("HAREKET","GERİ");

                                btSocket.getOutputStream().write("g".toString().getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;

                        case MotionEvent.ACTION_UP:
                            try {
                                Log.d("HAREKET","DUR");

                                btSocket.getOutputStream().write("d".toString().getBytes());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //o GİDERSE CİZGİ İZLEYEN BASLAR
        try {
            btSocket.getOutputStream().write("c".toString().getBytes());
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

    public void veriGonder(String basili,String cekili,MotionEvent motionEvent){
        if (btSocket!=null){
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_DOWN:
                    try {
                        btSocket.getOutputStream().write(basili.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    try {
                        btSocket.getOutputStream().write(cekili.toString().getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }
}

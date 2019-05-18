package com.example.bluetooth2;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Button acKapat,eslesmisButon;
    private BluetoothAdapter bluetoothAdapter;
    private Set<BluetoothDevice> eslesmisCihazlar;
    private ListView eslesmisList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acKapat = findViewById(R.id.buton);
        eslesmisButon = findViewById(R.id.eslesmisGöster);
        eslesmisList = findViewById(R.id.eslesmisList);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        acKapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acKapatAction();
            }
        });

        eslesmisButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eslesmisGöster();
            }
        });

        eslesmisList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                baglan(view);
            }});




        Button git = findViewById(R.id.git);
        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Baglanti.class));
            }
        });
    }

    public void acKapatAction(){
        if (bluetoothAdapter == null){
            Toast.makeText(this,"Cihaz Bozuk",Toast.LENGTH_SHORT);
        }
        if(!bluetoothAdapter.isEnabled()){
            Intent ıntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(ıntent);
        }else{
            bluetoothAdapter.disable();
        }
    }

    public void eslesmisGöster(){
        eslesmisCihazlar = bluetoothAdapter.getBondedDevices();
        ArrayList<String> eslesmisCihazlarArray = new ArrayList<>();

        if (eslesmisCihazlar.size() == 0){
            Toast.makeText(this,"Eslesmis Cihaz Bulunamadı",Toast.LENGTH_SHORT);
        }else{
            for (BluetoothDevice bluetoothDevice : eslesmisCihazlar)
                eslesmisCihazlarArray.add(bluetoothDevice.getName()+"/n"+bluetoothDevice.getAddress());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,eslesmisCihazlarArray);
            eslesmisList.setAdapter(adapter);
        }
    }

    public void baglan(View view){
        String text = ((TextView) view).getText().toString();
        String address = text.substring(text.length()-17);

        Intent baglantiIntent = new Intent(MainActivity.this ,Baglanti.class);
        baglantiIntent.putExtra("device_address",address);
        startActivity(baglantiIntent);
    }
}

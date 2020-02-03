package com.example.datacalas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText nama;
    private EditText posisi;
    private EditText gajih;

    private Button add;
    private Button view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nama = (EditText) findViewById(R.id.nama);
        posisi = (EditText) findViewById(R.id.posisi);
        gajih = (EditText) findViewById(R.id.gajih);

        add = (Button) findViewById(R.id.add);
        view = (Button) findViewById(R.id.view);

    }

    private void tambahcalas(){
        final String nmcalas = nama.getText().toString().trim();
        final String pscalas = posisi.getText().toString().trim();
        final String gjcalas = gajih.getText().toString().trim();

        class tambahcalas extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void  onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Santuy...",false,false);
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... v){
                HashMap<String, String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA.nama);
                params.put(konfigurasi.KEY_EMP_POSISI.posisi);
                params.put(konfigurasi.KEY_EMP_GAJIH.gajih);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD,params);
                return res;
            }
        }
        tambahcalas ae = new tambahcalas();
        ae.execute();
    }
    @Override
    public void onClick(View v){
        if (v == add){
            tambahcalas();
        }
        if (v == view){
            startActivity(new Intent(this, tampilsemua.class));
        }
    }
}

package com.example.datacalas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private EditText nim;
    private EditText nama;
    private EditText posisi;
    private EditText gajih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nim = (EditText) findViewById(R.id.nim);
        nama = (EditText) findViewById(R.id.nama);
        posisi = (EditText) findViewById(R.id.posisi);
        gajih = (EditText) findViewById(R.id.gajih);

    }

    private void tambahcalas(){
        final String nimcalas = nim.getText().toString().trim();
        final String nmcalas = nama.getText().toString().trim();
        final String pscalas = posisi.getText().toString().trim();
        final String gjcalas = gajih.getText().toString().trim();

        class TambahCalas extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
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
                params.put(konfigurasi.KEY_EMP_NIM, nimcalas);
                params.put(konfigurasi.KEY_EMP_NAMA, nmcalas);
                params.put(konfigurasi.KEY_EMP_POSISI, pscalas);
                params.put(konfigurasi.KEY_EMP_GAJIH, gjcalas);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }
        TambahCalas TC = new TambahCalas();
        TC.execute();
    }
    public void tambah(View v) {
        tambahcalas();
    }
            public void view(View v){
            Intent i = new Intent(MainActivity.this, tampilsemua.class);
            startActivity(i);
        }
    }

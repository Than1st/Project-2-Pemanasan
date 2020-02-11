package com.example.datacalas;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class tampilcalas extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextnim;
    private EditText editTextnama;
    private EditText editTextposisi;
    private EditText editTextgajih;

    private Button buttonUpdate;
    private Button buttonDelete;
    String nim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilcalas);

        Intent intent = getIntent();
        nim = intent.getStringExtra(konfigurasi. EMP_NIM);

        editTextnim = (EditText) findViewById(R.id.editTextnim);
        editTextnama = (EditText) findViewById(R.id.editTextnama);
        editTextposisi = (EditText) findViewById(R.id.editTextposisi);
        editTextgajih = (EditText) findViewById(R.id.editTextgajih);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener((View.OnClickListener) this);
        buttonDelete.setOnClickListener((View.OnClickListener) this);

        editTextnim.setText(nim);

        getCalas();
    }

    private void getCalas(){
        class GetCalas extends AsyncTask<Void,Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(tampilcalas.this,"Fetching...","Wait...",false,false);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                showCalas(s);
            }
            @Override
            protected String doInBackground(Void... voids) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_EMP,nim);
                return s;
            }
        }
        GetCalas gc = new GetCalas();
        gc.execute();
    }
    private void showCalas(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nama = c.getString(konfigurasi.TAG_NAMA);
            String posisi = c.getString(konfigurasi.TAG_POSISI);
            String gajih = c.getString(konfigurasi.TAG_GAJIH);

            editTextnama.setText(nama);
            editTextposisi.setText(posisi);
            editTextgajih.setText(gajih);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    private void updateCalas(){
        final String nama = editTextnama.getText().toString().trim();
        final String posisi = editTextposisi.getText().toString().trim();
        final String gajih = editTextgajih.getText().toString().trim();

        class UpdateCalas extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(tampilcalas.this,"Update dulu...","Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tampilcalas.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... voids){
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(konfigurasi.KEY_EMP_NIM,nim);
                hashMap.put(konfigurasi.KEY_EMP_NAMA,nama);
                hashMap.put(konfigurasi.KEY_EMP_POSISI,posisi);
                hashMap.put(konfigurasi.KEY_EMP_GAJIH,gajih);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP, hashMap);
                return s;
            }
        }
        UpdateCalas uc = new UpdateCalas();
        uc.execute();
    }
    private void deleteCalas(){
        class DeleteCalas extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(tampilcalas.this,"Update dulu...","Tunggu...",false,false);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(tampilcalas.this,s,Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(Void... params){
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_DELETE_EMP, nim);
                return s;
            }
        }
        DeleteCalas dc = new DeleteCalas();
        dc.execute();
    }
    private void confirmDeleteCalas(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Yakin Mau Di hapus??");
        alertDialogBuilder.setPositiveButton("Ya",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCalas();
                        startActivity(new Intent(tampilcalas.this,tampilsemua.class));
                    }
                });
        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    public void onClick(View v){
        if (v == buttonUpdate){
            updateCalas();
        }
        if (v == buttonDelete){
            confirmDeleteCalas();
        }
    }
}

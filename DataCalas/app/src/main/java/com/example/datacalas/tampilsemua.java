package com.example.datacalas;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class tampilsemua extends AppCompatActivity implements ListView.OnItemClickListener{
    private ListView listView;
    private String JSON_STRING;
    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        listView = (ListView) findViewById(R.id.list_item);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
        getJSON();
    }

    private void showcalas(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(konfigurasi.TAG_JSON_ARRAY);
            for (int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String nim = jo.getString(konfigurasi.TAG_NIM);
                String nama = jo.getString(konfigurasi.TAG_NAMA);

                HashMap<String, String> calas = new HashMap<>();
                calas.put(konfigurasi.TAG_NIM, nim);
                calas.put(konfigurasi.TAG_NAMA, nama);
                list.add(calas);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                tampilsemua.this, list, R.layout.list_item,
                new String[]{konfigurasi.TAG_NIM,konfigurasi.TAG_NAMA},
                new int[]{R.id.nim, R.id.nama});
        listView.setAdapter(adapter);
    }
    private void getJSON(){
        class GetJSON extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(tampilsemua.this,"Mengambil Data","Mohon Tunggu...", false, false);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showcalas();
            }

            @Override
            protected String doInBackground(Void... params){
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(konfigurasi.URL_GET_EMP, konfigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }
    public void onItemClick(AdapterView<?> parent, View view, int position, long nim){
        Intent intent = new Intent(this, tampilcalas.class);
        HashMap map = (HashMap)parent.getItemAtPosition(position);
        String empnim = map.get(konfigurasi.TAG_NIM).toString();
        intent.putExtra(konfigurasi.EMP_NIM,empnim);
        startActivity(intent);
    }

}

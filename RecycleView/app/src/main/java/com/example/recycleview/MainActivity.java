package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.ResponseCache;
import java.util.ArrayList;
import java.util.List;

import data.MyData;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "http://192.168.0.13/crud/select.php";
    private List<MyData> ListData;
    private JSONArray request;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListData = new ArrayList<>();
        jsonrequest();
    }

    private void jsonrequest() {
        JsonArrayRequest jArr = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);

                        MyData item = new MyData();

                        item.setNim(jsonObject.getString("nim"));
                        item.setNama(jsonObject.getString("nama"));
                        item.setAlamat(jsonObject.getString("alamat"));
                        item.setEmail(jsonObject.getString("email"));
                        // menambah item ke array
                        ListData.add(item);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                setuprecyclerview(ListData);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });


        Volley.newRequestQueue(MainActivity.this).add(jArr);


    }
    @SuppressLint("ResourceType")
    private void setuprecyclerview(List<MyData> ListData){

        recyclerView = (RecyclerView) findViewById(R.layout.list_row);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}

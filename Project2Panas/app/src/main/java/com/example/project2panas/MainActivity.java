package com.example.project2panas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.project2panas.Adapter.AdapterData;
import com.example.project2panas.Model.ModelData;
import com.example.project2panas.Util.AppController;
import com.example.project2panas.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelData> mItems;
    Button btnInsert, btnDelete;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerview = (RecyclerView) findViewById(R.id.recycleviewtemp);
        btnInsert = (Button) findViewById(R.id.btn_input);
        btnDelete = (Button) findViewById(R.id.btn_delete);
        pd = new ProgressDialog(MainActivity.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterData(MainActivity.this,mItems);
        mRecyclerview.setAdapter(mAdapter);
    }

    private void loadJson(){
        pd.setMessage("Mengambil Data");
        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DATA, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","respon : "+response.toString());
                        for (int i = 0 ; i < response.length(); i++){
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelData md = new ModelData();
                                md.setNIM(data.getString("nim"));
                                md.setNama(data.getString("nama"));
                                md.setJk(data.getString("jk"));
                                md.setPeminatan(data.getString("peminatan"));
                                md.setAlamat(data.getString("alamat"));
                                md.setAgama(data.getString("agama"));
                                md.setEmail(data.getString("email"));
                                mItems.add(md);
                            }   catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });
        AppController.getInstance().addToRequestQueue(reqData);
    }
}

package Util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.projek2.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.AppController;
import data.Data;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    FloatingActionButton fab;
    ListView list;
    SwipeRefreshLayout swipe;
    List<Data> itemList = new ArrayList<Data>();
    Adapter adapter;
    int success;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText txt_nim, txt_nama;
    String nim, nama;

    private static final String TAG = MainActivity.class.getSimpleName();

    private static String url_select     = Server.URL + "select.php";
    private static String url_insert     = Server.URL + "input.php";
    private static String url_edit       = Server.URL + "edit.php";
    private static String url_update     = Server.URL + "update.php";
    private static String url_delete     = Server.URL + "delete.php";

    public static final String TAG_NIM      = "nim";
    public static final String TAG_NAMA     = "nama";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    String tag_json_obj = "json_obj_req";

    public void pindahactivity(View v){
        Intent i = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(i);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // menghubungkan variablel pada layout dan pada java
        fab     = (FloatingActionButton) findViewById(R.id.fab_add);
        swipe   = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        list    = (ListView) findViewById(R.id.list);

        // untuk mengisi data dari JSON ke dalam adapter
        adapter = new adapter.Adapter(MainActivity.this, itemList);
        list.setAdapter((ListAdapter) adapter);

        // menamilkan widget refresh
        swipe.setOnRefreshListener(this);

        swipe.post(new Runnable() {
                       @Override
                       public void run() {
                           swipe.setRefreshing(true);
                           itemList.clear();
                           ((BaseAdapter)adapter).notifyDataSetChanged();
                           callVolley();
                       }
                   }
        );



        // fungsi floating action button memanggil form biodata
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DialogForm("", "", "SIMPAN");
//            }
//        });

        // listview ditekan lama akan menampilkan dua pilihan edit atau delete data
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view,
                                           final int position, long id) {
                // TODO Auto-generated method stub
                final String nimx = itemList.get(position).getNim();

                final CharSequence[] dialogitem = {"Edit", "Delete"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        switch (which) {
                            case 0:
                                edit(nimx);
                                break;
                            case 1:
                                delete(nimx);
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });

    }

    private void setSupportActionBar(Toolbar toolbar) {

    }

    @Override
    public void onRefresh() {
        itemList.clear();
        ((BaseAdapter)adapter).notifyDataSetChanged();
        callVolley();
    }

    // untuk mengosongi edittext pada form
    private void kosong(){
        txt_nim.setText(null);
        txt_nama.setText(null);
    }

    // untuk menampilkan dialog from biodata
//    private void DialogForm(String nimx, String namax, String button) {
//        dialog = new AlertDialog.Builder(MainActivity.this);
//        inflater = getLayoutInflater();
//        dialogView = inflater.inflate(R.layout.add_biodata, null);
//        dialog.setView(dialogView);
//        dialog.setCancelable(true);
//        dialog.setIcon(R.drawable.ic_people_black_24dp);
//        dialog.setTitle("Form Calas");
//
//        txt_nim      = (EditText) dialogView.findViewById(R.id.txt_nim);
//        txt_nama    = (EditText) dialogView.findViewById(R.id.txt_nama);
//
//        if (!nimx.isEmpty()){
//            txt_nim.setText(nimx);
//            txt_nama.setText(namax);
//        } else {
//            kosong();
//        }
//
//        dialog.setPositiveButton(button, new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                nim     = txt_nim.getText().toString();
//                nama    = txt_nama.getText().toString();
//
//                simpan_update();
//                dialog.dismiss();
//            }
//        });
//
//        dialog.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                kosong();
//            }
//        });
//
//        dialog.show();
//    }

    // untuk menampilkan semua data pada listview
    private void callVolley(){
        itemList.clear();
        ((BaseAdapter)adapter).notifyDataSetChanged();
        swipe.setRefreshing(true);

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());

                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        Data item = new Data();

                        item.setNim(obj.getString(TAG_NIM));
                        item.setNama(obj.getString(TAG_NAMA));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                // notifikasi adanya perubahan data pada adapter
                ((BaseAdapter)adapter).notifyDataSetChanged();

                swipe.setRefreshing(false);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                swipe.setRefreshing(false);
            }
        });

        // menambah request ke request queue
        AppController.getInstance().addToRequestQueue(jArr);
    }

    // fungsi untuk menyimpan atau update
    private void simpan_update() {
        String url;
        // jika id kosong maka simpan, jika id ada nilainya maka update
        if (nim.isEmpty()){
            url = url_insert;
        } else {
            url = url_update;
        }

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("Add/update", jObj.toString());

                        callVolley();
                        kosong();

                        Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                        ((BaseAdapter)adapter).notifyDataSetChanged();

                    } else {
                        Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                // jika id kosong maka simpan, jika id ada nilainya maka update
                if (nim.isEmpty()){
                    params.put("nama", nama);
                } else {
                    params.put("nim", nim);
                    params.put("nama", nama);
                }

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // fungsi untuk get edit data
    private void edit(final String nimx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_edit, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("get edit data", jObj.toString());
                        String nimx      = jObj.getString(TAG_NIM);
                        String namax    = jObj.getString(TAG_NAMA);

//                        DialogForm(nimx, namax, "UPDATE");

                        ((BaseAdapter)adapter).notifyDataSetChanged();

                    } else {
                        Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nim", nimx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

    // fungsi untuk menghapus
    private void delete(final String nimx){
        StringRequest strReq = new StringRequest(Request.Method.POST, url_delete, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);

                    // Cek error node pada json
                    if (success == 1) {
                        Log.d("delete", jObj.toString());

                        callVolley();

                        Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        ((BaseAdapter)adapter).notifyDataSetChanged();

                    } else {
                        Toast.makeText(MainActivity.this, jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error: " + error.getMessage());
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();
                params.put("nim", nimx);

                return params;
            }

        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }

}

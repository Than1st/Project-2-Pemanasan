package com.example.pro2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
class Get_data{
    String nim="", nama="", jk="", peminatan="", alamat="", agama="", email="";
    Get_data(String nim, String nama, String jk, String peminatan, String alamat, String agama, String email){


    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getJk() {
        return jk;
    }

    public String getPeminatan() {
        return peminatan;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getAgama() {
        return agama;
    }

    public String getEmail() {
        return email;
    }

    }
}

class Custom_adapter extends BaseAdapter{

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Get_data>model;
    Custom_adapter(Context context, ArrayList<Get_data> model)
    {
        this.context=context;
        this.model=model;
    }
    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=layoutInflater.inflate(R.layout.list, null);
        TextView nim, nama, jk, peminatan, alamat, agama, email;
        nim = view.findViewById(R.id.nim);
        nama = view.findViewById(R.id.nama);
        jk = view.findViewById(R.id.jk);
        peminatan = view.findViewById(R.id.peminatan);
        alamat = view.findViewById(R.id.alamat);
        agama = view.findViewById(R.id.agama);
        email = view.findViewById(R.id.email);

        nim.setText((model.get(position)).getNim());

        return null;
    }
}
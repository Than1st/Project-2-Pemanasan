package com.example.datacalas;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datacalas.R;

public class tampilsemua extends AppCompatActivity implements ListView.OnCreateContextMenuListener {
    private ListView listView;
    private String JSON_STRING;
    @Override
    protected void  onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout,tampilsemua);
    }
}

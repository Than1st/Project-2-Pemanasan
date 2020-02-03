package com.example.project2panas.Util;

import android.app.Application;
import android.nfc.Tag;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Collection;

public class AppController extends Application {
    private static final String TAG = AppController.class.getSimpleName();
    private static AppController instance;
    RequestQueue mRequestQueue;
    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

    public static synchronized AppController getInstance(){
        return instance;
    }

    private RequestQueue getRequestQueue(){
        if (mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public <T> void addtoRequestQueue (Request<T> req){
        if (mRequestQueue == null){
            mRequestQueue.cancelAll(req);
        }
    }

}

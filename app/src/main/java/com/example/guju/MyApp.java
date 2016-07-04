package com.example.guju;

import android.app.Application;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
/**
 * Created by green on 2016/7/4.
 */
public class MyApp extends Application {
    private static MyApp app;
    private RequestQueue requestqueue;
    public static MyApp getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.app=this;
        initVolley();
    }

    private void initVolley() {
        requestqueue= Volley.newRequestQueue(this);
    }
    public RequestQueue getRequestQueue(){
        return requestqueue;
    }
}


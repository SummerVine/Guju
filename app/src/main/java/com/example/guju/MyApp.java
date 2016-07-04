package com.example.guju;

import android.app.Application;

/**
 * Created by green on 2016/7/4.
 */
public class MyApp extends Application {
    private static MyApp app;

    public static MyApp getApp() {
        return app;

    }



    @Override
    public void onCreate() {
        super.onCreate();
        this.app=this;

    }


}


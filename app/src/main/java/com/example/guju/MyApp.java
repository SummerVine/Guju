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
  //      initUIL();
    }
    private void initVolley() {
        requestqueue= Volley.newRequestQueue(getApplicationContext());
    }
    public RequestQueue getRequestQueue(){
        return requestqueue;
    }
//    private void initUIL() {
//        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
//                .threadPoolSize(3)
//                .memoryCacheSize(10 << 20)
//                .memoryCacheSizePercentage(12)
//                .diskCacheSize(100 << 20)
//                .diskCacheFileCount(100)
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
//                .writeDebugLogs()
//                .build();
//        ImageLoader.getInstance().init(configuration);
//    }
}


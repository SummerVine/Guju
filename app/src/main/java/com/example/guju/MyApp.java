package com.example.guju;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.guju.bean.User;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

/**
 * Created by green on 2016/7/4.
 */
public class MyApp extends Application {
    private static MyApp app;
    private DbUtils dbUtils;
    private RequestQueue requestqueue;
    public static MyApp getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.app=this;
        initVolley();
        initDbUtils();
    }

    private void initDbUtils() {
        dbUtils=DbUtils.create(this, "/sdcard/Download", "user_db", 1, new DbUtils.DbUpgradeListener() {
            @Override
            public void onUpgrade(DbUtils dbUtils, int i, int i1) {
if(i<i1){
    try {
        dbUtils.dropTable(User.class);
        dbUtils.createTableIfNotExist(User.class);
    } catch (DbException e) {
        e.printStackTrace();
    }
}
            }
        });
        try {
            dbUtils.createTableIfNotExist(User.class);
            dbUtils.configDebug(true);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
public  DbUtils getDbUtils(){
    return  dbUtils;
}
    private void initVolley() {
        requestqueue= Volley.newRequestQueue(this);
    }
    public RequestQueue getRequestQueue(){
        return requestqueue;
    }
}

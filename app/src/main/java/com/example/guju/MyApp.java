package com.example.guju;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.guju.bean.User;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

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
    private DbUtils dbUtils;
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


    public void showShare(String text,String url,String comment) {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(text);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(text);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(comment);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("谷居");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

   // 启动分享GUI
        oks.show(this);
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


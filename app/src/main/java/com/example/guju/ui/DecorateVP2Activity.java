package com.example.guju.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.guju.R;

/**
 * Created by liushuxin on 2016/7/10.
 */
public class DecorateVP2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.decorate_vpdetails2_activity);
        WebView wv_id = (WebView) findViewById(R.id.decorate_vp_wv2_id);
        wv_id.loadUrl("http://guju.com.cn/app_indexvr");
    }
}
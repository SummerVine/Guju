package com.example.guju.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.guju.R;

/**
 * Created by green on 2016/7/11.
 */

    public class PictureLibraryWebView extends AppCompatActivity {
        private WebView wb_id;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.picturelibrarywebview);
            //初始化界面并加载Web资源
            wb_id = ((WebView) findViewById(R.id.wb_id));
            Intent intent = getIntent();
            String uri = intent.getStringExtra("link");
            //WebView加载web资源
            WebSettings settings = wb_id.getSettings();
            settings.setJavaScriptEnabled(true);
            wb_id.setWebViewClient(new WebViewClient());
            wb_id.loadUrl(uri);
        }
}

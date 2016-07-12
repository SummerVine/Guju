package com.example.guju.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guju.R;

import com.example.guju.url.Urlan;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class StrategyActivity extends AppCompatActivity{


    private WebView webView;
    private ImageView back;
   private ImageView share;
    private Intent intent;
    private String pa;
   /* private ImageView xin;
    private ImageView zhan;
    private TextView free;*/
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuangxiu_detail);
        webView = ((WebView) findViewById(R.id.webview_id));
        back = (ImageView) findViewById(R.id.back_id);
      /*  xin = (ImageView) findViewById(R.id.iv_xin);
        zhan = (ImageView) findViewById(R.id.iv_zhan);
        free=(TextView)findViewById(R.id.tv_free);*/
        share = (ImageView) findViewById(R.id.share_id);
        intent = getIntent();
        pa = intent.getStringExtra("str");
       // Log.i("pa", "------_________________________" + pa);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

         loadWeb();
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StrategyActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
       /*  free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StrategyActivity.this, FreedesignActivity.class);
                startActivity(intent);
            }
        });*/

    }

    private void loadWeb() {
        WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
     webView.loadUrl(pa);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

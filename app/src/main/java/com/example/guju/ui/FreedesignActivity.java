package com.example.guju.ui;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.guju.R;
import com.example.guju.url.Urlan;

public class FreedesignActivity extends Activity {
    private WebView webFreedesign;
    private ImageView back;
    private ImageView load;
    private AnimationDrawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_freedesign);
        initView();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        loadWeb();
    }

    private void initView() {
        webFreedesign = ((WebView) findViewById(R.id.webview_freedesign_id));
        back = ((ImageView) findViewById(R.id.back_freedesign_id));
        load = ((ImageView)findViewById(R.id.img_load_id));
        load.setBackgroundResource(R.drawable.load);
        drawable = (AnimationDrawable) load.getBackground();
        drawable.start();

    }

    private void loadWeb() {

        WebSettings settings=webFreedesign.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webFreedesign.setWebChromeClient(new WebChromeClient());
        webFreedesign.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                load.setVisibility(View.GONE);
            }
        });
        webFreedesign.loadUrl(Urlan.freeDesinPath);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webFreedesign.canGoBack()) {
            webFreedesign.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}

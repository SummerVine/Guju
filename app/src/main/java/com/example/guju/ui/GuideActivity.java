package com.example.guju.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.guju.R;
import com.example.guju.adapter.GuideAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by green on 2016/7/4.
 */
public class GuideActivity extends Activity {
    private SharedPreferences share;
    private Intent intent;
    private ViewPager viewPager;
    private List<ImageView> imageViews=new ArrayList<ImageView>();
    //第一次登陆导引界面准备数据源
    private int[] imgs={R.mipmap.guju_guide1,R.mipmap.guju_guide2,R.mipmap.guju_guide3,R.mipmap.guju_guide4};
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==100) {
                intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        share=getSharedPreferences("first", Context.MODE_PRIVATE);
        if(share.getBoolean("isFirst",true)){
            //第一次登录
            setContentView(R.layout.activity_guide);
            aboutViewPager();
            SharedPreferences.Editor editor=share.edit();
            editor.putBoolean("isFirst",false);
            editor.commit();
        }else {

            //第二次登录
            setContentView(R.layout.activity_guide1);
            image = ((ImageView) findViewById(R.id.image_id));
            image.setImageResource(R.mipmap.guju_splash);
            Message message=Message.obtain();
            handler.sendEmptyMessageDelayed(100,2000);
        }

    }




    public void aboutViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager_id);

        for (int i = 0; i <imgs.length ; i++) {
            ImageView view=new ImageView(this);
            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setImageResource(imgs[i]);
            if (i==imgs.length-1){
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent=new Intent(GuideActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
            }
            imageViews.add(view);
        }

        viewPager.setAdapter(new GuideAdapter(imageViews));


    }


}


package com.example.guju.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guju.R;

/**
 * Created by green on 2016/7/8.
 */
public class PictureLibraryActivity extends MainActivity{

    private ImageView iv_start_btn_back_id;
    private ImageView iv_start_share_id;
    private ImageView iv_love_hollow;
    private TextView tv_love_id;
    private ImageView iv_raiders_btn_massage_id;
    private TextView tv_raiders_btn_massage_id;
    private ImageView iv_umeng_xp_hl_indicator_bg;
    private TextView tv_design_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_next);
        initWidge();
    }

    private void initWidge() {
        iv_start_btn_back_id = ((ImageView) findViewById(R.id.iv_start_btn_back_id));
        iv_start_share_id = ((ImageView) findViewById(R.id.iv_start_share_id));
        iv_love_hollow = ((ImageView) findViewById(R.id.iv_love_hollow));
        tv_love_id = ((TextView) findViewById(R.id.tv_love_id));
        iv_raiders_btn_massage_id = ((ImageView) findViewById(R.id.iv_raiders_btn_massage_id));
        tv_raiders_btn_massage_id = ((TextView) findViewById(R.id.tv_raiders_btn_massage_id));
        iv_umeng_xp_hl_indicator_bg = ((ImageView) findViewById(R.id.iv_umeng_xp_hl_indicator_bg));
        tv_design_id = ((TextView) findViewById(R.id.tv_design_id));

    }
}

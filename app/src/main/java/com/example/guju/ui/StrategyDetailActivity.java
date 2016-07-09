package com.example.guju.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guju.R;
import com.example.guju.bean.Designers;

import java.util.ArrayList;
import java.util.List;

public class StrategyDetailActivity  extends AppCompatActivity {
    private CollapsingToolbarLayout collapsing;
    private ImageView headIcon;
    private TextView info;
    private TabLayout tabLayout;
    private ViewPager content;
    private List<View> tabs=new ArrayList<>();
    private String[] subTabs2={"案例","攻略","灵感集","分享"};
    private List<String> subTabs1=new ArrayList<>();
    private TextView count;
    private TextView sort;
    private String name;
    private String userImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trategy);
        initData();
        initView();
        initTabs();
    }

    private void initData() {
        Intent intent=new Intent();
        Bundle bundle=intent.getExtras();
        name = (String) bundle.get("userName");
        userImg = (String) bundle.get("userImage");
        subTabs1.add((String) bundle.get("projectNum"));
        subTabs1.add((String) bundle.get("strategyNum"));
        subTabs1.add((String) bundle.get("ideabookNum"));
        subTabs1.add((String) bundle.get("photoNum"));

    }

    private void initTabs() {
       tabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i <4 ; i++) {
            View view =View.inflate(this,R.layout.item_tabs,null);
            count = ((TextView) view.findViewById(R.id.count_stategytab_id));
            sort = ((TextView) view.findViewById(R.id.sort_stategy_id));
            Designers.ProfessionalsBean dp=new Designers.ProfessionalsBean();
            count.setText(subTabs1.get(i));
            sort.setText(subTabs2[i]);
            tabLayout.addTab(tabLayout.newTab().setCustomView(view));
        }

    }

    private void initView() {
        collapsing = ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar));
        headIcon = ((ImageView) findViewById(R.id.headicon_stategy_id));
        info = ((TextView) findViewById(R.id.info_stategy_id));
        tabLayout = ((TabLayout) findViewById(R.id.tabs_stategy_id));
        content = ((ViewPager) findViewById(R.id.viewpager_stategy_id));
        collapsing.setTitle(name);
        collapsing.setStatusBarScrimColor(Color.rgb(2,68,144));
    }
}

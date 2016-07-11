package com.example.guju.ui;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guju.R;
import com.example.guju.adapter.MyPagerAdapter;
import com.example.guju.bean.Designers;
import com.example.guju.fragment.CaseFragment;
import com.example.guju.fragment.ShareFragment;
import com.example.guju.fragment.StrGridFragment;
import com.example.guju.fragment.StraFragment;

import java.util.ArrayList;
import java.util.List;

public class StrategyDetailActivity extends FragmentActivity {
    private CollapsingToolbarLayout collapsing;
    private ImageView headIcon;
    private TextView info;
    private TabLayout tabLayout;
    private ViewPager content;
    private String[] tabs = {"案例", "攻略", "灵感集", "分享"};
    private List<String> subTabs = new ArrayList<>();
    private List<Fragment> framents = new ArrayList<>();
    private TextView count;
    private TextView sort;
    private String name;
    private String userImg;
    private int currentPosition=0;
    private ImageView imgBg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trategy);
        initData();
        initView();
        aboutContent();
        setTabs();
        initEven();
    }

    private void initEven() {
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                TabLayout.Tab tab1=tabLayout.getTabAt(currentPosition);
                changeTab(tab1,currentPosition);
             int position= tab.getPosition();
              changeTab(tab,position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

public void changeTab(TabLayout.Tab tab,int position){
    View view=tab.getCustomView();
    TextView countTab = ((TextView) view.findViewById(R.id.count_stategytab_id));
    TextView  sortTab = ((TextView) view.findViewById(R.id.sort_stategy_id));
    if(currentPosition==position){
        countTab.setTextColor(Color.rgb(160,160,160));
        sortTab.setTextColor(Color.rgb(160,160,160));
   }else {
        countTab.setTextColor(Color.rgb(0,67,110));
        sortTab.setTextColor(Color.rgb(0,67,110));
        content.setCurrentItem(position);
        currentPosition=position;
    }

}
    private void aboutContent() {
        Bundle bundle=new Bundle();
        bundle.putString("name",name);
        CaseFragment caseFragment = new CaseFragment();
        caseFragment.setArguments(bundle);
        StraFragment stategyFragment = new StraFragment();
        stategyFragment.setArguments(bundle);
        StrGridFragment inspiraFragment = new StrGridFragment();
        inspiraFragment.setArguments(bundle);
        ShareFragment shareFragment = new ShareFragment();
        shareFragment.setArguments(bundle);
        framents.add(caseFragment);
        framents.add(stategyFragment);
        framents.add(inspiraFragment);
        framents.add(shareFragment);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), framents, tabs);
        content.setAdapter(adapter);
       tabLayout.setupWithViewPager(content);
    }

    private void initData() {
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        name = (String) bundle.get("userName");
        userImg = (String) bundle.get("userImage");
        subTabs.add((String) bundle.get("projectNum"));
        subTabs.add((String) bundle.get("strategyNum"));
        subTabs.add((String) bundle.get("ideabookNum"));
        subTabs.add((String) bundle.get("photoNum"));

    }

    private void setTabs() {
        for (int i = 0; i < 4; i++) {
    //        tabLayout.addTab(tabLayout.newTab());
            tabLayout.getTabAt(i).setCustomView(getTabView(i));
        }

    }

    public View getTabView(int i) {
        View view = View.inflate(this, R.layout.item_tabs, null);
        count = ((TextView) view.findViewById(R.id.count_stategytab_id));
        sort = ((TextView) view.findViewById(R.id.sort_stategy_id));
        Designers.ProfessionalsBean dp = new Designers.ProfessionalsBean();
        count.setText(subTabs.get(i));
        sort.setText(tabs[i]);
        if(i==0){
            count.setTextColor(Color.rgb(0,67,110));
            sort.setTextColor(Color.rgb(0,67,110));
            content.setCurrentItem(0);
        }
        return view;
    }

    private void initView() {
        collapsing = ((CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar));
        imgBg = ((ImageView) findViewById(R.id.img_bg_id));
        headIcon = ((ImageView) findViewById(R.id.headicon_stategy_id));
        info = ((TextView) findViewById(R.id.info_stategy_id));
        tabLayout = ((TabLayout) findViewById(R.id.tabs_stategy_id));
        content = ((ViewPager) findViewById(R.id.viewpager_stategy_id));
        collapsing.setTitle(name);
        collapsing.setCollapsedTitleTextColor(Color.BLUE);
        Glide.with(this).load(userImg).into(imgBg);
        Glide.with(this).load(userImg).transform(new GlideCircleTransform(this)).crossFade().into(headIcon);

    }
}

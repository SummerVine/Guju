package com.example.guju.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.guju.R;
import com.example.guju.fragment.anliFrament;
import com.example.guju.fragment.fenxingFrament;
import com.example.guju.fragment.gongluFrament;
import com.example.guju.fragment.lingganFrament;

import java.util.LinkedList;
import java.util.List;

public class StrategyDetailActivity  extends AppCompatActivity {
    private String[] titles;
    private List<Fragment> fragments;
    private ViewPager vp_id;
    private TabLayout tabs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strategyactivity_main);

        vp_id= (ViewPager) findViewById(R.id.viewpager_id);
        tabs= (TabLayout) findViewById(R.id.tabs);
aboutTabLayout();
        aboutViewPager();
    }

    private void aboutViewPager() {
       fragments=new LinkedList<>();
        Fragment anli=new anliFrament();
        Fragment gonglu=new gongluFrament();
        Fragment linggan=new lingganFrament();
        Fragment fenxing=new fenxingFrament();
        fragments.add(anli);
        fragments.add(linggan);
        fragments.add(gonglu);
        fragments.add(fenxing);
        FragmentPagerAdapter adapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        };
        vp_id.setAdapter(adapter);
    }

    private void aboutTabLayout() {
        titles=new String[]{"案例","攻略","灵感集","分享"};
        tabs.setupWithViewPager(vp_id);
    }

}

package com.example.guju.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
   private List<Fragment> data;
    private  String[] tabs;
    public MyPagerAdapter(FragmentManager fm, List<Fragment> data,String[] tabs) {
        super(fm);
        this.data = data;
        this.tabs=tabs;
    }

    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

}

package com.example.guju.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public class StrategyViewPagerAdapter extends PagerAdapter {
    private List<ImageView> imgData;

    public StrategyViewPagerAdapter(List<ImageView> imgData) {
        this.imgData = imgData;
    }

    @Override
    public int getCount() {
        return imgData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView img = imgData.get(position);

        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imgData.get(position));

    }
}

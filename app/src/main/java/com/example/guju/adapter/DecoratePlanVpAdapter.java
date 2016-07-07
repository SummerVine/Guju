package com.example.guju.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by liushuxin on 2016/7/5.
 */
public class DecoratePlanVpAdapter extends PagerAdapter {
    private List<ImageView> ds;
    public DecoratePlanVpAdapter(List<ImageView> ds) {
        super();
        this.ds = ds;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = ds.get(position);

        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(ds.get(position));
    }
}

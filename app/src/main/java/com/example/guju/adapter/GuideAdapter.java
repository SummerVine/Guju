package com.example.guju.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

/**
 * Created by green on 2016/7/4.
 */
public class GuideAdapter extends PagerAdapter {
    private List<ImageView> data;

    public GuideAdapter(List<ImageView> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(data.get(position));
        return data.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //    super.destroyItem(container, position, object);
        container.removeView(data.get(position));
    }
}


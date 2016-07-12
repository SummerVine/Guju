package com.example.guju.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by green on 2016/7/9.
 */
public class PicyureDetailsAdapter extends PagerAdapter{

        private List<PhotoView> imageViews;

        public  PicyureDetailsAdapter(List<PhotoView> data){
            imageViews=data;
        }

        @Override
        public int getCount() {

            return imageViews.size();
        }
        public void addAll(List<PhotoView> dd){
            imageViews.addAll(dd);
            notifyDataSetChanged();
        }
        public void clear(){
            imageViews.clear();
            notifyDataSetChanged();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view==object;
        }


        public Object instantiateItem(ViewGroup container, int position) {

            View imageview=imageViews.get(position);
            container.addView(imageview);
            return imageview;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }
    }


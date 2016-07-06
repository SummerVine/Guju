package com.example.guju.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.guju.bean.PictureFactory;

import java.util.List;

/**
 * Created by green on 2016/7/6.
 */
public class PictureAdapter extends RecyclerView.Adapter {
    private List<PictureFactory.PhotosBean> data;
    Context context;
    public PictureAdapter(List<PictureFactory.PhotosBean> data, Context context){
        this.data=data;
        this.context=context;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

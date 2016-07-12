package com.example.guju.entity;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 做个类设置间隔
 * Created by green on 2016/7/7.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration{
    private int space;
    //把space传进来
    public SpacesItemDecoration(int space) {
        this.space=space;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
        }
    }



}

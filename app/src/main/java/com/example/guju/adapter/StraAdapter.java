package com.example.guju.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guju.R;
import com.example.guju.bean.Stra;

import java.util.List;

public class StraAdapter extends MyBaseAdapter<Stra.StrategyListBean> {
    private Context context;
    public StraAdapter(List<Stra.StrategyListBean> data, Context context) {
        super(data, context);
        this.context=context;
    }

    @Override
    public View getViewItem(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view==null){
            view=getInflater().inflate(R.layout.item_stategy_stra,null);
            vh=new ViewHolder(view);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        if(getItem(i).getTitle() != null){
        vh.title.setText(getItem(i).getTitle());}
        if(getItem(i).getCategoryName() != null){
        vh.type.setText(getItem(i).getCategoryName());}
        if(getItem(i).getLikeCount()+"" != null){
        vh.collect.setText(getItem(i).getLikeCount()+"");}
        if(getItem(i).getLikeCount()+"" != null){
        vh.raider.setText(getItem(i).getHasPublish()+"");}

        Glide.with(context).load("http://img.guju.com.cn/dimages/"+getItem(i).getCovorPhotoId()+"_0_w220_h200_m0.jpg").into(vh.img);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"translationX",1000,0);
        animator.setDuration(800);
        animator.start();
        return view;
    }
    class ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView type;
        private TextView collect;
        private TextView raider;


        public ViewHolder(View view) {
            this.img = (ImageView) view.findViewById(R.id.img_str_id);
            this.title = (TextView) view.findViewById(R.id.title_str_id);
            this.type = (TextView) view.findViewById(R.id.type_str_id);
            this.collect = (TextView) view.findViewById(R.id.collect_str_id);
            this.raider = (TextView) view.findViewById(R.id.raider_str_id);

        }
    }
}

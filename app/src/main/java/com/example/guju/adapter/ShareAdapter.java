package com.example.guju.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guju.R;
import com.example.guju.bean.Share;
import com.example.guju.ui.GlideCircleTransform;

import java.util.List;

public class ShareAdapter extends MyBaseAdapter<Share.PhotosBean> {
    private Context context;
    public ShareAdapter(List<Share.PhotosBean> data, Context context) {
        super(data, context);
        this.context=context;
    }

    @Override
    public View getViewItem(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view==null){
            view=getInflater().inflate(R.layout.item_strategy_share,null);
            vh=new ViewHolder(view);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        vh.name.setText(getItem(i).getUser().getUserName());
        Log.i("tag",getItem(i).getId()+"---------id");
        Glide.with(context).load("http://img.guju.com.cn/dimages/"+getItem(i).getId()+"_0_w220_h200_m0.jpg").into(vh.imgLarge);
        Glide.with(context).load(getItem(i).getUser().getUserImage().getSmall()).transform(new GlideCircleTransform(context)).crossFade().into(vh.imgSmall);
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"translationX",1000,0);
        animator.setDuration(1000);
        animator.start();
        return view;
    }
    class ViewHolder {
        private ImageView imgLarge;
        private ImageView imgSmall;
        private TextView name;

        public ViewHolder(View view) {
            this.imgLarge = (ImageView) view.findViewById(R.id.imglarge_share_id);
            this.imgSmall = (ImageView) view.findViewById(R.id.imgsmall_share_id);
            this.name = (TextView) view.findViewById(R.id.name_share_id);

        }
    }
}

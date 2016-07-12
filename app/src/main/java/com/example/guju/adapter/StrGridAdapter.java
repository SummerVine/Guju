package com.example.guju.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guju.R;
import com.example.guju.bean.StrGrid;

import java.util.List;


public class StrGridAdapter extends MyBaseAdapter<StrGrid.IdeabooksBean> {
    private Context context;
    public StrGridAdapter(List<StrGrid.IdeabooksBean> data, Context context) {
        super(data, context);
        this.context=context;
    }

    @Override
    public View getViewItem(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view==null){
            view=getInflater().inflate(R.layout.item_strategy_grid,null);
            vh=new ViewHolder(view);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        vh.title.setText(getItem(i).getTitle());
        Glide.with(context).load("http://img.guju.com.cn/dimages/"+getItem(i).getId()+"_0_w220_h200_m0.jpg").into(vh.img);

        return view;
    }
    class ViewHolder {
        private ImageView img;
        private TextView title;


        public ViewHolder(View view) {
            this.img = (ImageView) view.findViewById(R.id.img_stragrid_id);
            this.title = (TextView) view.findViewById(R.id.title_stragrid_id);

        }
    }
}

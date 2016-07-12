package com.example.guju.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guju.R;
import com.example.guju.bean.Case;
import com.example.guju.ui.GlideCircleTransform;

import java.util.List;

public class CaseAdapter extends MyBaseAdapter<Case.ProjectsBean> {
private Context context;
    public CaseAdapter(List<Case.ProjectsBean> data, Context context) {
        super(data, context);
        this.context=context;
    }

    @Override
    public View getViewItem(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view==null){
            view=getInflater().inflate(R.layout.item_stategy_case,null);
            vh=new ViewHolder(view);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
      if(getItem(i).getTitle() != null){
          vh.title.setText(getItem(i).getTitle());
      }
          vh.style.setText(getItem(i).getStyleShow());
          vh.num.setText(getItem(i).getTypeShow());
          vh.area.setText(getItem(i).getAreaShow());
          vh.money.setText(getItem(i).getCostShow());
          Log.i("tag",getItem(i).getCoverPhoto()+"---------------");
          Glide.with(context).load("http://image.guju.com.cn/images/146/9/"+getItem(i).getCoverPhoto()+"_0_9-.jpg@1o").into(vh.imgLarge);
          Glide.with(context).load(getItem(i).getUser().getUserImage().getLarge()).transform(new GlideCircleTransform(context)).crossFade().into(vh.imgSmall);//圆形。。。。。。。。。。。。。。。。。。。。。。。。。。
        return view;
    }

    class ViewHolder {
        private ImageView imgLarge;
        private ImageView imgSmall;
        private TextView title;
        private TextView style;
        private TextView num;
        private TextView area;
        private TextView money;

        public ViewHolder(View view) {
            this.imgLarge = (ImageView) view.findViewById(R.id.case_large_id);
            this.title = (TextView) view.findViewById(R.id.case_title_id);
            this.imgSmall = (ImageView) view.findViewById(R.id.case_small_id);
            this.style = (TextView) view.findViewById(R.id.case_style_id);
            this.area = (TextView) view.findViewById(R.id.case_area_id);
            this.num = (TextView) view.findViewById(R.id.case_num_id);
            this.money = (TextView) view.findViewById(R.id.case_money_id);
        }
    }
}

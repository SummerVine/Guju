package com.example.guju.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guju.Bean.Designers;
import com.example.guju.R;

import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by Administrator on 2016/7/5.
 */
public class MyAdapter extends BaseAdapter {
    private List<Designers.ProfessionalsBean> data;
    private Context context;
    public MyAdapter(List<Designers.ProfessionalsBean> data,Context context) {

     this.context=context;
        this.data = data;

    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      ViewHolder vh=null;
        if(view==null){
            vh=new ViewHolder();
            view=View.inflate(context, R.layout.guide4_item,null);
            vh.iv_id= (ImageView) view.findViewById(R.id.iv_id);
            vh.Work_address_id= (TextView) view.findViewById(R.id.Work_address_id);
            vh.sex_id= (TextView) view.findViewById(R.id.sex_id);
            vh.address_id= (TextView) view.findViewById(R.id.address_id);
            vh.et_eg_id= (TextView) view.findViewById(R.id.et_eg_id);
            vh.et_share_id= (TextView) view.findViewById(R.id.et_share_id);
            vh.et_work_id= (TextView) view.findViewById(R.id.et_work_id);
            view.setTag(vh);

        }else{
            vh= (ViewHolder) view.getTag();

        }
      Uri url= (Uri.parse(data.get(i).getUserImage().getSmall()));
        Picasso.with(context).load(url).into(vh.iv_id);
        vh.et_work_id.setText(data.get(i).getProductLikeNum());
        vh.address_id.setText(data.get(i).getProvince());
        vh.et_eg_id.setText(data.get(i).getProjectNum());
        vh.et_share_id.setText(data.get(i).getPhotoNum());
        vh.sex_id.setText(data.get(i).getSex());
        vh.Work_address_id.setText(data.get(i).getUserName());
        return view;
    }
    public class ViewHolder{


        private ImageView iv_id;
        private TextView Work_address_id;
        private TextView sex_id;
        private TextView address_id;
        private TextView et_eg_id;
        private TextView et_work_id;
        private TextView et_share_id;
    }




}

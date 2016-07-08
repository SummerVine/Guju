package com.example.guju.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guju.R;
import com.example.guju.bean.Designers;

import java.util.List;



/**
 * Created by Administrator on 2016/7/5.
 */
public class MyAdapter extends BaseAdapter {
    private List<Designers.ProfessionalsBean> data;
    private Context context;
    public MyAdapter(List<Designers.ProfessionalsBean> data, Context context) {

        this.context=context;
        this.data = data;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
      ViewHolder vh=null;
        if(view==null){
            vh=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.guide4_item,null);
            vh.iv_id= (ImageView) view.findViewById(R.id.iv_id);
            vh.UserName_id= (TextView) view.findViewById(R.id.UserName_id);
            vh.sex_id= (TextView) view.findViewById(R.id.sex_id);
            vh.provice_id= (TextView) view.findViewById(R.id.provice_id);
            vh.projectNum_id= (TextView) view.findViewById(R.id.projectNum_id);
            vh.PhotoNum_id= (TextView) view.findViewById(R.id.PhotoNum_id);
            vh.producrLikeNum_id= (TextView) view.findViewById(R.id.producrLikeNum_id);
            view.setTag(vh);

        }else{
            vh= (ViewHolder) view.getTag();
        }
        Uri url= (Uri.parse(data.get(i).getUserImage().getLarge()));
        Glide.with(context).load(url).into(vh.iv_id);
        vh.UserName_id.setText((CharSequence) data.get(i).getUserName());
        vh.PhotoNum_id.setText(data.get(i).getPhotoNum()+"");

        vh.producrLikeNum_id.setText(data.get(i).getProductLikeNum()+"");
        vh.projectNum_id.setText(data.get(i).getProjectNum()+"");
        vh.sex_id.setText(data.get(i).getSex()+"");
        vh.provice_id.setText((CharSequence) data.get(i).getProvince());
        return view;
    }


    public void addAll(List<Designers.ProfessionalsBean> dd){
        data.addAll(dd);
        notifyDataSetChanged();
    }

    public class ViewHolder{
        private ImageView iv_id;
        private TextView UserName_id ;
        private TextView provice_id;
        private TextView sex_id;
        private TextView projectNum_id;
        private TextView PhotoNum_id;
        private TextView producrLikeNum_id;
    }





}

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
import com.example.guju.entity.StrategyEntity;
import com.example.guju.utils.GetDetailDataCallBack;

import java.util.List;

/**
 * Created by Administrpator on 2016/7/9.
 */
public class MyStrategyAdapter extends BaseAdapter  {
    private List<StrategyEntity.StrategyListBean> ds;
    private Context context;
private GetDetailDataCallBack callback;
    private String pathDetail;

    public MyStrategyAdapter(List<StrategyEntity.StrategyListBean> ds, Context context,GetDetailDataCallBack callback) {
        this.ds = ds;
        this.context = context;
this.callback=callback;
    }

    @Override
    public int getCount() {
        return ds.size();
    }

    @Override
    public Object getItem(int i) {
        return ds.get(i);
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
            view= LayoutInflater.from(context).inflate(R.layout.zhuangxiu_item,null);
            vh.tv_explain= (TextView) view.findViewById(R.id.tv_explain_id);
            vh.tv_typee= (TextView) view.findViewById(R.id.tv_typee_id);


            vh.tv_count= (TextView) view.findViewById(R.id.tv_count_id);

            vh.tv_good= (TextView) view.findViewById(R.id.tv_good);
            vh.iv_tupian= (ImageView) view.findViewById(R.id.iv_tupian_id);
            view.setTag(vh);

        }else{
            vh= (ViewHolder) view.getTag();
        }


        pathDetail = "http://m.guju.com.cn/release/views/gonglue/?id="+ds.get(i).getId();
        callback.getAllData(pathDetail);

        String path="http://img.guju.com.cn/dimages/"+ds.get(i).getCovorPhotoId()+"_0_w220_h200_m0.jpg";
        Uri url= (Uri.parse(path));
       // Uri urlDetail= (Uri.parse(pathDetail));
        Glide.with(context).load(url).into(vh.iv_tupian);
        vh.tv_explain.setText((CharSequence) ds.get(i).getTitle());
        vh.tv_typee.setText(ds.get(i).getCategoryName()+"");

        vh.tv_count.setText(ds.get(i).getTuijian()+"");
        vh.tv_good.setText(ds.get(i).getType()+"");

        return view;
    }
    public void callData(){

    }
    public void addAll(List<StrategyEntity.StrategyListBean> dd){
        ds.addAll(dd);
        notifyDataSetChanged();
    }


    public class ViewHolder{
        private TextView tv_explain;
        private TextView tv_typee;

        private TextView tv_count;

        private TextView tv_good;
        private ImageView iv_tupian;

    }
}

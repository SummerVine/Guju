package com.example.guju.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.guju.bean.Exemples;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8.
 */
public class MyAdapteranli  extends BaseAdapter {
    private Context context;
    private List<Exemples> data;
    public  MyAdapteranli(List<Exemples> data,Context context){
         this.data=data;
         this.context=context;
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
        return null;
    }

    public void addAll(Exemples exemple) {
        data.add(exemple);
        notifyDataSetChanged();
    }

    class  ViewHolder{

    }
}

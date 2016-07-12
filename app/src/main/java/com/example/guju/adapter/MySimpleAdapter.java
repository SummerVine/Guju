package com.example.guju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guju.R;


/**
 * Created by Green
 */
public class MySimpleAdapter extends BaseAdapter {
    private Context context;
    private String space[] ={"不限", "玄关", "客厅", "餐厅",
        "卧室", "厨房", "书房","儿童房","卫生间","阳台"};
    private String style[] = {"不限", "现代", "简约", "日式",
            "地中海", "欧式", "中式","新古典","宜家","田园","小资","美式"};
    private String color[] = {"不限", "原木色", "红色", "紫色",
            "春色", "黑白", "黄色","粉色","蓝色","绿色"};
    public MySimpleAdapter(Context context){
        this.context = context;
    }
    public int getCount() {
        return space.length;
    }

    public Object getItem(int item) {
        return item;
    }

    public long getItemId(int id) {
        return id;
    }
    ViewHolder holder = null;
    //创建View方法
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){



            convertView = LayoutInflater.from(context).inflate(R.layout.picture_gridview_left_item, parent,false);
           //ViewHolder赋值
            holder = new ViewHolder(convertView);

           //把holder类作为tag赋值
           convertView.setTag(holder);
       }else {
           holder = (ViewHolder) convertView.getTag();
        }
        //给控件进行赋值
        if (space[position]!=null) {
            holder.tv_item_id.setText(space[position]);
        }


        //把convertView返回//
        return convertView;

}
public static class ViewHolder {
    private TextView tv_item_id;
    public ViewHolder (View convertView){

        tv_item_id = ((TextView) convertView.findViewById(R.id.tv_item_id));

    }
}
}
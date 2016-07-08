package com.example.guju.adapter;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.guju.R;

/**
 * Created by liushuxin on 2016/7/7.
 */
public class DecorateGridViewAdapter extends BaseAdapter {
    private String[] datas;
    private Activity activity;
    private int clickTemp = -1;

    //标识选择的Item

    public void setSeclection(int position) {

        clickTemp = position;

    }

    public DecorateGridViewAdapter(String[] datas, Activity activity) {
        this.activity=activity;
        this.datas=datas;
    }

    @Override
    public int getCount() {
        return datas.length;
    }

    @Override
    public Object getItem(int i) {
        return datas[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
     final  TextView tv;
                   if(view == null){
                          tv = new TextView(activity);
                        }
                   else {
                            tv = (TextView)view;
                        }

                    tv.setSingleLine(true);
        if (clickTemp == i) {

            tv.setBackgroundResource(R.drawable.abc_menu_hardkey_panel_holo_dark);
        } else {

            tv.setBackgroundResource(R.drawable.abc_menu_hardkey_panel_holo_light);
        }
                    tv.setGravity(Gravity.CENTER);
                    tv.setText(datas[i]);
                    return tv;

    }
}

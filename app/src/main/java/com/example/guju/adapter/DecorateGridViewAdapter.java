package com.example.guju.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
     final  TextView gv_itm_btn;
                   if(view == null){
                       gv_itm_btn = new TextView(activity);
                        }
                   else {
                       gv_itm_btn = (TextView) view;
                        }

                 gv_itm_btn.setSingleLine(true);
        if (clickTemp == i) {
            gv_itm_btn.setTextColor(Color.BLUE);
        } else {

            //gv_itm_btn.setBackgroundResource(R.drawable.abc_menu_hardkey_panel_holo_light);
        }
        gv_itm_btn.setGravity(Gravity.CENTER);
        gv_itm_btn.setText(datas[i]);
                    return gv_itm_btn;

    }


}

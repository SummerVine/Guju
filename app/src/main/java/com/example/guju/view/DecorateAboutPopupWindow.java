package com.example.guju.view;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.guju.R;
import com.example.guju.adapter.DecorateGridViewAdapter;
import com.example.guju.datas.DecorateDatasUtils;

/**
 * Created by liushuxin on 2016/7/7.
 */
public class DecorateAboutPopupWindow {
    private static GridView gridView;
    private static PopupWindow popupWindow;
    private static DecorateGridViewAdapter adapter;

    public static PopupWindow showPopupWindow(Activity context, final View parent, final TextView contentview) {
        //加载布局
      final View  view = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.decorate_popup_activity, null);
        //找到布局的控件
        gridView = (GridView) view.findViewById(R.id.decorate_gv_id);
        //设置适配器
        adapter = new DecorateGridViewAdapter(DecorateDatasUtils.popArea,context);
        gridView.setAdapter(adapter);
        // 实例化popupWindow
        popupWindow = new PopupWindow(view, 1050,750);
        //控制键盘是否可以获得焦点
        popupWindow.setFocusable(true);
        //设置popupWindow弹出窗体的背景
        WindowManager manager=(WindowManager)context.getSystemService(context.getApplication().WINDOW_SERVICE);
        //获取xoff
        int xpos=manager.getDefaultDisplay().getWidth()/2- popupWindow.getWidth()/2;
        int ypos=manager.getDefaultDisplay().getWidth()/2- popupWindow.getWidth()/2;
        //xoff,yoff基于anchor的左下角进行偏移。
//        DisplayMetrics display=new DisplayMetrics();
//        int widthPixels = display.widthPixels;
//        popupWindow.setWidth(widthPixels);
        popupWindow.showAsDropDown(parent,xpos,0);
        //监听
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // arg1是当前item的view，通过它可以获得该项中的各个组件。
            //arg2是当前item的ID。这个id根据你在适配器中的写法可以自己定义。
            //arg3是当前的item在listView中的相对位置！
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //关闭popupWindow
                adapter.setSeclection(arg2);
                adapter.notifyDataSetChanged();
                popupWindow.dismiss();
                ImageView iv = (ImageView)parent;
                contentview.setTextColor(Color.BLACK);
                contentview.setText(DecorateDatasUtils.popArea[arg2]);
                iv.setImageResource(R.drawable.guju_down);
        }
        });
        return popupWindow;
    }
}

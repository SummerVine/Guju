package com.example.guju.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.guju.R;
import com.example.guju.adapter.StrategyAdapter;
import com.example.guju.entity.Strategy;

import java.util.List;


public class  StrategyFragment extends BaseFragment{
    private Activity activity;
    private FragmentActivity fragmentActivity;
    private List<Strategy> strategyData;
    private RecyclerView rv;
    private StrategyAdapter rvdapter;
    private ViewPager vp_id;
    private LinearLayout ll_container_id;
    private GridLayout layout;
    private ListView listView;
    private PopupWindow popupWindow;
    private String title[] = { "装修灵感", "验房须知", "装修合同", "装修预算", "装修风水","装修设计","装修要点"};
    private  ImageView iv_qian_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        super.onCreate(savedInstanceState);
        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getActivity().setContentView(R.layout.activity_main);

        iv_qian_id.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                showPopupWindow(iv_qian_id);
            }
        });

    }
    public void showPopupWindow(View parent) {
        //加载布局
        layout = (GridLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.zhuangxiu_popup_qian_item, null);
        //找到布局的控件
        listView = (ListView) layout.findViewById(R.id.lv_dialog_id);
        //设置适配器
        listView.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.zhuangxiu_qian_item, R.id.btn_qian_id, title));
        // 实例化popupWindow
        popupWindow = new PopupWindow(layout, 500,500);
        //控制键盘是否可以获得焦点
        popupWindow.setFocusable(true);
        //设置popupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null,""));
       WindowManager manager=(WindowManager)getActivity().getSystemService(getActivity().getApplication().WINDOW_SERVICE);

        @SuppressWarnings("deprecation")
        //获取xoff
        int xpos=manager.getDefaultDisplay().getWidth()/2-popupWindow.getWidth()/2;
        //xoff,yoff基于anchor的左下角进行偏移。
        popupWindow.showAsDropDown(parent,xpos, 0);
        //监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //关闭popupWindow
                popupWindow.dismiss();
                popupWindow = null;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhuangxiu, null);
        rv = (RecyclerView)view.findViewById(R.id.rv_id);
        rv.setLayoutManager(new LinearLayoutManager(activity));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        aboutRecyclerView();
        super.onActivityCreated(savedInstanceState);
    }
    public View getView() {
        View view = activity.getLayoutInflater().inflate(R.layout.headview, null);
        view.findViewById(R.id.dh_tv_id);
        ll_container_id = (LinearLayout)view.findViewById(R.id.ll_container_id);
        view.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "click View", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void aboutRecyclerView() {
        initAdapter();
        //设置适配器
        rvdapter.addHeaderView(getView());
        rvdapter.addFooterView(getView());
        rv.setAdapter(rvdapter);

    }

    private void initAdapter() {
        rvdapter = new StrategyAdapter(20);
        rvdapter.openLoadAnimation();
        rv.setAdapter(rvdapter);
        rvdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity, "" + Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }

}

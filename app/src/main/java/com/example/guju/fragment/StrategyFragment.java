package com.example.guju.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.guju.R;
import com.example.guju.adapter.DecoratePlanRvAdapter;
import com.example.guju.adapter.StrategyAdapter;
import com.example.guju.entity.Strategy;

import java.util.List;


public class StrategyFragment extends Fragment{
    private Activity activity;
    private List<Strategy> strategyData;
    private RecyclerView rv;
    private StrategyAdapter rvdapter;
    private ViewPager vp_id;
    private LinearLayout ll_container_id;
    private PopupWindow pw1;
    private PopupWindow pw2;
    private PopupWindow pw3;
    private View contentView1;
    private View contentView2;
    private View contentView3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        activity = getActivity();
        super.onCreate(savedInstanceState);
        initPopubWindow();
    }

    private void initPopubWindow() {
        contentView1 = View.inflate(getContext(), R.layout.zhuangxiu_popup_qian_item, null);
        contentView2 = View.inflate(getContext(), R.layout.zhuangxiu_popup_zhong_item, null);
        contentView3 = View.inflate(getContext(), R.layout.zhuangxiu_popup_hou_item, null);

        pw1 = new PopupWindow(contentView1, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT);
        pw2 = new PopupWindow(contentView2, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT);
        pw3 = new PopupWindow(contentView3, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT);

        // true if the popup should receive outside touch events, false
        // otherwise
        // 含义：弹出窗口是否会收到外面的触碰事件。
        pw1.setOutsideTouchable(true);
        pw2.setOutsideTouchable(true);
        pw3.setOutsideTouchable(true);

        // 3、添加监听器(给窗口中的视图控件添加)


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhuangxiu_item, null);
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
        View view = activity.getLayoutInflater().inflate(R.layout.zhuangxiu_main, null);
        vp_id = (ViewPager) view.findViewById(R.id.vp_id);
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
        rvdapter = new StrategyAdapter(strategyData);
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

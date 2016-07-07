package com.example.guju.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.guju.R;
import com.example.guju.adapter.DecoratePlanRvAdapter;
import com.example.guju.adapter.DecoratePlanVpAdapter;
import com.example.guju.entity.DecoratePlan;

import java.util.List;

/**
 * Created by liushuxin on 2016/7/5.
 */
public class DecoratePlanFragment extends BaseFragment {

    private ViewPager vp_id;
    private LinearLayout ll_container_id;
    private RecyclerView mRecyclerView;
    private List<ImageView> ds;
    private DecoratePlanVpAdapter viewPagerAdapter;
    private Activity activity;
    private DecoratePlan decorate;
    private DecoratePlanRvAdapter mQuickAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_decorateplan_fragment, null);
//        vp_id = (ViewPager) findViewById(R.id.vp_id);
//        ll_container_id = (LinearLayout)findViewById(R.id.ll_container_id);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.rv_id);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
//        //关于ViewPager
//        aboutViewPager();
//        //关于小圆点
//        aboutDots();
        //关于RecyclerView
        aboutRecyclerView();
        super.onActivityCreated(savedInstanceState);
    }

    //关于RecyclerView
    private void aboutRecyclerView() {
        initAdapter();
        mQuickAdapter.addHeaderView(getView());
        mQuickAdapter.addFooterView(getView());
        mRecyclerView.setAdapter(mQuickAdapter);
        //设置适配器

    }
    public View getView() {
        View view = activity.getLayoutInflater().inflate(R.layout.headview, null);
        view.findViewById(R.id.dh_tv_id);
        view.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "click View", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
    private void initAdapter() {
        mQuickAdapter = new DecoratePlanRvAdapter(10);
        mQuickAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mQuickAdapter);
        mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(activity, "" + Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }
//    //关于小远点的点击事件
//    private final class MyOnClickListener implements View.OnClickListener {
//
//        @Override
//        public void onClick(View view) {
//            vp_id.setCurrentItem((Integer) view.getTag());
//        }
//    }
//
//    //关于小圆点
//    private void aboutDots() {
//        MyOnClickListener listener = new MyOnClickListener();
//        for (int i = 0; i < ds.size(); i++) {
//            ImageView dot = new ImageView(activity);
//            dot.setTag(i);
//            dot.setEnabled(true);
//            dot.setOnClickListener(listener);
//            ll_container_id.addView(dot);
//        }
//    }
//
//    //关于ViewPager
//    private void aboutViewPager() {
//        //从Recycler中找到ViewPager
//        ViewPager rv_vp = (ViewPager) rv_id.getChildAt(0);
//        //准备数据源
//        ds = new LinkedList<>();
//        for (int i = 0; i <= 4; i++) {
//            ImageView iv = new ImageView(activity);
//            iv.setImageResource(R.drawable.icon_id);
//            ds.add(iv);
//        }
//        //准备适配器
//        viewPagerAdapter = new DecoratePlanVpAdapter(ds);
//
//        //设置适配器
//        vp_id.setAdapter(viewPagerAdapter);
//        vp_id.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                for (int i = 0; i < ll_container_id.getChildCount(); i++) {
//                    View view = ll_container_id.getChildAt(i);
//                }
//                ll_container_id.getChildAt(position).setEnabled(false);
//            }
//        });
//        handler.sendEmptyMessageDelayed(0, 2000);
//    }
//
//    private boolean isContinue = true;
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            // ①判断ViewPager中目前播放的图片的索引值
//            if (vp_id.getCurrentItem() > vp_id.getChildCount()) {
//                // a）若索引值>最大的索引值，将当前的ViewPager的索引值设置为0
//                vp_id.setCurrentItem(0);
//            } else {
//                // b）否则，将当前的ViewPager的索引值=当前索引值+1
//                vp_id.setCurrentItem(vp_id.getCurrentItem() + 1);
//            }
//            if (isContinue) {
//                handler.sendEmptyMessageDelayed(0, 2000);
//            }
//        }
//    };
//
//    @Override
//    public void onDestroy() {
//        isContinue = false;
//        super.onDestroy();
//    }
}

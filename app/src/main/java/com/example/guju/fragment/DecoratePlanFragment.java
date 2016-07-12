package com.example.guju.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.guju.R;
import com.example.guju.adapter.DecoratePlanRvAdapter;
import com.example.guju.adapter.DecoratePlanVpAdapter;
import com.example.guju.bean.DecorateResultBean;
import com.example.guju.entity.DecoratePlan;
import com.example.guju.ui.DecorateRvDetailsActivity;
import com.example.guju.ui.DecorateVPDetailsActivity;
import com.example.guju.utils.DecorateServerinterface;
import com.example.guju.utils.OkHttp3Utils;
import com.example.guju.view.DecorateAboutPopupWindow;

import java.util.LinkedList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    private DecoratePlanRvAdapter mQuickAdapter;
    private boolean isContinue = true;
    private ImageView decorete_iv_area_id;
    private TextView decorate_area_id;
    private PopupWindow popupWindow;
    private Call<DecorateResultBean> call_result;
    private DecorateServerinterface myInterface;
    private  List<DecoratePlan> resultlistDatas;
    private  DecoratePlan decorate;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // ①判断ViewPager中目前播放的图片的索引值
            if (vp_id.getCurrentItem() > vp_id.getChildCount()) {
                // a）若索引值>最大的索引值，将当前的ViewPager的索引值设置为0
                vp_id.setCurrentItem(0);
                ll_container_id.getChildAt(4).setEnabled(true);
                ll_container_id.getChildAt(0).setEnabled(false);
            } else {
                // b）否则，将当前的ViewPager的索引值=当前索引值+1
                int currentIndex = vp_id.getCurrentItem();
                vp_id.setCurrentItem(currentIndex + 1);
                ll_container_id.getChildAt(currentIndex).setEnabled(true);
                ll_container_id.getChildAt(currentIndex + 1).setEnabled(false);
            }
            if (isContinue) {
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        initRetrofit();
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.decorate_fragment_activity, null);
        decorete_iv_area_id = (ImageView) view.findViewById(R.id.decorete_iv_area_id);
        decorate_area_id = (TextView) view.findViewById(R.id.decorate_area_id);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_id);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        decorete_iv_area_id.setTag(0);
        decorete_iv_area_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((Integer) decorete_iv_area_id.getTag() == 0) {
                    decorete_iv_area_id.setImageResource(R.drawable.guju_up);
                    decorate_area_id.setTextColor(Color.GREEN);
                    popupWindow = DecorateAboutPopupWindow.showPopupWindow(activity, decorete_iv_area_id, decorate_area_id);
                    decorete_iv_area_id.setTag(1);
                }
                //TODO刘书新
                else {
                    decorete_iv_area_id.setImageResource(R.drawable.guju_down);
                    decorate_area_id.setTextColor(Color.BLACK);
                    decorete_iv_area_id.setTag(0);
                    popupWindow.dismiss();
                }
            }
        });

        return view;
    }

    private void initRetrofit() {
        OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://api.guju.com.cn").client(client).addConverterFactory(GsonConverterFactory.create()).build();
        myInterface=retrofit.create(DecorateServerinterface.class);
        call_result = myInterface.getInfoList();
        call_result.enqueue(new Callback<DecorateResultBean>() {
           @Override
           public void onResponse(Call<DecorateResultBean> call, Response<DecorateResultBean> response) {
               resultlistDatas = response.body().getDecorateDatas();
           }

           @Override
           public void onFailure(Call<DecorateResultBean> call, Throwable t) {
           }
       });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // 关于RecyclerView
        aboutRecyclerView();
        super.onActivityCreated(savedInstanceState);
    }

    //关于RecyclerView
    private void aboutRecyclerView() {
        initAdapter();
        mQuickAdapter.addHeaderView(getHeaderView());
        //mQuickAdapter.addFooterView(getView());
        mRecyclerView.setAdapter(mQuickAdapter);
    }

    //生成RecyclerView头
    public View getHeaderView() {
        View view = LayoutInflater.from(activity).inflate(R.layout.decorate_viewpager_activity, null);
        vp_id = (ViewPager) view.findViewById(R.id.decorate_vp_id);
        ll_container_id = (LinearLayout) view.findViewById(R.id.ll_container_id);
        //关于ViewPager
        aboutViewPager();
        //关于小圆点
        aboutDots();
        view.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, DecorateVPDetailsActivity.class));
            }
        });
        return view;
    }

    //RecyclerView适配器的初始化
    private void initAdapter() {
        mQuickAdapter = new DecoratePlanRvAdapter(R.layout.decorate_rvitem_activity,resultlistDatas);
        mQuickAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mQuickAdapter);
        mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                activity.startActivity(new Intent(activity, DecorateRvDetailsActivity.class));
            }
        });
    }

    //关于ViewPager中小圆点的点击事件
    private final class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            vp_id.setCurrentItem((Integer) view.getTag());
        }
    }

    //关于ViewPager中的小圆点
    private void aboutDots() {
        MyOnClickListener listener = new MyOnClickListener();
        for (int i = 0; i < ds.size(); i++) {
            ImageView dot = new ImageView(activity);
            dot.setTag(i);
            dot.setEnabled(true);
            dot.setOnClickListener(listener);
            dot.setImageResource(R.drawable.decorate_plan_dot_selector);
            ll_container_id.addView(dot);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(16, 16);
            lp.setMargins(10, 10, 10, 10);
            dot.setLayoutParams(lp);
        }
        ll_container_id.getChildAt(0).setEnabled(false);
    }

    //关于ViewPager
    private void aboutViewPager() {
        //准备数据源
        ds = new LinkedList<>();
        for (int i = 0; i <= 4; i++) {
            ImageView iv = new ImageView(activity);
            iv.setImageResource(R.drawable.decorate_loading_image);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            ds.add(iv);
        }
        //准备适配器
        viewPagerAdapter = new DecoratePlanVpAdapter(ds);

        //设置适配器
        vp_id.setAdapter(viewPagerAdapter);
        vp_id.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ll_container_id.getChildCount(); i++) {
                    View view = ll_container_id.getChildAt(i);
                    view.setEnabled(true);
                }
                ll_container_id.getChildAt(position).setEnabled(false);
            }
        });
        handler.sendEmptyMessageDelayed(0, 3000);
    }


    @Override
    public void onDestroy() {
        isContinue = false;
        super.onDestroy();
    }
}

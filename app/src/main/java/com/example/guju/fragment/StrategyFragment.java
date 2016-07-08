package com.example.guju.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.guju.R;

import com.example.guju.adapter.StrategyAdapter;
import com.example.guju.adapter.StrategyViewPagerAdapter;
import com.example.guju.entity.Strategy;
import com.example.guju.ui.DecorateRvDetailsActivity;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


import java.util.LinkedList;
import java.util.List;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;


public class  StrategyFragment extends BaseFragment{
    private Activity activity;
    private FragmentActivity fragmentActivity;
    private List<ImageView> imgData;
    private RecyclerView zhuangxiu_rv_id;
    private StrategyAdapter rvdapter;
    private ViewPager zhuangxiu_vp_id;
    private LinearLayout ll_scontainer_id;
    private SwipeRefreshLayout rl;
    private LinearLayout layout;
    private GridView gridView;
    private PopupWindow popupWindow;
    private String title1[] = { "装修灵感", "验房须知", "装修合同", "装修预算", "装修风水","装修设计","装修要点"};
    private String title2[] = { "装修选材", "建材安装", "装修合同", "拆改工程", "水电工程","防水工程","泥瓦工程","水木工程","油漆工程"};
    private String title3[] = { "装修污染", "装修验收", "家具护理", "家居配饰", "家电家私"};
    private boolean isContinue=true;
    private ImageView iv_qian,iv_zhong,iv_hou;
    private TextView tv_qian_id;
    private StrategyViewPagerAdapter vpAdapter;



    public void showPopupWindow(View parent,String[] title) {
        //加载布局
        layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.zhuangxiu_popup_qian_item, null);
        //找到布局的控件
        gridView = (GridView) layout.findViewById(R.id.lv_dialog_id);
        //设置适配器

        gridView.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.zhuangxiu_qian_item, R.id.btn_qian_id, title));
        // 实例化popupWindow
        popupWindow = new PopupWindow(layout, 1000,700);
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
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //关闭popupWindow
                popupWindow.dismiss();
                popupWindow = null;
            }
        });
    }
    private Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // ①判断ViewPager中目前播放的图片的索引值
            if (zhuangxiu_vp_id.getCurrentItem() > zhuangxiu_vp_id.getChildCount()) {
                // a）若索引值>最大的索引值，将当前的ViewPager的索引值设置为0
                zhuangxiu_vp_id.setCurrentItem(0);
                ll_scontainer_id.getChildAt(2).setEnabled(true);
                ll_scontainer_id.getChildAt(0).setEnabled(false);
            } else {
                // b）否则，将当前的ViewPager的索引值=当前索引值+1
                int currentIndex=zhuangxiu_vp_id.getCurrentItem();
                zhuangxiu_vp_id.setCurrentItem(currentIndex+ 1);
                ll_scontainer_id.getChildAt(currentIndex).setEnabled(true);
                ll_scontainer_id.getChildAt(currentIndex + 1).setEnabled(false);
            }
            if (isContinue) {
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhuangxiu, null);
        zhuangxiu_rv_id = (RecyclerView)view.findViewById(R.id.zhuangxiu_rv_id);
        zhuangxiu_rv_id.setLayoutManager(new LinearLayoutManager(activity));
        iv_qian = (ImageView)view.findViewById(R.id.iv_qian_id);
        iv_zhong = (ImageView)view.findViewById(R.id.iv_zhong_id);
        iv_hou = (ImageView)view.findViewById(R.id.iv_hou_id);
        iv_qian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(iv_qian,title1);
            }
        });
        iv_zhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(iv_zhong,title2);
            }
        });
        iv_hou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(iv_hou,title3);
            }
        });



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
       aboutRecyclerView();
        super.onActivityCreated(savedInstanceState);
    }


    public View getView() {
        View view=LayoutInflater.from(activity).inflate(R.layout.zhuangxiu_main, null);
        zhuangxiu_vp_id = (ViewPager) view.findViewById(R.id.zhuangxiu_vp_id);
        ll_scontainer_id = (LinearLayout) view.findViewById(R.id.ll_scontainer_id);
        //关于ViewPager的操作
        aboutViewPager();
        //关于小圆点的操作
        aboutLitterDots();
        view.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.startActivity(new Intent(activity, DecorateRvDetailsActivity.class));
            }
        });
        return view;
       /* View view = activity.getLayoutInflater().inflate(R.layout.headview, null);
       // view.findViewById(R.id.dh_tv_id);
        //ll_container_id = (LinearLayout)view.findViewById(R.id.ll_container_id);
        view.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "click View", Toast.LENGTH_LONG).show();
            }
        });
        return view;*/
    }
private final class MyOnClickListener implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        zhuangxiu_vp_id.setCurrentItem((Integer) view.getTag());
    }
}
    private void aboutLitterDots() {
        MyOnClickListener mylistener = new MyOnClickListener();
        for (int i = 0; i < imgData.size(); i++) {
            ImageView dots = new ImageView(getActivity());
            dots.setTag(i);
            dots.setEnabled(true);
            dots.setOnClickListener(mylistener);
            dots.setImageResource(R.drawable.decorate_plan_dot_selector);
            ll_scontainer_id.addView(dots);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(16,16);
            params.setMargins(10,10,10,10);
            dots.setLayoutParams(params);
        }
        ll_scontainer_id.getChildAt(0).setEnabled(false);

    }



    private void aboutRecyclerView() {
        initAdapter();
        //设置适配器
        rvdapter.addHeaderView(getView());
        zhuangxiu_rv_id.setAdapter(rvdapter);


    }


    private void initAdapter() {
        rvdapter = new StrategyAdapter(10);
        rvdapter.openLoadAnimation();
        zhuangxiu_rv_id.setAdapter(rvdapter);
        rvdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                activity.startActivity(new Intent(activity, DecorateRvDetailsActivity.class));
            }
        });
    }
    private void aboutViewPager() {
        //准备数据源
        imgData = new LinkedList<>();
        for (int i = 0; i <=4; i++) {
            ImageView iv = new ImageView(activity);
            iv.setImageResource(R.drawable.decorate_loading_image);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            imgData.add(iv);
        }
        //准备适配器
        vpAdapter = new StrategyViewPagerAdapter(imgData);

        //设置适配器
        zhuangxiu_vp_id.setAdapter(vpAdapter);
        zhuangxiu_vp_id.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < ll_scontainer_id.getChildCount(); i++) {
                    View view = ll_scontainer_id.getChildAt(i);
                    view.setEnabled(true);
                }
                ll_scontainer_id.getChildAt(position).setEnabled(false);
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

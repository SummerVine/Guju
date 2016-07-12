package com.example.guju.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.guju.R;
import com.example.guju.adapter.DecoratePlanRvAdapter;
import com.example.guju.adapter.DecoratePlanVpAdapter;
import com.example.guju.datas.DecorateDatasUtils;
import com.example.guju.entity.AA;
import com.example.guju.entity.DecoratePlan;
import com.example.guju.ui.DecorateVP1DetailsActivity;
import com.example.guju.ui.DecorateVP2Activity;
import com.example.guju.ui.StrategyActivity;
import com.example.guju.utils.DecorateServerinterface;
import com.example.guju.utils.OkHttp3Utils;
import com.google.gson.internal.LinkedTreeMap;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liushuxin on 2016/7/5.
 */
public class DecoratePlanFragment extends BaseFragment  implements SwipeRefreshLayout.OnRefreshListener {

    private ViewPager vp_id;
    private LinearLayout ll_container_id;
    private RecyclerView mRecyclerView;
    private List<ImageView> ds;
    private DecoratePlanVpAdapter viewPagerAdapter;
    private Activity activity;
    private DecoratePlanRvAdapter mQuickAdapter;
    private boolean isContinue = true;
    private ImageView decorete_iv_area_id;
    private Call<AA> call_result;
    private DecorateServerinterface myInterface;
    private List<DecoratePlan> datas1;
    private List<DecoratePlan> datas2;
    private List<DecoratePlan> datas3;
    private List<AA.ProjectsBean> projects;
    private Retrofit retrofit;
    private static final int TOTAL_COUNTER = 100;
    private static final int PAGE_SIZE = 10;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Map<String, Integer> map;
    private Map<String, Integer> map1;
    private ImageView decorete_iv_cost_id;
    private ImageView decorete_iv_type_id;
    private ImageView decorete_iv_style_id;
    private PopupWindow areaPopup;
    private PopupWindow stylePopup;
    private PopupWindow typePopup;
    private PopupWindow costPopup;
    private View costPopupview;
    private View typePopupview;
    private View stylePopupview;
    private View areaPopupview;
    private int areaUrlCode;
    private int costUrlCode;
    private int typeUrlCode;
    private int styleUrlCode;
    private DecorateGridViewAdapter areagridViewAdapter;
    private DecorateGridViewAdapter  typegridViewAdapter;
    private DecorateGridViewAdapter  costgridViewAdapter;
    private DecorateGridViewAdapter  stylegridViewAdapter;
    private int areaSelected;
    private int costSelected;
    private int typeSelected;
    private int styleSelected;
    private TextView tv_area;
    private TextView tv_cost;
    private TextView tv_type;
    private TextView tv_style;

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
        datas2 = new LinkedList<>();
        datas1 = new LinkedList<>();
        datas3 = new LinkedList<>();
        //请求网络资源
        OkHttpClient client = OkHttp3Utils.getOkHttpSingletonInstance();
        retrofit = new Retrofit.Builder().
                baseUrl("http://api.guju.com.cn/v2/").client(client).addConverterFactory(GsonConverterFactory.create()).build();
        map = new LinkedTreeMap<>();
        map1=new LinkedTreeMap<>();
       // map.put("start",0);
        map.put("area", 0);
        map.put("cost", 0);
        map.put("type", 0);
        map.put("style",0);
        map1.put("area", 0);
        map1.put("cost", 404);
        map1.put("type", 207);
        map1.put("style",105);
        datas1 = initRetrofit(datas1, map);
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.decorate_fragment_activity, null);
        tv_area = (TextView) view.findViewById(R.id.decorate_area_id);
        tv_cost = (TextView) view.findViewById(R.id.decorate_pluge_id);
        tv_type = (TextView) view.findViewById(R.id.decorate_type_id);
        tv_style = (TextView) view.findViewById(R.id.decorate_style_id);
        decorete_iv_area_id = (ImageView) view.findViewById(R.id.decorete_iv_area_id);
        decorete_iv_cost_id = (ImageView) view.findViewById(R.id.decorete_iv_cost_id);
        decorete_iv_type_id = (ImageView) view.findViewById(R.id.decorete_iv_type_id);
        decorete_iv_style_id = (ImageView) view.findViewById(R.id.decorete_iv_style_id);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_id);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
        newPopupWindow();
        decorete_iv_area_id.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (areaPopup.isShowing()) {
                    tv_area.setTextColor(Color.BLACK);
                    areaPopup.dismiss();
                    decorete_iv_area_id.setImageResource(R.drawable.guju_down);
                } else if (!areaPopup.isShowing()) {
                    tv_area.setTextColor(Color.rgb(135,206,235));
                    newPopupWindow();
                    areaPopup.showAsDropDown(tv_area, 0, 10);
                    decorete_iv_area_id.setImageResource(R.drawable.guju_up);
                }
            }
        });
        decorete_iv_cost_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (costPopup.isShowing()) {
                    tv_cost.setTextColor(Color.BLACK);
                    costPopup.dismiss();
                    decorete_iv_cost_id.setImageResource(R.drawable.guju_down);
                } else if (!costPopup.isShowing()) {
                    tv_cost.setTextColor(Color.rgb(135,206,235));
                    newPopupWindow();
                    costPopup.showAsDropDown(tv_area, 0, 10);
                    decorete_iv_cost_id.setImageResource(R.drawable.guju_up);
                }
            }
        });
        decorete_iv_type_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (typePopup.isShowing()) {
                    tv_type.setTextColor(Color.BLACK);
                    decorete_iv_type_id.setImageResource(R.drawable.guju_down);
                    typePopup.dismiss();
                } else if (!typePopup.isShowing()) {
                    tv_type.setTextColor(Color.rgb(135,206,235));
                    newPopupWindow();
                    typePopup.showAsDropDown(tv_area, 0, 10);
                    decorete_iv_type_id.setImageResource(R.drawable.guju_up);
                }
            }
        });
        decorete_iv_style_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( stylePopup.isShowing()) {
                    tv_style.setTextColor(Color.BLACK);
                    stylePopup.dismiss();
                    decorete_iv_style_id.setImageResource(R.drawable.guju_down);
                } else if ( !stylePopup.isShowing()) {
                    tv_style.setTextColor(Color.rgb(135,206,235));
                    newPopupWindow();
                    stylePopup.showAsDropDown(tv_area, 0, 10);
                    decorete_iv_style_id.setImageResource(R.drawable.guju_up);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        aboutRecyclerView();
        super.onActivityCreated(savedInstanceState);
    }

    //生成PopupWindow
    public void newPopupWindow() {

        //获得Popupwindow界面
        costPopupview = LayoutInflater.from(activity).inflate(R.layout.decorate_popup_activity, null);
        typePopupview = LayoutInflater.from(activity).inflate(R.layout.decorate_popup_activity, null);
        stylePopupview =LayoutInflater.from(activity).inflate(R.layout.decorate_popup_activity, null);
        areaPopupview = LayoutInflater.from(activity).inflate(R.layout.decorate_popup_activity, null);
        //生成面积的PoppuWindow
        areagridViewAdapter = new DecorateGridViewAdapter(DecorateDatasUtils.popArea,areaSelected);
        final GridView areagridView = (GridView) areaPopupview.findViewById(R.id.decorate_gv_id);
        areagridView.setAdapter(areagridViewAdapter);
        //生成户型的PoppuWindow
        typegridViewAdapter = new DecorateGridViewAdapter(DecorateDatasUtils.popType,typeSelected);
        final GridView typegridView = (GridView) typePopupview.findViewById(R.id.decorate_gv_id);
        typegridView.setAdapter(typegridViewAdapter);
        //生成预算的PoppuWindow
        costgridViewAdapter = new DecorateGridViewAdapter(DecorateDatasUtils.popCost,costSelected);
        final GridView costgridView = (GridView) costPopupview.findViewById(R.id.decorate_gv_id);
        costgridView.setAdapter(costgridViewAdapter);
        //生成风格的PoppuWindow
       stylegridViewAdapter = new DecorateGridViewAdapter(DecorateDatasUtils.popStyle,styleSelected);
        final GridView stylegridView = (GridView) stylePopupview.findViewById(R.id.decorate_gv_id);
        stylegridView.setAdapter(stylegridViewAdapter);
        // 实例化popupWindow
        areaPopup = new PopupWindow(areaPopupview, 1050, 750);;
        costPopup = new PopupWindow(costPopupview, 1050, 750);
        typePopup = new PopupWindow(typePopupview, 1050, 750);
        stylePopup =new PopupWindow(stylePopupview, 1050, 750);
        //控制键盘是否可以获得焦点
        areaPopup.setFocusable(false);
        areaPopup.setOutsideTouchable(false);
        costPopup.setFocusable(false);
        costPopup.setOutsideTouchable(false);
        typePopup.setFocusable(false);
        typePopup.setOutsideTouchable(false);
        stylePopup.setFocusable(false);
        stylePopup.setOutsideTouchable(false);
        //设置popupWindow弹出窗体的背景
        areaPopup.setBackgroundDrawable(new BitmapDrawable());
        costPopup.setBackgroundDrawable(new BitmapDrawable());
        typePopup.setBackgroundDrawable(new BitmapDrawable());
        stylePopup.setBackgroundDrawable(new BitmapDrawable());
        //监听
        areagridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // arg1是当前item的view，通过它可以获得该项中的各个组件。
            //arg2是当前item的ID。这个id根据你在适配器中的写法可以自己定义。
            //arg3是当前的item在listView中的相对位置！
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //关闭popupWindow
                    areaUrlCode=DecorateDatasUtils.obtainPopArea().get(DecorateDatasUtils.popArea[arg2]);
                    itemefresh();
                    areagridView.setSelection(arg2);
                    areagridViewAdapter.notifyDataSetChanged();
                    areaPopup.dismiss();
                    //tv_area.setTextColor(Color.BLACK);
                if (arg2==0){
                tv_area.setText("面积");
                }else{
                    tv_area.setText(DecorateDatasUtils.popArea[arg2]);
                }
                tv_area.setTextColor(Color.BLACK);
                decorete_iv_area_id.setImageResource(R.drawable.guju_down);
                    areaSelected=arg2;

            }
        });
        costgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // arg1是当前item的view，通过它可以获得该项中的各个组件。
            //arg2是当前item的ID。这个id根据你在适配器中的写法可以自己定义。
            //arg3是当前的item在listView中的相对位置！
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //关闭popupWindow
                costUrlCode=DecorateDatasUtils.obtainPopCost().get(DecorateDatasUtils.popCost[arg2]);
                itemefresh();
                costgridView.setSelection(arg2);
                costgridViewAdapter.notifyDataSetChanged();
                costPopup.dismiss();
                //tv_cost.setTextColor(Color.BLACK);
                if (arg2==0){
                    tv_cost.setText("预算");
                }else {
                    tv_cost.setText(DecorateDatasUtils.popCost[arg2]);
                }
                tv_cost.setTextColor(Color.BLACK);
                decorete_iv_cost_id.setImageResource(R.drawable.guju_down);
                costSelected=arg2;
            }
        });
        typegridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // arg1是当前item的view，通过它可以获得该项中的各个组件。
            //arg2是当前item的ID。这个id根据你在适配器中的写法可以自己定义。
            //arg3是当前的item在listView中的相对位置！
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //关闭popupWindow
                typeUrlCode=DecorateDatasUtils.obtainPopType().get(DecorateDatasUtils.popType[arg2]);
                itemefresh();
                typegridView.setSelection(arg2);
                typegridViewAdapter.notifyDataSetChanged();
                typePopup.dismiss();
                //tv_type.setTextColor(Color.BLACK);
                if (arg2==0){
                    tv_type.setText("户型");
                }else {
                    tv_type.setText(DecorateDatasUtils.popType[arg2]);
                }
                tv_type.setTextColor(Color.BLACK);
                decorete_iv_type_id.setImageResource(R.drawable.guju_down);
                typeSelected=arg2;
            }
        });
       stylegridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // arg1是当前item的view，通过它可以获得该项中的各个组件。
            //arg2是当前item的ID。这个id根据你在适配器中的写法可以自己定义。
            //arg3是当前的item在listView中的相对位置！
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //关闭popupWindow
                styleUrlCode=DecorateDatasUtils.obtainPopStyle().get(DecorateDatasUtils.popStyle[arg2]);
                itemefresh();
                stylegridView.setSelection(arg2);
                stylegridViewAdapter.notifyDataSetChanged();
                stylePopup.dismiss();
                //tv_style.setTextColor(Color.BLACK);
                if (arg2==0){
                    tv_style.setText("风格");
                }else{

                    tv_style.setText(DecorateDatasUtils.popStyle[arg2]);
                }
                tv_style.setTextColor(Color.BLACK);
                decorete_iv_style_id.setImageResource(R.drawable.guju_down);
                styleSelected=arg2;
            }
        });

    }


    //点击popup每一项刷新数据
    public void itemefresh() {
        map1.clear();
        map1.put("area", areaUrlCode);
        map1.put("cost", costUrlCode);
        map1.put("type", typeUrlCode);
        map1.put("style", styleUrlCode);
        datas3 = initRetrofit(datas3, map1);
        mQuickAdapter.notifyDataSetChanged();
        mQuickAdapter.setNewData(datas3);
        mQuickAdapter.openLoadMore(PAGE_SIZE, true);
        mCurrentCounter = PAGE_SIZE;
        datas1.clear();
    }

    //返回请求的数据
    private List<DecoratePlan> initRetrofit(final List<DecoratePlan> returnData, final Map<String, Integer> maps) {
        myInterface = retrofit.create(DecorateServerinterface.class);
        call_result = myInterface.getInfoList(maps);
        call_result.enqueue(new Callback<AA>() {
            @Override
            public void onResponse(Call<AA> call, Response<AA> response) {
                projects = response.body().getProjects();
                for (int i = 0; i < projects.size(); i++) {
                    AA.ProjectsBean plan = projects.get(i);
                    DecoratePlan data = new DecoratePlan();
                    data.setBuildingName(plan.getBuildingName());
                    data.setTitle("  " + plan.getTitle());
                    data.setCoverPhoto(plan.getCoverPhoto());
                    data.setUserSmallImage(plan.getUser().getUserImage().getLarge());
                    Toast.makeText(activity, "作者小图", Toast.LENGTH_LONG);
                    data.setDetails("   " + plan.getStyleShow() + " . " + plan.getTypeShow() + " . " + plan.getAreaShow() + " . " + plan.getCostShow());
                    returnData.add(data);
                    mQuickAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onFailure(Call<AA> call, Throwable t) {
                Toast.makeText(activity, "请求网络失败", Toast.LENGTH_LONG).show();
                if (maps==map1){
                    Toast.makeText(activity, "暂时木有您要查找的房源,不过您还有更多选择呦！！", Toast.LENGTH_LONG).show();
                }
            }
        });
        return returnData;
    }


    //关于RecyclerView
    private void aboutRecyclerView() {
        initAdapter();
        addHeadView();
        mRecyclerView.setAdapter(mQuickAdapter);
    }

    //生成RecyclerView头
    public void addHeadView() {
        View view = LayoutInflater.from(activity).inflate(R.layout.decorate_viewpager_activity, null);
        vp_id = (ViewPager) view.findViewById(R.id.decorate_vp_id);
        ll_container_id = (LinearLayout) view.findViewById(R.id.ll_container_id);
        //关于ViewPager
        aboutViewPager();
        //关于小圆点
        aboutDots();
        view.setLayoutParams(new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mQuickAdapter.addHeaderView(view);
    }

    //RecyclerView适配器的初始化
    private void initAdapter() {
        //datas1.addAll(DataServer.getSampleData(6));
        mQuickAdapter = new DecoratePlanRvAdapter(datas1);
        mCurrentCounter = datas1.size();
        mQuickAdapter.openLoadAnimation();
        mRecyclerView.setAdapter(mQuickAdapter);
        mQuickAdapter.openLoadMore(PAGE_SIZE, true);
        mQuickAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.post(new Runnable() {

                    private View footview;

                    @Override
                    public void run() {
                        if (mCurrentCounter > 20) {
                            mQuickAdapter.notifyDataChangedAfterLoadMore(false);
                            footview = LayoutInflater.from(activity).inflate(R.layout.decorate_not_loading, null);
                            mQuickAdapter.addFooterView(footview);
                        } else {
                            mQuickAdapter.notifyDataChangedAfterLoadMore(datas1, true);
                            mCurrentCounter = mQuickAdapter.getItemCount();
                        }
                    }
                });

            }
        });
        mQuickAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //TODO
                activity.startActivity(new Intent(activity, StrategyActivity.class));
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
            Glide.with(activity).load(DecorateDatasUtils.viewpagerUrl[i]).into(iv);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            MyViewPagerListener listener = new MyViewPagerListener(i);
            iv.setOnClickListener(listener);

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

    public final class MyViewPagerListener implements View.OnClickListener {
        private int i;

        public MyViewPagerListener(int i) {
            this.i = i;
        }

        @Override
        public void onClick(View view) {
            switch (i) {
                case 0:
                    activity.startActivity(new Intent(activity, DecorateVP1DetailsActivity.class));
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                    activity.startActivity(new Intent(activity, DecorateVP2Activity.class));
                    break;
            }
        }
    }

    //SwipRefresh加载更多
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                datas2 = initRetrofit(datas2, map1);
                datas1.addAll(datas2);
                mQuickAdapter.setNewData(datas1);
                mQuickAdapter.openLoadMore(PAGE_SIZE, true);
                mCurrentCounter = PAGE_SIZE;
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, delayMillis);
    }

    @Override
    public void onDestroy() {
        isContinue = false;
        super.onDestroy();
    }

    //关于Grideview的适配器
    public class DecorateGridViewAdapter extends BaseAdapter {
        private String[] adapterDatas;
        private int currentSelected;

        public DecorateGridViewAdapter(String[] datas,int currentSelected) {
            this.adapterDatas = datas;
            this.currentSelected=currentSelected;
        }

        @Override
        public int getCount() {
            return adapterDatas.length;
        }

        @Override
        public Object getItem(int i) {

            return adapterDatas[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final TextView tvItem;
            if (view == null) {
                tvItem = new TextView(activity);
            } else {
                tvItem = (TextView) view;
            }
            tvItem.setSingleLine(true);
            if (currentSelected == i) {
                tvItem.setTextColor(Color.rgb(135,206,235));
            }
            tvItem.setGravity(Gravity.CENTER);
            tvItem.setText(adapterDatas[i]);
            tvItem.setTextSize(20);
            return tvItem;
        }

    }

}

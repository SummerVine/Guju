package com.example.guju.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.guju.MyApp;
import com.example.guju.R;
import com.example.guju.adapter.MySimpleAdapter;
import com.example.guju.adapter.PictureAdapter;
import com.example.guju.bean.Edithor;
import com.example.guju.bean.PictureFactory;
import com.example.guju.entity.SpacesItemDecoration;
import com.example.guju.ui.PictureLibraryActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by green on 2016/7/7.
 */
public class PictureLibraryFragment extends BaseFragment {
    private View leftpopupview;
    private View centerpopupview;
    private View rightpopupview;
    private View pictureview;
    private RecyclerView rv_id;
    private int page=0;
    private PictureAdapter pictureadapter;
    private SwipeRefreshLayout swiperefreshlayout_id;
    private List<PictureFactory.PhotosBean> photos;
    private PictureFactory picturefactory;
    private List<String> lists;
    private PopupWindow pw_left;
    private PopupWindow pw_center;
    private PopupWindow pw_right;
    private List<ImageView> imageViews;
    private LinearLayoutManager linearmanager;
    private CheckBox iv_color_id;
    private CheckBox iv_space_id;
    private CheckBox iv_style_id;
    private boolean isSlidingToLast = false;
    private boolean Flag=false;
    private TextView tv_color_id;
    private TextView tv_space_id;
    private TextView tv_style_id;
    private TextView tv_title_id;
    private PopupWindow popupWindow;
    private LinearLayout layout;
    private TextView textView =null;
    private List<Map<String,String>> data;
    private WindowManager manager;
    private int space[] ={0 , 12 , 21 , 11 , 9  ,18  ,16 , 17 , 165 , 25 };
    private int style[] = {0 , 142 , 149 , 177 , 164 , 143 , 160  ,156  ,157 , 151, 152};
    private int color[] = {0 , 139  ,158, 144 , 167 , 150,  152 , 145 , 153 , 164 , 161, 154};
    private View  leftview;
    private GridView gv_space_id;

    private int getMaxElem(int[] arr) {
        int size = arr.length;
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            if (arr[i]>maxVal)
                maxVal = arr[i];
        }
        return maxVal;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获取主界面
        pictureview = inflater.inflate(R.layout.activity_picture, container, false);
        //获得Popupwindow界面
        leftview=inflater.inflate(R.layout.picture_gridview_left,null);
        gv_space_id = ((GridView) leftview.findViewById(R.id.gv_space_id));

        MySimpleAdapter adapter = new MySimpleAdapter(getActivity().getApplicationContext());
        gv_space_id.setAdapter(adapter);

        leftpopupview = inflater.inflate(R.layout.picture_library_popup_left_item, container, false);
        centerpopupview = inflater.inflate(R.layout.picture_library_center_item, container, false);
        rightpopupview = inflater.inflate(R.layout.picture_library_popup_right_item, container, false);
        //界面初始化
        initWidtge();
        //三个Popupwindow获取
        pw_left = new PopupWindow(leftview, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT);
        //设置背景
        pw_left.setBackgroundDrawable(new BitmapDrawable(null,""));
        pw_center = new PopupWindow(centerpopupview, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT);
        pw_center.setBackgroundDrawable(new BitmapDrawable(null,""));
        pw_right = new PopupWindow(rightpopupview, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT);
        pw_right.setBackgroundDrawable(new BitmapDrawable(null,""));
        manager = (WindowManager) getActivity().getSystemService(getActivity().getApplication().WINDOW_SERVICE);

        //设置适配器
        SetAdapter();
        //界面个性化定制
        SettingByGreen();
        //获得RecyclerView布局管理器
        SetRecyclerManager();
        //下载数据源 并把数据放到适配器里
        DownloadData();
        //下拉刷新
        DownPullRefresh();
        //上拉加载新的一页
        UpDownloadNextPage();
        //给下一界面传值
        SendMessage();
        //给Popup设置监听
        AddListener();
        //tian
        AddGradView();
        return pictureview;
    }

    private void AddGradView() {
        gv_space_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
                pictureadapter.clear();//清除原有数据
                if (page>15) {
                    Toast.makeText(getActivity().getApplicationContext(),"最后一页啦!!!",Toast.LENGTH_LONG).show();
                }else{
                    String StartNum=""+page*15;
                    String url="http://api.guju.com.cn/v2/photo/flow?sortId=2&count=15&start="+StartNum+"&space="+""+space[i]+"&style=0&color=0&userId=0&";
                    StringRequest stringrequest = new StringRequest(url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            picturefactory = gson.fromJson(response, PictureFactory.class);
                            pictureadapter.addAll(picturefactory.getPhotos());
                            photos.addAll(picturefactory.getPhotos());
                        }
                    }, null);

                    stringrequest.setTag("xp");
                    MyApp.getApp().getRequestQueue().add(stringrequest);
                }
                pw_left.dismiss();
            }
        });
    }




    private void SettingByGreen() {
        //设置动画
//        rv_id.setItemAnimator(new DefaultItemAnimator());

        // mRecyclerView.addItemDecoration();//设置分割线
        //把LayoutManager设置成StaggeredGridLayoutManager(参数一 列数;参数二 方向)
        rv_id.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));//设置RecyclerView布局管理器为2列垂直排布
        //设置间隔 这里单独做个类
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        rv_id.addItemDecoration(decoration);
    }

    //设置适配器
    private void SetAdapter() {
        photos=new ArrayList<>();
        pictureadapter = new PictureAdapter(new ArrayList<PictureFactory.PhotosBean>(),getActivity().getApplicationContext());
        rv_id.setAdapter(pictureadapter);

    }

    //获得RecyclerView布局管理器
    private void SetRecyclerManager() {
        linearmanager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearmanager.setOrientation(LinearLayoutManager.VERTICAL);
        linearmanager.setReverseLayout(true);

    }


    private void AddListener() {
        //popup界面获得 设置左边监听事件
        iv_space_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //PopupWindow视图的获取

                //判断
                if(!b&&pw_left.isShowing()){
                    pw_left.dismiss();
                }else if(b&&!pw_left.isShowing()){
                    pw_left.showAsDropDown(iv_space_id,0,10);
                }

            }
        });




        //popup界面获得 设置中间监听事件
        iv_style_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //

                if (!b&&pw_center.isShowing()) {
                    pw_center.dismiss();

                } else if(b&&!pw_center.isShowing()){
                    pw_center.showAsDropDown(iv_style_id,-20,10);

                }

            }
        });
        //popup界面获得 设置右边监听事件
        iv_color_id.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {//b=false


                if (!b&&pw_right.isShowing()) {

                    pw_right.dismiss();


                } else if(b&&!pw_right.isShowing()){

                    pw_right.showAsDropDown(iv_color_id,-30,10);

                }

            }
        });

    }
      //给下一界面传值
    private void SendMessage() {

        pictureadapter.setOnItemClickListener(new PictureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View itemView) {

                Toast.makeText(getActivity(), "点击了：" + position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(), PictureLibraryActivity.class);
                //用handler传值
                List<Edithor> editors=new ArrayList<>();

                for(int i=0;i<photos.size();i++){
                    int LikeNum=photos.get(i).getLikeNum();
                    int CommentNum=photos.get(i).getCommentNum();
                    String id=""+photos.get(i).getId();

                    String photolink=photos.get(i).getUser().getUserImage().getLarge();

                    String name=photos.get(i).getUser().getUserName();

                    Boolean certified=photos.get(i).getUser().isCertified();

                    int editorId=photos.get(i).getUser().getUserId();

                    Edithor edithor= new Edithor(id,photolink,name,certified,editorId,LikeNum,CommentNum);

                    editors.add(edithor);
                }

                Message message=PictureLibraryActivity.handler.obtainMessage();
                message.obj=editors;
                message.what=position;
                PictureLibraryActivity.handler.sendMessage(message);
                startActivity(intent);
            }
//            //长按删除
//            @Override
//            public void ItemLongClickListener(int postion, View itemview) {
//
//                lists.remove(postion);
//                pictureadapter.notifyItemRemoved(postion);
//            }
        });


    }
    //下载数据
    private void DownloadData() {
        if (page>15) {
            Toast.makeText(getActivity().getApplicationContext(),"最后一页啦!!!",Toast.LENGTH_LONG).show();
        }else{
            String StartNum=""+page*15;
            String uri="http://api.guju.com.cn/v2/photo/flow?sortId=2&count=15&start="+StartNum+"&space=0&style=0&color=0&userId=0&";
            StringRequest stringrequest = new StringRequest(uri, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    picturefactory = gson.fromJson(response, PictureFactory.class);
                    pictureadapter.addAll(picturefactory.getPhotos());
                    photos.addAll(picturefactory.getPhotos());
                }
            }, null);

            stringrequest.setTag("xp");
            MyApp.getApp().getRequestQueue().add(stringrequest);
        }
    }

    //上拉加载下一页
    private void UpDownloadNextPage() {

        rv_id.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(rv_id, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if(dy > 0){
                    //大于0表示，正在向下滚动
                    isSlidingToLast = true;
                }else{
                    //小于等于0 表示停止或向上滚动
                    isSlidingToLast = false;
                }

            }

            @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);

                    StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) recyclerView.getLayoutManager();
                    // 当不滚动时
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        //获取最后一个完全显示的ItemPosition
                        int[] lastVisiblePositions = manager.findLastVisibleItemPositions(new int[manager.getSpanCount()]);
                        int lastVisiblePos = getMaxElem(lastVisiblePositions);
                        int totalItemCount = manager.getItemCount();

                        // 判断是否滚动到底部
                        if (lastVisiblePos == (totalItemCount - 1)&& isSlidingToLast) {
                            //加载更多功能的代码
                            page++;
                            DownloadData();
                        }
                    }
                }


            });
    }
        //下拉刷新
    private void DownPullRefresh() {

        swiperefreshlayout_id.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (linearmanager.findFirstVisibleItemPosition() == 0) {
                    pictureadapter.clear();
                    DownloadData();
                    swiperefreshlayout_id.setRefreshing(false);
                }
            }
        });

    }

        private void initWidtge() {
            iv_color_id = ((CheckBox) pictureview.findViewById(R.id.iv_color_id));
            iv_space_id = ((CheckBox) pictureview.findViewById(R.id.iv_space_id));
            iv_style_id = ((CheckBox) pictureview.findViewById(R.id.iv_style_id));
            tv_color_id = ((TextView) pictureview.findViewById(R.id.tv_color_id));
            tv_space_id = ((TextView) pictureview.findViewById(R.id.tv_space_id));
            tv_style_id = ((TextView) pictureview.findViewById(R.id.tv_style_id));
            tv_title_id = ((TextView) pictureview.findViewById(R.id.tv_title_id));
            rv_id = ((RecyclerView) pictureview.findViewById(R.id.rv_id));
            swiperefreshlayout_id = ((SwipeRefreshLayout) pictureview.findViewById(R.id.swiperefreshlayout_id));
        }

    @Override
        public void onDestroy() {
            super.onDestroy();
            MyApp.getApp().getRequestQueue().cancelAll("xp");
        }
    }



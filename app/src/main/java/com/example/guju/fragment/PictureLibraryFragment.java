package com.example.guju.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.guju.MyApp;
import com.example.guju.R;
import com.example.guju.adapter.PictureAdapter;
import com.example.guju.bean.PictureFactory;
import com.example.guju.entity.SpacesItemDecoration;
import com.example.guju.url.Url;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by green on 2016/7/7.
 */
public class PictureLibraryFragment extends BaseFragment {
        private View leftpopupview;
        private View centerpopupview;
        private View rightpopupview;
        private View pictureview;
        private RecyclerView rv_id;
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

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //获取主界面
            pictureview=inflater.inflate(R.layout.activity_picture,container,false);
            //获得Popupwindow界面
            leftpopupview=inflater.inflate(R.layout.picture_library_popup_left_item,container,false);
            centerpopupview=inflater.inflate(R.layout.picture_library_center_item,container,false);
            rightpopupview=inflater.inflate(R.layout.picture_library_popup_right_item,container,false);
            //界面初始化
            initWidtge();
            pictureadapter=new PictureAdapter(new ArrayList<PictureFactory.PhotosBean>(),getActivity().getApplicationContext());
            rv_id.setAdapter(pictureadapter);

            rv_id.setItemAnimator(new DefaultItemAnimator());

            // mRecyclerView.addItemDecoration();//设置分割线
             //把LayoutManager设置成StaggeredGridLayoutManager(参数一 列数;参数二 方向)
            rv_id.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));//设置RecyclerView布局管理器为2列垂直排布
             //设置间隔 这里单独做个类
            SpacesItemDecoration decoration=new SpacesItemDecoration(16);
            rv_id.addItemDecoration(decoration);
            //设置点击监听
            pictureadapter.setOnItemClickListener(new PictureAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position, View itemView) {
                    Toast.makeText(getActivity().getApplication(), "点击了：" + position, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void ItemLongClickListener(int postion, View itemview) {
                    //长按删除
                    lists.remove(postion);
                    pictureadapter.notifyItemRemoved(postion);
                }
              });
            //获得RecyclerView布局管理器
            linearmanager=new LinearLayoutManager(getActivity().getApplicationContext());
            linearmanager.setOrientation(LinearLayoutManager.VERTICAL);
            linearmanager.setReverseLayout(true);
            //popup界面获得
            PopupViewShow();
            //下载数据
            DownloadData();
            //下拉刷新
            DownPullRefresh();

            return pictureview;
            }



       //下拉刷新
    private void DownPullRefresh() {
        swiperefreshlayout_id.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (linearmanager.findFirstVisibleItemPosition()==0) {
                    pictureadapter.clear();
                    DownPullRefresh();
                    swiperefreshlayout_id.setRefreshing(false);
                }
            }
        });
        //上拉加载下一页
//        rv_id.addOnScrollListener(new RecyclerView.OnScrollListener(){
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (linearmanager.findLastVisibleItemPosition() == pictureadapter.getItemCount()) {
//                    page++;
//                    loadListViewData();
//                }
//            }
//        });
    }



    private void PopupViewShow() {
        pw_left= new PopupWindow(leftpopupview, ViewPager.LayoutParams.MATCH_PARENT,
              ViewPager.LayoutParams.WRAP_CONTENT);
        pw_center= new PopupWindow(leftpopupview, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT);
        pw_right= new PopupWindow(leftpopupview, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.WRAP_CONTENT);
        pw_left.setOutsideTouchable(true);
        pw_center.setOutsideTouchable(true);
        pw_right.setOutsideTouchable(true);
    }


    private void DownloadData() {
            StringRequest stringrequest=new StringRequest(Url.secondUri, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Gson gson=new Gson();
                    picturefactory=gson.fromJson(response,PictureFactory.class);

//                    imageViews=new ArrayList<ImageView>();
//                    for(int i=0;i< picturefactory.getPhotos().size();i++){
//
//                        ImageView imageView = new ImageView(getActivity().getApplicationContext());
//
//                        Glide.with(getActivity().getApplicationContext()).load("http://image.guju.com.cn/images/145/9/"+picturefactory.getPhotos().get(i).getId()+"_0_9-.jpg@1o").into(imageView);
//                        imageViews.add(imageView);
//                    }
                    pictureadapter.addAll(picturefactory.getPhotos());
                }
            },null);

            stringrequest.setTag("xp");
            MyApp.getApp().getRequestQueue().add(stringrequest);
        }
        private void initWidtge() {
            pictureview.findViewById(R.id.iv_color_id);
            pictureview.findViewById(R.id.iv_space_id);
            pictureview.findViewById(R.id.iv_style_id);
            pictureview.findViewById(R.id.tv_color_id);
            pictureview.findViewById(R.id.tv_space_id);
            pictureview.findViewById(R.id.tv_style_id);
            pictureview.findViewById(R.id.tv_title_id);
            rv_id = ((RecyclerView) pictureview.findViewById(R.id.rv_id));
            swiperefreshlayout_id = ((SwipeRefreshLayout) pictureview.findViewById(R.id.swiperefreshlayout_id));
        }

    @Override
        public void onDestroy() {
            super.onDestroy();
            MyApp.getApp().getRequestQueue().cancelAll("xp");
        }
    }



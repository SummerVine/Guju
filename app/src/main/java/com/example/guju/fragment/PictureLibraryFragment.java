package com.example.guju.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.guju.R;
import com.example.guju.adapter.PictureAdapter;
import com.example.guju.bean.PictureFactory;
import com.example.guju.url.Url;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by green on 2016/7/5.
 */
public class PictureLibraryFragment extends BaseFragment{
    private View pictureview;
    private RecyclerView rv_id;
    private SwipeRefreshLayout swiperefreshlayout_id;
    private List<PictureFactory.PhotosBean> photos;

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         pictureview=inflater.inflate(R.layout.activity_picture,null);
         initWidtge();
         DownloadData();
        PictureAdapter pictureadapter=new PictureAdapter(new ArrayList<PictureFactory.PhotosBean>(),getActivity().getApplicationContext());

        return pictureview;
    }

    private void DownloadData() {
        StringRequest stringrequest=new StringRequest(Url.secondUri, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                PictureFactory picturefactory=gson.fromJson(response,PictureFactory.class);

            }
        },null);
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
}

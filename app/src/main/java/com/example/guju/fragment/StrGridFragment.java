package com.example.guju.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.guju.MyApp;
import com.example.guju.R;
import com.example.guju.adapter.StrGridAdapter;
import com.example.guju.bean.StrGrid;
import com.example.guju.ui.StrategyActivity;
import com.example.guju.url.Urlan;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;

public class StrGridFragment extends Fragment {
    private PullToRefreshGridView gridView;
    private StrGridAdapter adapter;
    private String name;
    private StringRequest request;
    private int page=0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_strgride,null);

        Bundle bundle=getArguments();
        name = (String) bundle.get("name");
        gridView = ((PullToRefreshGridView) view.findViewById(R.id.gridview_str_id));
        adapter = new StrGridAdapter(new ArrayList<StrGrid.IdeabooksBean>(),getActivity());
       gridView.setAdapter(adapter);
        loadData();
        aboutRefresh();
        gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), StrategyActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void aboutRefresh() {
        gridView.setMode(PullToRefreshBase.Mode.BOTH);
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<GridView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
                page=0;
                adapter.removeAll();
                loadData();
                gridView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<GridView> pullToRefreshBase) {
                page+=5;
                loadData();
                adapter.notifyDataSetChanged();
                gridView.onRefreshComplete();
            }
        });
    }

    private void loadData() {
        request = new StringRequest(Urlan.getStrGrid(name), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                StrGrid strGrid=gson.fromJson(response,StrGrid.class);
                adapter.addAll(strGrid.getIdeabooks());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setTag("cancel");
        MyApp.getApp().getRequestQueue().add(request);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (request!=null){
            request.cancel();
        }
    }
}

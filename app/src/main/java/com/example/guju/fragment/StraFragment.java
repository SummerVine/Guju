package com.example.guju.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.guju.MyApp;
import com.example.guju.R;
import com.example.guju.adapter.StraAdapter;
import com.example.guju.bean.Stra;
import com.example.guju.url.Urlan;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;

public class StraFragment extends Fragment {
    private PullToRefreshListView listView;
    private StraAdapter adapter;
    private String name;
    private StringRequest request;
    private int page=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_listview,null);
        Bundle bundle=getArguments();
        name = (String) bundle.get("name");
        listView = ((PullToRefreshListView) view.findViewById(R.id.list_sfragment_id));
        adapter = new StraAdapter(new ArrayList<Stra.StrategyListBean>(),getActivity());
       listView.setAdapter(adapter);
        loadData();
        aboutRefresh();
//        listView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        return view;
    }
    private void aboutRefresh() {
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page = 0;
                adapter.removeAll();
                loadData();
                listView.onRefreshComplete();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                page += 5;
                loadData();
                adapter.notifyDataSetChanged();
                listView.onRefreshComplete();
            }
        });
    }
    private void loadData() {
        request = new StringRequest(Urlan.getStra(name), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                Stra stra=gson.fromJson(response,Stra.class);
                adapter.addAll(stra.getStrategyList());
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
        if(request!=null){
            MyApp.getApp().getRequestQueue().cancelAll("cancel");
        }
    }
}

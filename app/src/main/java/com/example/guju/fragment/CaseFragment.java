package com.example.guju.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.guju.MyApp;
import com.example.guju.R;
import com.example.guju.adapter.CaseAdapter;
import com.example.guju.bean.Case;
import com.example.guju.url.Urlan;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;


public class CaseFragment extends Fragment {
    private PullToRefreshListView listView;
    private CaseAdapter adapter;
    private StringRequest request;
    private String name;
    private int page=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_listview,null);
        listView = ((PullToRefreshListView) view.findViewById(R.id.list_sfragment_id));
        listView.setEmptyView((ImageView)view.findViewById(R.id.empty_id));
        Bundle bundle=getArguments();
        name = (String) bundle.get("name");
        adapter = new CaseAdapter(new ArrayList<Case.ProjectsBean>(),getActivity());
        listView.setAdapter(adapter);
        loadData();
        aboutRefresh();
        //listView.setAdapter(new CaseAdapter());
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
                    page=0;
                 adapter.removeAll();
                 loadData();
                listView.onRefreshComplete();

         }

         @Override
         public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
             page+=5;
             loadData();
             adapter.notifyDataSetChanged();
             listView.onRefreshComplete();
         }
     });

    }

    /**
     * 记载数据
     */
    private void loadData() {
        request = new StringRequest(Urlan.getCase(name,page), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            //请求成功
                Gson gson = new Gson();
                Case aCase = gson.fromJson(response, Case.class);
                adapter.addAll(aCase.getProjects());
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

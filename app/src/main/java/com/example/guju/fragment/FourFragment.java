package com.example.guju.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.guju.MyApp;
import com.example.guju.R;
import com.example.guju.adapter.MyAdapter;
import com.example.guju.bean.Designers;
import com.example.guju.ui.CityActivity;
import com.example.guju.ui.StrategyDetailActivity;
import com.example.guju.url.commont;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class FourFragment extends  BaseFragment {

    private View view;
    private PullToRefreshListView pull_refresh_list;
    private MyAdapter  adapter;
   private List<Designers.ProfessionalsBean> data;
    private Button citySelect;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

      view=  inflater.inflate(R.layout.activity_guide4,null);
        citySelect = ((Button) view.findViewById(R.id.city_button_id));
        citySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CityActivity.class);
                startActivityForResult(intent,100);
            }
        });

        pull_refresh_list= (PullToRefreshListView) view.findViewById(R.id.pull_refresh_list);

        data=new LinkedList<>();
        initData(data);
       adapter=new MyAdapter(data,getActivity().getApplicationContext());
        pull_refresh_list.setAdapter(adapter);
          pull_refresh_list.setMode(PullToRefreshBase.Mode.BOTH);
        pull_refresh_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(
                    PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(
                    PullToRefreshBase<ListView> refreshView) {

            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==100&&resultCode==200){
            citySelect.setText(data.getStringExtra("city"));
        }
    }


    private void initData(final List<Designers.ProfessionalsBean> data) {
        StringRequest request=new StringRequest(commont.url4, new Response.Listener<String>(){


            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                Designers designers=gson.fromJson(response,Designers.class);
               data.addAll(designers.getProfessionals());
                pull_refresh_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent(getActivity(), StrategyDetailActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("userName",data.get(i).getUserName());
                        bundle.putString("userImage",data.get(i).getUserImage().getLarge());
                        bundle.putString("projectNum",data.get(i).getProjectNum()+"");
                        bundle.putString("strategyNum",data.get(i).getStrategyNum()+"");
                        bundle.putString("ideabookNum",data.get(i).getIdeabookNum()+"");
                        bundle.putString("photoNum",data.get(i).getPhotoNum()+"");
                        intent.putExtras(bundle);
                          startActivity(intent);
                    }
                });
            }
        },null);
        MyApp.getApp().getRequestQueue().add(request);
    }
}

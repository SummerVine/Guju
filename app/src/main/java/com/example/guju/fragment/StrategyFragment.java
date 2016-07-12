package com.example.guju.fragment;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
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
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.guju.MyApp;
import com.example.guju.R;
import com.example.guju.adapter.MyStrategyAdapter;
import com.example.guju.entity.StrategyEntity;
import com.example.guju.ui.StrategyActivity;
import com.example.guju.url.commont;
import com.example.guju.utils.GetDetailDataCallBack;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.LogRecord;


public class  StrategyFragment extends BaseFragment  {
    private LinearLayout layout;
    private GridView gridView;
    private View view;
    private View vi;
    private PopupWindow popupWindow;
    private String title1[] = {"装修灵感", "验房须知", "装修合同", "装修预算", "装修风水", "装修设计", "装修要点"};
    private String title2[] = {"装修选材", "建材安装", "装修合同", "拆改工程", "水电工程", "防水工程", "泥瓦工程", "水木工程", "油漆工程"};
    private String title3[] = {"装修污染", "装修验收", "家具护理", "家居配饰", "家电家私"};
    private ImageView iv_qian, iv_zhong, iv_hou;
    private TextView tv_qian_id;
    private PullToRefreshListView ptrl_id;
    List<StrategyEntity.StrategyListBean> ds;
    private MyStrategyAdapter strategyadapter;
    private GridView lv_dialog_id;
    private String path=commont.strategyUrl[3];
    private  String webPath;



    public void showPopupWindow(View parent, String[] title) {
        //加载布局
        layout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(
                R.layout.zhuangxiu_popup_qian_item, null);

        //找到布局的控件
        gridView = (GridView) layout.findViewById(R.id.lv_dialog_id);
        //设置适配器

        gridView.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.zhuangxiu_qian_item, R.id.tv_qian_id, title));
        // 实例化popupWindow 设置显示框大小
        popupWindow = new PopupWindow(layout, 1000, 700);
        //控制键盘是否可以获得焦点
        popupWindow.setFocusable(true);
        //设置popupWindow弹出窗体的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        WindowManager manager = (WindowManager) getActivity().getSystemService(getActivity().getApplication().WINDOW_SERVICE);
        //获取xoff
                int xpos = manager.getDefaultDisplay().getWidth() / 2 - popupWindow.getWidth() / 2;
        //xoff,yoff基于anchor的左下角进行偏移。
        popupWindow.showAsDropDown(parent, xpos, 0);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv_qian_id.setText( title1[i]);//替换成此时加载url类型值
                path = (commont.strategyUrl[i]);//更改加载的url
                ds.clear();//清除原有数据
                initData(ds);//初始化数据源
                fuyong();//适配器等
                //关闭popupWindow
                popupWindow.dismiss();
                popupWindow = null;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zhuangxiu, null);
        iv_qian = (ImageView) view.findViewById(R.id.iv_qian_id);
        iv_zhong = (ImageView) view.findViewById(R.id.iv_zhong_id);
        iv_hou = (ImageView) view.findViewById(R.id.iv_hou_id);
        tv_qian_id=(TextView) view.findViewById(R.id.tv_qian_id);
        iv_qian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopupWindow(iv_qian, title1);
            }
        });
       // vi = View.inflate(getActivity(),R.layout.zhuangxiu_popup_qian_item, null);
       // lv_dialog_id = (GridView) vi.findViewById(R.id.lv_dialog_id);
        iv_zhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(iv_zhong, title2);
            }
        });
        iv_hou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopupWindow(iv_hou, title3);
            }
        });
        ptrl_id = (PullToRefreshListView) view.findViewById(R.id.ptrl_id);
        Log.i("pk","==============================================");
        ds = new LinkedList<>();
        initData(ds);
        fuyong();
        ptrl_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), StrategyActivity.class);
                intent.putExtra("str",webPath);
                startActivity(intent);
                //Log.i("teg","=========================");
            }
        });

            return view;
        }
    private void fuyong() {
       // GetDetailDataCallBack callback=new GetDetailDataCallBack(getActivity());
        //strategyadapter = new MyStrategyAdapter(ds, getActivity().getApplicationContext());
        strategyadapter=new MyStrategyAdapter(ds, getActivity() , new GetDetailDataCallBack() {
                    @Override
                    public void getAllData(String interPath) {
                        webPath = interPath;
                /*Message message = StrategyActivity.handler.obtainMessage();
                message.obj = webPath;
                // message.what=webPath;
                StrategyActivity.handler.sendMessage(message);
*/
            }
        });
        ptrl_id.setAdapter(strategyadapter);
        ptrl_id.setMode(PullToRefreshBase.Mode.BOTH);
        ptrl_id.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

            }
        });
        //strategyadapter.notifyDataSetChanged();
    }
   /* public static Handler handler=new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            data=new ArrayList<>();
            data=(List<Edithor>)msg.obj;
            p=msg.what;
            Log.i("info", "------" + data.size());
        }
    };*/

    private void initData(final List<StrategyEntity.StrategyListBean> ds) {

        StringRequest request = new StringRequest(path, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                StrategyEntity strategyEntity = gson.fromJson(response, StrategyEntity.class);
                ds.addAll(strategyEntity.getStrategyList());

            }
        }, null);
        MyApp.getApp().getRequestQueue().add(request);
    }

   /* @Override
    public void getAllData(String interPath) {
        String webPath=interPath;
        Message message=StrategyActivity.handler.obtainMessage();
        message.obj=webPath;
        // message.what=webPath;
        StrategyActivity.handler.sendMessage(message);
    }*/
  /*  @Override
    public void getAllData(String interPath) {

    webPath=interPath;
    }*/
}



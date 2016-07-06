package com.example.guju.ui;

import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.guju.R;
import com.example.guju.fragment.BaseFragment;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup group;
    private List<Fragment> fragments = new ArrayList<>();
    private boolean isExit = false;
    private Handler mHandler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        isExit = false;
    }
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        linked();

    }

    private void linked() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                for (int j = 0; j < group.getChildCount(); j++) {
                    if (group.getChildAt(j).getId() == i) {
                        getFragmentManager().beginTransaction().replace(R.id.base_fragment_id, fragments.get(j)).commit();
                    }
                }
            }
        });
    }

    private void initView() {
        group = ((RadioGroup) findViewById(R.id.group_id));
        //new 4 个模块的fragment--------------------需要各自更改
        Fragment fragment1 = new BaseFragment();
        Fragment fragment2 = new BaseFragment();
        Fragment fragment3 = new BaseFragment();
        Fragment fragment4 = new BaseFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        getFragmentManager().beginTransaction().replace(R.id.base_fragment_id, fragments.get(0)).commit();

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //利用handler延迟发送更改状态信息
                mHandler.sendEmptyMessageDelayed(0, 2000);
            } else {
                finish();
                System.exit(0);
            }
        }
        return false;
    }


}
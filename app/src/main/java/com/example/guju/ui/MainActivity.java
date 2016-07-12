package com.example.guju.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.guju.R;
import com.example.guju.fragment.DecoratePlanFragment;
import com.example.guju.fragment.FourFragment;
import com.example.guju.fragment.PictureLibraryFragment;
import com.example.guju.fragment.StrategyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RadioGroup group;
    private List<Fragment> fragments = new ArrayList<>();
    private boolean isExit = false;
    private ImageView login;
    private ImageView freeDesign;
    private  static String  str;
    public static  Handler loginHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
             str = (String) msg.obj;
            // url= (Uri.parse(str));
           // Glide.with().load(url).into(login);
        }
    };
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
        aboutFreedesign();
        aboutLogin();
        linked();

    }

        //Glide.with(this).load(str).into(login);
    private void aboutFreedesign() {
        freeDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, FreedesignActivity.class);
                startActivity(intent);

            }
        });
    }
    private void aboutLogin() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
          Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
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
        login = ((ImageView)findViewById(R.id.image_login_id));
        freeDesign = ((ImageView) findViewById(R.id.image_freedesign_id));
        group = ((RadioGroup) findViewById(R.id.group_id));
        //new 4 个模块的fragment--------------------需要各自更改


        PictureLibraryFragment plf = new  PictureLibraryFragment();

        Fragment fragment1 = new DecoratePlanFragment();
        Fragment fragment2 = new PictureLibraryFragment();
        Fragment fragment3 = new StrategyFragment();
        Fragment fragment4 = new FourFragment();
        fragments.add(fragment1);
        fragments.add(plf);
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
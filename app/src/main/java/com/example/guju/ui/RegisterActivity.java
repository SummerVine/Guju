package com.example.guju.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guju.MyApp;
import com.example.guju.R;
import com.example.guju.bean.User;
import com.lidroid.xutils.exception.DbException;

import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class RegisterActivity extends AppCompatActivity {
    private EditText re_name_id;
    private EditText re_pwd_id;
    private String name;
    private String pwd;
    private User user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_activity);
        re_name_id  = (EditText) findViewById(R.id.re_name_id);
        re_pwd_id = (EditText) findViewById(R.id.re_pwd_id);
        initData();
    }
    private void initData() {
        name=re_name_id.getText().toString();
        pwd=re_pwd_id.getText().toString();
      user=new User(name,pwd);
    }
    public void Registered(View view){
        if (ishave(re_name_id,re_pwd_id)){
            Toast.makeText(RegisterActivity.this,"用户名已存在，请直接登录",Toast.LENGTH_LONG).show();
        }else {
            try {
                MyApp.getApp().getDbUtils().saveOrUpdate(user);
                Toast.makeText(RegisterActivity.this,"恭喜注册成功，请返回登录界面登录",Toast.LENGTH_LONG).show();
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean ishave(EditText re_name_id,EditText re_pwd_id){
        try {
            List<User> users = MyApp.getApp().getDbUtils().findAll(User.class);
            for (User user2:users) {
                if(re_name_id.getText().toString().equals(user2.getUserName())&&re_pwd_id.getText().toString().equals(user2.getUserPwd())){
                    return true;
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }

        return false;
    }

}

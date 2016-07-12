package com.example.guju.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guju.MyApp;
import com.example.guju.R;
import com.example.guju.bean.User;
import com.lidroid.xutils.exception.DbException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by Administrator on 2016/7/9.
 */
public class LoginActivity extends AppCompatActivity implements PlatformActionListener{
    private EditText et_name_id;
    private EditText et_pass_id;
    private User user;
    private String name;
    private String pwd;
    private View view;
   // private boolean LoginStat=false;
  //  private String PlatName;
    //private ImageView Login;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        setContentView(R.layout.login_activity);

        et_name_id= (EditText) findViewById(R.id.et_name_id);
        et_pass_id= (EditText) findViewById(R.id.et_pass_id);
        name=et_name_id.getText().toString();
        pwd=et_pass_id.getText().toString();
        user=new User(name,pwd);
    }
    public void Login(View view){
        switch (view.getId()) {
            case R.id.But_Login:
                if(ishave(name,pwd)){
                   // LoginStat=true;
                    Toast.makeText(LoginActivity.this,"登录成功！！！！",Toast.LENGTH_LONG).show();
                    onBackPressed();
                   // finish();
                }else {
                    //错误提示
                    Toast.makeText(LoginActivity.this,"用户名或者密码不正确！！！！",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.But_regist:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
        }
    }
    public boolean ishave(String na, String word){
        try {
            List<User> users = MyApp.getApp().getDbUtils().findAll(User.class);
            for (User user:users) {
                if(na.equals(user.getUserName())&&word.equals(user.getUserPwd())){
                    return true;
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void LoginXinLang(View view) {
        loginDeal(new SinaWeibo(this));
    }
    public void LoginWenXin(View view) {
        loginDeal(new Wechat(this));
    }
    public void LoginQQ(View view) {
        loginDeal(new QZone(this));
    }
    public void loginDeal(Platform platform)
    {
        String userId = platform.getDb().getUserId();
        if(!TextUtils.isEmpty(userId))
        {
            //已经登陆过,直接跳转到你需要跳转的处理页面
        }
        else{
            //注册授权监听
            platform.setPlatformActionListener(this);
            //认证用户合法信息【要功能不要数据】
            //1.platform.authorize();
            platform.showUser(null);
        }
    }
    /**
     * 授权成功
     * @param platform:平台信息
     * @param i:标注功能【1.要功能不要数据，2.要数据不要功能】
     * @param hashMap:用户的数据
     */
    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
       // LoginStat=true;
        if(i == Platform.ACTION_USER_INFOR)//要数据不要功能
        {
           // PlatName=platform.getName();
            Set<Map.Entry<String, Object>> entries = hashMap.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                Log.i("info","key:"+entry.getKey()+"\tvalue:"+entry.getValue());
            }
            Message message = MainActivity.loginHandler.obtainMessage();
            message.obj = platform.getDb().getUserIcon();
            MainActivity.loginHandler.sendMessage(message);

        }
        else if(i == Platform.ACTION_AUTHORIZING)//要功能不要数据
        {
            //跳转到成功认证的界面
        }
    }
    /**
     * 授权失败
     * @param platform
     * @param i
     * @param throwable:异常信息
     */
    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }
    /**
     * 取消登陆
     * @param platform
     * @param i
     */
    //ShareSDK cancelAuthWithType:ShareTypeTencentWeibo
    @Override
    public void onCancel(Platform platform, int i) {
        ShareSDK.stopSDK(this);
    }


}

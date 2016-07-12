package com.example.guju.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guju.MyApp;
import com.example.guju.R;
import com.example.guju.adapter.PicyureDetailsAdapter;
import com.example.guju.bean.Edithor;
import com.example.guju.url.Urlan;
import com.example.guju.utils.GlideCircleTransform;
import com.example.guju.view.PictureLibraryWebView;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by green on 2016/7/8.
 */
public class PictureLibraryActivity extends Activity {

    private ImageView iv_start_btn_back_id;
    private ImageView iv_start_share_id;
    private ImageView iv_title_photo_id;
    private TextView tv_name_id;
    private ImageView iv_certified_id;
    private TextView tv_raiders_btn_massage_id;
    private ImageView iv_umeng_xp_hl_indicator_bg;
    private TextView tv_design_id;
    private ViewPager  vp_id;
    private PicyureDetailsAdapter pda;
    private static List<Edithor> data;
    private static int position;
//    private void showShare() {
//        ShareSDK.initSDK(this);
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//
//        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
//        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
////         title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
////         oks.setTitle(getString(R.string.Guju));
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        // oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享!");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
////        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
////        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
////        oks.setSite(getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
////        oks.setSiteUrl("http://sharesdk.cn");
//        // 启动分享GUI
//        oks.show(this);
//    }
//    private static List<PictureFactory.PhotosBean> data;
    public static Handler handler=new Handler(){
        public void handleMessage(Message msg){
               super.handleMessage(msg);
            data=new ArrayList<>();
            data=(List<Edithor>)msg.obj;
            position=msg.what;
            Log.i("info", "------" + data.size());
        }
    };
    private ImageView iv_guju_shop_right;
    private TextView tv_love_id;
    private ImageView iv_love_hollow;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_next);

        //初始化
        initWidge();
        pda = new PicyureDetailsAdapter(new ArrayList<PhotoView>());
        vp_id.setAdapter(pda);

        //请求图片信息
        RequestPicture();
        //设置名字和图片外加认证标志
        SetNameAndPhoto();
        vp_id.setCurrentItem(position);
        //添加控件点击事件
        AddClickEvent();
        iv_love_hollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void SetNameAndPhoto() {

        vp_id.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Glide.with(getApplicationContext()).load(data.get(position).getHeadPhotos())
                        .transform(new GlideCircleTransform(getApplicationContext()))
                        .crossFade().into(iv_title_photo_id);
                tv_name_id.setText(data.get(position).getName());
                Boolean certified=data.get(position).getCertified();
                iv_certified_id.setImageResource(R.drawable.ordinary_designer_icon);
                tv_love_id.setText(""+data.get(position).getLikeNum());
                tv_raiders_btn_massage_id.setText(""+data.get(position).getCommentNum());
                if(certified){

                    iv_certified_id.setVisibility(View.VISIBLE);
                }else{
//                    获得那个布局然后 用 setVisibity()方法实现 显示或隐藏参数分别为
//                    View.INVISIBLE （不显示，仍占有内存）、View.GONE(不显示，不占用内存)和 View.VISIBLE。
                    iv_certified_id.setVisibility(View.GONE);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void AddClickEvent() {

        //返回原来界面
        iv_start_btn_back_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 给添加按返回键效果
                onBackPressed();
            }
        });
        //返回原来界面
        vp_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 给添加按返回键效果
                onBackPressed();
            }
        });
        //跳到免费设计
        tv_design_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        //跳到设计时详情界面
        iv_guju_shop_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent =new Intent();


            }
        });

        //分享
        iv_start_share_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MyApp.getApp().showShare("你好!我的分享","123","这张图片很好看");
            }
        });
        //跳到免费设计
        tv_design_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), PictureLibraryWebView.class);
                intent.putExtra("link",Urlan.freeDesinPath);
                startActivity(intent);

            }
        });
    }



    private void RequestPicture() {
        List<PhotoView> imageViews=new ArrayList<>();
        for(int i=0;i<data.size();i++){
            PhotoView imageView = new PhotoView(this);
            Glide.with(this).load("http://image.guju.com.cn/images/145/9/"+data.get(i).getStartNumber()+"_0_9-.jpg@1o").into(imageView);
            imageViews.add(imageView);

        }
        pda.addAll(imageViews);
    }

    private void initWidge() {
        iv_start_btn_back_id = ((ImageView) findViewById(R.id.iv_start_btn_back_id));
        iv_start_share_id = ((ImageView) findViewById(R.id.iv_start_share_id));
        iv_title_photo_id = ((ImageView) findViewById(R.id.iv_title_photo_id));
        tv_name_id = ((TextView) findViewById(R.id.tv_name_id));
        iv_certified_id = ((ImageView) findViewById(R.id.iv_certified_id));
        tv_raiders_btn_massage_id = ((TextView) findViewById(R.id.tv_raiders_btn_massage_id));
        iv_umeng_xp_hl_indicator_bg = ((ImageView) findViewById(R.id.iv_umeng_xp_hl_indicator_bg));
        tv_design_id = ((TextView) findViewById(R.id.tv_design_id));
        iv_guju_shop_right = ((ImageView) findViewById(R.id.iv_guju_shop_right));
        vp_id = ((ViewPager) findViewById(R.id.vp_id));
        tv_love_id = ((TextView) findViewById(R.id.tv_love_id));
        iv_love_hollow = ((ImageView) findViewById(R.id.iv_love_hollow));


    }
}

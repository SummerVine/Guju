package com.example.guju.url;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
public class Urlan {
    public  static String freeDesinPath="http://guju.com.cn/freeDesignNew?src=app_home_freeDes";
    public  static  String url4(String city,int page){
        String stringCity = "";
        try {
            stringCity = URLEncoder.encode(city, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "http://api.guju.com.cn/v2/user/professionals?start="+page+"&count=10&user=&city="+stringCity;
    }
    //案例
    public static String getCase(String name,int page){
        //UTF -8 转换为URL的
        String stringName = "";
        try {
            stringName = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "http://api.guju.com.cn/v2/user/"+stringName+"/projects?start="+page+"&count=5&user=null&";
    }

    //攻略
    public static String getStra(String name){
        //UTF -8 转换为URL的
        String stringName = "";
        try {
            stringName = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "http://api.guju.com.cn/v2/user/"+stringName+"/strategy?start=0&count=5&checked=1&loginId=&";
    }
    //   http://api.guju.com.cn/v2/user/昀阳/strategy?start=0&count=5&checked=1&loginId=&
//分享    http://api.guju.com.cn/v2/user/%E6%9D%A8%E6%A2%A6%E5%88%A9/photos?start=5&count=5&user=&
    public static String getShare(String name){
        //UTF -8 转换为URL的
        String stringName = "";
        try {
            stringName = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "http://api.guju.com.cn/v2/user/"+stringName+"/photos?start=5&count=5&user=&";
    }
    //灵感集
    //http://api.guju.com.cn/v2/user/%E5%8C%97%E4%BA%AC%E4%BB%8A%E6%9C%9D%E8%A3%85%E9%A5%B0%E6%80%BB%E9%83%A8/ideabooks?start=0&count=6&user=&
    public static String getStrGrid(String name){
        //UTF -8 转换为URL的
        String stringName = "";
        try {
            stringName = URLEncoder.encode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "http://api.guju.com.cn/v2/user/"+stringName+"/ideabooks?start=0&count=6&user=&";
    }
//    public  static String freeDesinPath="http://guju.com.cn/freeDesignNew?src=app_home_freeDes";
//
//    //案例
//public static String getCase(String name,int page){
//        return "http://api.guju.com.cn/v2/user/"+name+"/projects?start="+page+"&count=5&user=null&";
//    }
//
////攻略
//public static String getStra(String name){
//    return "http://api.guju.com.cn/v2/user/"+name+"/strategy?start=0&count=5&checked=1&loginId=&";
//}
// //   http://api.guju.com.cn/v2/user/昀阳/strategy?start=0&count=5&checked=1&loginId=&
////分享    http://api.guju.com.cn/v2/user/%E6%9D%A8%E6%A2%A6%E5%88%A9/photos?start=5&count=5&user=&
// public static String getShare(String name){
//     return "http://api.guju.com.cn/v2/user/"+name+"/photos?start=5&count=5&user=&";
// }
//    //灵感集
//    //http://api.guju.com.cn/v2/user/%E5%8C%97%E4%BA%AC%E4%BB%8A%E6%9C%9D%E8%A3%85%E9%A5%B0%E6%80%BB%E9%83%A8/ideabooks?start=0&count=6&user=&
//    public static String getStrGrid(String name){
//        return "http://api.guju.com.cn/v2/user/"+name+"/ideabooks?start=0&count=6&user=&";
//    }
}

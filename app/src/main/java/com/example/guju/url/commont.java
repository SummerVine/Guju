package com.example.guju.url;

/**
 * Created by Administrator on 2016/7/5.
 */
public class commont {

    public  static final  String url4="http://api.guju.com.cn/v2/user/professionals?start=0&count=10&user=&city=%E5%8C%97%E4%BA%AC&";
//public static final String strategyUrl="http://api.guju.com.cn/v2/strategy/strategyList?userId=&count=10&start=0&orderString=CATEGORY&category="+j+"&";//装修要点
       public static String getUrl(int j ){
       return  "http://api.guju.com.cn/v2/strategy/strategyList?userId=&count=10&start=0&orderString=CATEGORY&category="+j+"&";

    }


    // "http://api.guju.com.cn/v2/strategy/strategyList?userId=&count=10&start=0&orderString=CATEGORY&category=15&",//装修设计
//"http://api.guju.com.cn/v2/strategy/strategyList?userId=&count=10&start=0&orderString=CATEGORY&category=2&",//家居配饰
//"http://api.guju.com.cn/v2/strategy/strategyList?userId=&count=10&start=0&orderString=CATEGORY&category=20&",//装修灵感

}

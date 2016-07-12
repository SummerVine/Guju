package com.example.guju.datas;

import com.google.gson.internal.LinkedTreeMap;

import java.util.Map;

/**
 * Created by liushuxin on 2016/7/7.
 */
public class DecorateDatasUtils {


    public static Map<String,Integer> obtainPopArea(){
        Map<String,Integer> map=new LinkedTreeMap<>();
        map.put("不限",0);
        map.put("70以下",301);
        map.put("70-80",302);
        map.put("80-90",303);
        map.put("90-100",304);
        map.put("100-110",305);
        map.put("110-130",306);
        map.put("130-150",307);
        map.put("150以上",308);
        return map;
    }

    public static  Map<String,Integer> obtainPopCost(){
        Map<String,Integer> map=new LinkedTreeMap<>();
        map.put("不限",0);
       for (int i=1,j=401;i<9;i++,j++){
           map.put(popCost[i],j);
       }
        return map;
    }
    public static Map<String,Integer> obtainPopType(){
        Map<String,Integer> map=new LinkedTreeMap<>();
        map.put("不限",0);
        for (int i=1,j=201;i<14;i++,j++){
            map.put(popType[i],j);
        }
        return map;
    }
    public static Map<String,Integer> obtainPopStyle(){
        Map<String,Integer> map=new LinkedTreeMap<>();
        map.put("不限",0);
        for (int i=1,j=101;i<15;i++,j++){
            map.put(popStyle[i],j);
        }
        return map;
    }

    public static final String[] popArea = { "不限", "70以下", "70-80", "80-90", "90-100","100-110","110-130","130-150","150以上"};
    public static final String[] popCost = { "不限", "8W以下", "8_10W", "10-14W", "14-20W","20-30W","30-40W","40-50W","50W以上"};
    public static final String[] popStyle = { "不限", "现代", "简约", "韩式", "地中海","欧式","中式","日式","新古典","宜家","田园","混搭","清新","小资","美式"};
    public static final String[] popType = { "不限", "一居", "两居", "三居", "四居","小户型","大户型","别墅","复式","公寓","跃层","LOFT","阁楼","公装"};

    public  static  final String coverPhotoLastStr="_0_9-.jpg@1o";
    public static final String coverPhotoPreStr="http://image.guju.com.cn/images/";
   // http://image.guju.com.cn/images/145/9/1459743_0_9-.jpg@1o

    public static final String[] viewpagerUrl={"http://img.guju.com.cn/indexImg/banner_project_20160601114526.png","http://img.guju.com.cn/indexImg/banner_project_20160518103450.png","http://img.guju.com.cn/indexImg/banner_project_20160628112501.png","http://img.guju.com.cn/indexImg/banner_project_20160628112515.png","http://img.guju.com.cn/indexImg/banner_project_20160620112107.png"};


}

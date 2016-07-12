package com.example.guju.utils;

import com.example.guju.entity.AA;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by steven on 16/3/29.
 */
public interface DecorateServerinterface {
   // public static final
    ///////////////////////////////////////////////////////////////////////////
    // GET网络请求方式
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 作用：GET请求最简单的写法,无Path参数和Query参数
     */
//  @GET("hot/?start=0&count=20&is_ipad=true")
//  Call<ResponseBody> getLatestString();
   // public String strUrl= DecorateDatasUtils.coverPhotoLastStr;


    @GET("project/list?user=&count=10&start=0&")
    Call<AA> getInfoList(@QueryMap Map<String,Integer> map);
}

package com.example.guju.utils;

import com.example.guju.bean.DecorateResultBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by steven on 16/3/29.
 */
public interface DecorateServerinterface {
    ///////////////////////////////////////////////////////////////////////////
    // GET网络请求方式
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 作用：GET请求最简单的写法,无Path参数和Query参数
     */
//    @GET("hot/?start=0&count=20&is_ipad=true")
//    Call<ResponseBody> getLatestString();
    @GET("/v2/project/list?user=&count=10&start=0&area=303&cost=0&type=0&style=0&")
    Call<DecorateResultBean> getInfoList();
}

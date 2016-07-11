package com.example.guju.bean;

import com.example.guju.entity.DecoratePlan;

import java.util.List;

/**
 * Created by liushuxin on 2016/7/8.
 */
public class DecorateResultBean  {
    private List<DecoratePlan> resultlistDatas;
    public List<DecoratePlan> getDecorateDatas() {
        return resultlistDatas;
    }
    public void setDecorateDatas(List<DecoratePlan> resultlistDatas) {
        this.resultlistDatas = resultlistDatas;
    }
}

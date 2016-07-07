package com.example.guju.datas;

import com.example.guju.entity.DecoratePlan;
import com.example.guju.entity.Strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liushuxin on 2016/7/6.
 */
public class DecorateRvDataServer {

    private DecorateRvDataServer() {
    }

    public static List<DecoratePlan> getSampleData(int lenth) {
        List<DecoratePlan> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            DecoratePlan plan = new DecoratePlan();
            plan.setPlanName("Chad" + i);
            plan.setPanDetail("谷居设计");
            plan.setPlanAuthor("装修设计师"+i);
//            plan.setPlanAuthorIcon("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
//            plan.setPlanImgUrl("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            list.add(plan);
        }
        return list;
    }
    public static List<Strategy> getStrategy(int lenth) {
        List<Strategy> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Strategy plan = new Strategy();
            plan.setTitle("Chad" + (i+""));
            plan.setCategoryName("谷居设计");
            plan.setTypeName("装修设计师"+i);
//            plan.setPlanAuthorIcon("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
//            plan.setPlanImgUrl("https://avatars1.githubusercontent.com/u/7698209?v=3&s=460");
            list.add(plan);
        }
        return list;
    }
}

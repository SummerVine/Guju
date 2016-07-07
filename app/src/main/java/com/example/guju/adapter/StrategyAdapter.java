package com.example.guju.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.guju.R;
import com.example.guju.datas.DataServer;
import com.example.guju.entity.Strategy;



/**
 * Created by Administrator on 2016/7/6.
 */
public class StrategyAdapter extends BaseQuickAdapter<Strategy> {


    public StrategyAdapter(int dataSize) {
        super(R.layout.activity_decorateplan_rv_item, DataServer.getStrategy(100));
    }


    public StrategyAdapter() {
        super( R.layout.zhuangxiu_item, DataServer.getStrategy(100));
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Strategy strategy) {
        baseViewHolder.setText(R.id.tv_id, strategy.getTypeName());
    }
}

package com.example.guju.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.guju.R;
import com.example.guju.datas.DataServer;
import com.example.guju.entity.DecoratePlan;
import com.example.guju.entity.Strategy;

import java.util.List;


/**
 * Created by Administrator on 2016/7/6.
 */
public class StrategyAdapter extends BaseQuickAdapter<Strategy> {

    public StrategyAdapter() {
        super( R.layout.zhuangxiu_item, DataServer.getStrategy(100));
    }

    public StrategyAdapter(int dataSize) {
        super( R.layout.zhuangxiu_item, DataServer.getStrategy(dataSize));
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Strategy strategy) {
        baseViewHolder.setText(R.id.tv_type_id, strategy.getType());
    }
}

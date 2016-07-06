package com.example.guju.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.guju.R;
import com.example.guju.entity.Strategy;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class StrategyAdapter extends BaseQuickAdapter<Strategy> {
    public StrategyAdapter(int layoutResId, List<Strategy> data) {
        super(R.layout.zhuangxiu_item, data);
    }

    public StrategyAdapter(View contentView, List<Strategy> data) {
        super(R.layout.zhuangxiu_item, data);
    }

    public StrategyAdapter(List<Strategy> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Strategy strategy) {
        baseViewHolder.setText(R.id.vp_id, strategy.getCategoryName());
    }
}

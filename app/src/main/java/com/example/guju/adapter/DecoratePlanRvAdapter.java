package com.example.guju.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.guju.R;
import com.example.guju.datas.DataServer;
import com.example.guju.entity.DecoratePlan;

/**
 * Created by liushuxin on 2016/7/5.
 */
public class DecoratePlanRvAdapter extends BaseQuickAdapter<DecoratePlan> {
    public DecoratePlanRvAdapter() {
        super( R.layout.decorate_rvitem_activity, DataServer.getSampleData(100));
    }

    public DecoratePlanRvAdapter(int dataSize) {
        super( R.layout.decorate_rvitem_activity, DataServer.getSampleData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DecoratePlan decoratePlan) {
        baseViewHolder.setText(R.id.tv_id, decoratePlan.getPlanName());
//                .setText(R.id.tweetText, decoratePlan.getText())
//                .setText(R.id.tweetDate, decoratePlan.getCreatedAt())
//                .setVisible(R.id.tweetRT, decoratePlan.isRetweet())
//                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
//                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
//                .linkify(R.id.tweetText);

        //Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));
    }
}



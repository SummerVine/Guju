package com.example.guju.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.guju.R;
import com.example.guju.entity.DecoratePlan;

import java.util.List;

/**
 * Created by liushuxin on 2016/7/5.
 */
public class DecoratePlanRvAdapter extends BaseQuickAdapter<DecoratePlan> {


    public DecoratePlanRvAdapter(int layoutResId, List<DecoratePlan> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DecoratePlan decoratePlan) {
        baseViewHolder.setText(R.id.decorate_bulidname_id, decoratePlan.getBuildingName());
        baseViewHolder.setText(R.id.Decorate_title_id, decoratePlan.getTitle() + "");
        baseViewHolder.setText(R.id.Decorate_details_id, decoratePlan.getStyleShow() + "." + decoratePlan.getTypeShow() + "." + decoratePlan.getAreaShow() + "." + decoratePlan.getCostShow());


//                .setText(R.id.tweetText, decoratePlan.getText())
//                .setText(R.id.tweetDate, decoratePlan.getCreatedAt())
//                .setVisible(R.id.tweetRT, decoratePlan.isRetweet())
//                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
//                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
//                .linkify(R.id.tweetText);

        //Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));
    }
}



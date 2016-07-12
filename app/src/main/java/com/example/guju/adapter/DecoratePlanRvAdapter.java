package com.example.guju.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.guju.R;
import com.example.guju.datas.DecorateDatasUtils;
import com.example.guju.entity.DecoratePlan;
import com.example.guju.utils.GlideCircleTransform;

import java.util.List;

/**
 * Created by liushuxin on 2016/7/5.
 */
public class DecoratePlanRvAdapter extends BaseQuickAdapter<DecoratePlan> {
    private List<DecoratePlan> datas;

    public DecoratePlanRvAdapter(List<DecoratePlan> data) {
        super(R.layout.decorate_rvitem_activity, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, DecoratePlan decoratePlan) {

        baseViewHolder.setText(R.id.decorate_bulidname_id, decoratePlan.getBuildingName());
        baseViewHolder.setText(R.id.Decorate_tv_title_id, decoratePlan.getTitle());
        baseViewHolder.setText(R.id.Decorate_details_id, decoratePlan.getDetails());
        Glide.with(mContext).load(decoratePlan.getUserSmallImage()).crossFade().transform(new GlideCircleTransform(mContext)).into((ImageView) baseViewHolder.getView(R.id.decorate_author_id));
        int coverPhoto = decoratePlan.getCoverPhoto();
        String cover = coverPhoto + "";
        if (cover.length() == 1) {
            Glide.with(mContext).load("http://image.guju.com.cn/images/138/9/1389106_0_9-.jpg@1o").into((ImageView) baseViewHolder.getView(R.id.decorate_coveriv_id));
        } else {
            String preThree = cover.substring(0, 3);
            Glide.with(mContext).load(DecorateDatasUtils.coverPhotoPreStr + preThree + "/9/" + coverPhoto + DecorateDatasUtils.coverPhotoLastStr).into((ImageView) baseViewHolder.getView(R.id.decorate_coveriv_id));
        }

        //http://image.guju.com.cn/images/
        // 145
        // /9/
        // 1459743
        // _0_9-.jpg@1o
    }

    @Override
    public void setOnLoadMoreListener(RequestLoadMoreListener requestLoadMoreListener) {
        super.setOnLoadMoreListener(requestLoadMoreListener);
    }

    @Override
    public void setOnLoadMoreListener(int pageSize, RequestLoadMoreListener requestLoadMoreListener) {
        super.setOnLoadMoreListener(pageSize, requestLoadMoreListener);
    }


}


//                .setText(R.id.tweetText, decoratePlan.getText())
//                .setText(R.id.tweetDate, decoratePlan.getCreatedAt())
//                .setVisible(R.id.tweetRT, decoratePlan.isRetweet())
//                .setOnClickListener(R.id.tweetAvatar, new OnItemChildClickListener())
//                .setOnClickListener(R.id.tweetName, new OnItemChildClickListener())
//                .linkify(R.id.tweetText);

//Glide.with(mContext).load(item.getUserAvatar()).crossFade().placeholder(R.mipmap.def_head).transform(new GlideCircleTransform(mContext)).into((ImageView) helper.getView(R.id.tweetAvatar));





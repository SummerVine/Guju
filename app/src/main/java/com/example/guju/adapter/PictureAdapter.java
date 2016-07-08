package com.example.guju.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guju.R;
import com.example.guju.bean.PictureFactory;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by green on 2016/7/6.
 */
public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<PictureFactory.PhotosBean> data;
    private Context context;
    private List<Integer> heights;
    private OnItemClickListener onItemClickListener;
    //传值
    public PictureAdapter(List<PictureFactory.PhotosBean> data, Context context) {
        this.data = data;
        this.context = context;

    }

    @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MasonryView(LayoutInflater.from(context).inflate(R.layout.picture_library_item, viewGroup, false));
        }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
//        //得到item的LayoutParams布局参数
//        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
//        //把随机的高度赋予item布局
//       params.height = heights.get(position);//下标越界
//        //把params设置给item布局
//        holder.itemView.setLayoutParams(params);
//        //为控件绑定数据
        Glide.with(context).load("http://image.guju.com.cn/images/145/9/"+data.get(position).getId()+"_0_9-.jpg@1o").into(((MasonryView) holder).iv_picture_library_id);
        //如果设置了监听那么它就不为空，然后回调相应的方法
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //得到当前点击item的位置pos
                    int pos = holder.getLayoutPosition();
                    //把事件交给我们实现的接口那里处理
                    onItemClickListener.onItemClick(pos,holder.itemView);
                }
            });
            //添加长按监听
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //得到当前点击item的位置pos
                    int pos = holder.getLayoutPosition();
                    //把事件交给我们实现的接口那里处理
                    onItemClickListener.ItemLongClickListener(pos,holder.itemView);
                    return true;
                }
            });
        }
    }


        public int getItemCount() {
            return data.size();
        }
        public void addAll(List<PictureFactory.PhotosBean> d){
        data.addAll(d);
        notifyDataSetChanged();
    }
    //下拉刷新的时候会被调用
        public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

        public class MasonryView  extends  RecyclerView.ViewHolder {

            private final ImageView iv_picture_library_id;

            public MasonryView(View itemView) {
                super(itemView);
                iv_picture_library_id = (ImageView) itemView.findViewById(R.id.iv_picture_library_id);
            }

            public void onClick(View v) {
                onItemClickListener.onItemClick(getLayoutPosition(), v);
            }

        }
   // 设置点击事件的监听方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    //设置监听事件接口
    public interface  OnItemClickListener{
         void onItemClick(int position, View itemView);
         void ItemLongClickListener(int postion,View itemview);
    }


}

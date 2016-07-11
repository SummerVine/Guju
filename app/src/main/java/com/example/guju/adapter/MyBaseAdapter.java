package com.example.guju.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;


public abstract class MyBaseAdapter<T> extends BaseAdapter{
    private List<T>  data;
    private LayoutInflater inflater;

    public MyBaseAdapter(List<T> data, Context context) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public LayoutInflater getInflater() {
        return inflater;
    }

    public void addAll(List<T> list){
        data.addAll(list);
        notifyDataSetChanged();
    }
    public void removeAll(){
        data.clear();
        notifyDataSetChanged();
    }
    public void deleteItem(int position){
        data.remove(position);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getViewItem(i,view,viewGroup);
    }
    public abstract View getViewItem(int i, View view, ViewGroup viewGroup);
}

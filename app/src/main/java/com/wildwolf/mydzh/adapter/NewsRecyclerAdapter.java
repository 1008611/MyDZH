package com.wildwolf.mydzh.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.wildwolf.mydzh.model.NewsEntity;

/**
 * Created by ${wild00wolf} on 2016/11/15.
 */
public class NewsRecyclerAdapter extends RecyclerArrayAdapter<NewsEntity.StoriesBean>{
    public NewsRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsViewHolder(parent);
    }
}

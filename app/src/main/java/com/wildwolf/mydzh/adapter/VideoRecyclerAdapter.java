package com.wildwolf.mydzh.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.wildwolf.mydzh.model.VideoEntity;

/**
 * Created by ${wild00wolf} on 2016/11/14.
 */
public class VideoRecyclerAdapter  extends RecyclerArrayAdapter<VideoEntity>{
    public VideoRecyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHolder(parent);
    }
}

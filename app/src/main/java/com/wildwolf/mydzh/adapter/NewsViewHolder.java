package com.wildwolf.mydzh.adapter;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.wildwolf.mydzh.R;
import com.wildwolf.mydzh.model.NewsEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${wild00wolf} on 2016/11/15.
 */
public class NewsViewHolder extends BaseViewHolder<NewsEntity.StoriesBean> {

    @Bind(R.id.item_news_title)
    TextView itemNewsTitle;
    @Bind(R.id.item_news_img)
    ImageView itemNewsImg;


    public NewsViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_news);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(NewsEntity.StoriesBean data) {

        Picasso.with(getContext()).load(data.getImages().get(0)).into(itemNewsImg);
        itemNewsTitle.setText(data.getTitle());
    }
}

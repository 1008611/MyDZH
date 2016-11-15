package com.wildwolf.mydzh.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.wildwolf.mydzh.R;
import com.wildwolf.mydzh.adapter.NewsRecyclerAdapter;
import com.wildwolf.mydzh.base.BaseMvpViewPagerFragment;
import com.wildwolf.mydzh.model.NewsDetailsEntity;
import com.wildwolf.mydzh.model.NewsEntity;
import com.wildwolf.mydzh.presenter.NewsPresenter;
import com.wildwolf.mydzh.ui.NewsView;
import com.wildwolf.mydzh.ui.activity.NewsDetailsActivity;
import com.wildwolf.mydzh.widget.TopViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${wild00wolf} on 2016/11/15.
 */
public class NewsFragment extends BaseMvpViewPagerFragment<NewsPresenter> implements NewsView, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    @Bind(R.id.vp_top_stories)
    TopViewPager vpTopStories;
    @Bind(R.id.tv_top_title)
    TextView tvTopTitle;
    private View rootView;

    private LinearLayoutManager mLayoutManager;
    private NewsRecyclerAdapter mNewsRecyclerAdapter;
    private NewsEntity newsInfo;
    private List<NewsEntity.StoriesBean> storiesBeen = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_news, container, false);
        }
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected NewsPresenter creatPresenter() {
        return new NewsPresenter(NewsFragment.this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mNewsRecyclerAdapter = new NewsRecyclerAdapter(getActivity());
        mRecyclerView.setAdapter(mNewsRecyclerAdapter);

        mNewsRecyclerAdapter.setMore(R.layout.view_more, this);
        mNewsRecyclerAdapter.setError(R.layout.view_error);

        mRecyclerView.setRefreshListener(this);
        mRecyclerView.setEmptyView(R.layout.view_empty);
        mNewsRecyclerAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra("id", storiesBeen.get(position).getId() + "");
                startActivity(intent);
            }
        });
        mvpPresenter.getVideoList();
    }

    @Override
    public void getNewsSuccess(NewsEntity newsEntity) {
        newsInfo = newsEntity;
        storiesBeen.addAll(newsEntity.getStories());
        mNewsRecyclerAdapter.addAll(newsEntity.getStories());
        vpTopStories.init(newsEntity.getTop_stories(), tvTopTitle, new TopViewPager.ViewPagerClickListenner() {
            @Override
            public void onClick(NewsEntity.TopStoriesBean item) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra("id", item.getId() + "");
                startActivity(intent);
            }

        });
        vpTopStories.startAutoRun();
    }

    @Override
    public void getNewsFail(String msg) {
        toastShow(msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
        vpTopStories.stopAutoRun();
        storiesBeen.clear();
        newsInfo=null;
        mNewsRecyclerAdapter.clear();
        mvpPresenter.getVideoList();
    }

    @Override
    public void onLoadMore() {
        vpTopStories.stopAutoRun();
        mvpPresenter.getBeforeNews(newsInfo.getDate());
    }
}

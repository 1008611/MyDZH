package com.wildwolf.mydzh.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.wildwolf.mydzh.R;
import com.wildwolf.mydzh.adapter.VideoRecyclerAdapter;
import com.wildwolf.mydzh.base.BaseMvpViewPagerFragment;
import com.wildwolf.mydzh.base.BasePresenter;
import com.wildwolf.mydzh.model.VideoEntity;
import com.wildwolf.mydzh.presenter.MainPresenter;
import com.wildwolf.mydzh.ui.MainView;
import com.wildwolf.mydzh.ui.activity.WebViewActivity;
import com.wildwolf.mydzh.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${wild00wolf} on 2016/11/14.
 */
public class VideListFragment extends BaseMvpViewPagerFragment<MainPresenter> implements MainView, RecyclerArrayAdapter.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {


    @Bind(R.id.recyclerView)
    EasyRecyclerView mRecyclerView;
    private int index = 0;
    private String[] ids = {"1", "13", "64", "16", "31", "19", "62", "63", "3", "59", "27", "5", "18", "6", "193"};
    private VideoRecyclerAdapter mVideoRecyclerAdapter;
    private LinearLayoutManager mLayoutManager;
    private int page = 1;
    private int count = 10;
    private List<VideoEntity> videoEntities = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.layout_only_list;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    protected MainPresenter creatPresenter() {
        return new MainPresenter(VideListFragment.this);
    }

    private void initView() {
        index = getArguments().getInt("index", 0);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mVideoRecyclerAdapter = new VideoRecyclerAdapter(getActivity());
        mRecyclerView.setAdapter(mVideoRecyclerAdapter);

        mVideoRecyclerAdapter.setMore(R.layout.view_more, this);
        mVideoRecyclerAdapter.setError(R.layout.view_error);

        mRecyclerView.setRefreshListener(this);
        mRecyclerView.setEmptyView(R.layout.view_empty);
        mVideoRecyclerAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", videoEntities.get(position).getUrl());
                getActivity().startActivity(intent);
            }
        });
        mRecyclerView.setRefreshing(true);
        onRefresh();
    }

    private void getVideoList(String s) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", ids[index]);
        map.put("page", page);
        map.put("count", count);
        if (!TextUtils.isEmpty(s)) {
            map.put("max_id", s);
        } else {
            page = 1;
            videoEntities.clear();
            mVideoRecyclerAdapter.clear();
        }
        mvpPresenter.getVideoList(map);
        LogUtil.d(page);
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
    }

    @Override
    public void getVideoSuccess(List<VideoEntity> videoEntity) {
        videoEntities.addAll(videoEntity);
        mVideoRecyclerAdapter.addAll(videoEntity);
    }

    @Override
    public void getVideoFail(String msg) {
        toastShow(msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onLoadMore() {
        page++;
        getVideoList(videoEntities.get(videoEntities.size() - 1).getId() + "");
    }

    @Override
    public void onRefresh() {
        page = 1;
        getVideoList(null);
    }
}

package com.wildwolf.mydzh.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildwolf.mydzh.R;
import com.wildwolf.mydzh.adapter.BaseFragmentAdapter;
import com.wildwolf.mydzh.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ${wild00wolf} on 2016/11/11.
 */
public class VideoFragment extends BaseFragment {

    @Bind(R.id.tabs)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    private View rootView;
    List<Fragment> mFragments;
    String[] mTitles = new String[]{
            "热门", "搞笑", "逗比", "明星名人", "男神", "女神", "音乐", "舞蹈", "旅行", "美食", "美妆时尚", "涨姿势", "宝宝", "萌宠乐园", "二次元"
    };



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        setupViewPager();
    }

    private void setupViewPager() {
        mFragments = new ArrayList<>();
        for (int i =0;i<mTitles.length;i++){
            VideListFragment videListFragment = new VideListFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index",i);
            videListFragment.setArguments(bundle);
            mFragments.add(videListFragment);
        }
        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getChildFragmentManager(),mFragments,mTitles);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setupWithViewPager(mViewPager);

    }

}

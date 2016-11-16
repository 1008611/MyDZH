package com.wildwolf.mydzh.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.wildwolf.mydzh.utils.LogUtil;

/**
 * Created by ${wild00wolf} on 2016/11/14.
 */
public abstract class ViewPagerFragment extends BaseFragment {
    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    /**
     * onCreateView()里返回的view，修饰为protected,所以子类继承该类时，在onCreateView里必须对该变量进行初始化
     */
    protected View rootView;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.d("setUserVisibleHint() -> isVisibleToUser: " + isVisibleToUser);
        if (rootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            onFragmentVisibleChange(false);
            isFragmentVisible = false;
        }
    }

    protected void onFragmentVisibleChange(boolean b) {
        LogUtil.w("onFragmentVisibleChange -> isVisible: " + b);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    private void initVariable() {
        hasCreateView = false;
        isFragmentVisible = false;
    }
}

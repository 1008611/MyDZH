package com.wildwolf.mydzh.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by ${wild00wolf} on 2016/11/14.
 */
public abstract class BaseMvpViewPagerFragment<P  extends  BasePresenter> extends ViewPagerFragment {

    protected P  mvpPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mvpPresenter = creatPresenter();
    }

    protected  abstract P creatPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
    public void showLoading() {
        showProgressDialog();
    }

    public void hideLoading() {
        dismissProgressDialog();
    }
}

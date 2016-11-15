package com.wildwolf.mydzh.ui.activity;

import android.os.Bundle;

import com.wildwolf.mydzh.base.BaseActivity;
import com.wildwolf.mydzh.base.BasePresenter;

/**
 * Created by ${wild00wolf} on 2016/11/15.
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);

    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null){
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

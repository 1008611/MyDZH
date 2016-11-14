package com.wildwolf.mydzh.presenter;

import com.wildwolf.mydzh.base.BasePresenter;
import com.wildwolf.mydzh.model.VideoEntity;
import com.wildwolf.mydzh.net.ApiCallBack;
import com.wildwolf.mydzh.ui.MainView;

import java.util.HashMap;
import java.util.List;

import rx.Observable;

/**
 * Created by ${wild00wolf} on 2016/11/14.
 */
public class MainPresenter extends BasePresenter<MainView> {
    public MainPresenter(MainView view) {
        attachView(view);
    }

    public void getVideoList(HashMap<String, Object> map) {
        mvpView.showLoading();
        addSubscription(videoapiStores.getVideoList(map), new ApiCallBack<List<VideoEntity>>() {
            @Override
            public void onSuccess(List<VideoEntity> model) {
                mvpView.getVideoSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.getVideoFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }


}

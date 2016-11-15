package com.wildwolf.mydzh.presenter;

import com.wildwolf.mydzh.base.BasePresenter;
import com.wildwolf.mydzh.model.NewsEntity;
import com.wildwolf.mydzh.net.ApiCallBack;
import com.wildwolf.mydzh.ui.NewsView;

/**
 * Created by ${wild00wolf} on 2016/11/15.
 */
public class NewsPresenter extends BasePresenter<NewsView> {
    public NewsPresenter(NewsView newsView) {
        attachView(newsView);
    }

    public void getVideoList() {
        mvpView.showLoading();
        addSubscription(zhihuApiStores.getLatestNews(), new ApiCallBack<NewsEntity>() {
            @Override
            public void onSuccess(NewsEntity model) {
                mvpView.getNewsSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.getNewsFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }

    public void getBeforeNews(String date) {
        mvpView.showLoading();
        addSubscription(zhihuApiStores.getBeforetNews(date), new ApiCallBack<NewsEntity>() {
            @Override
            public void onSuccess(NewsEntity model) {
                mvpView.getNewsSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.getNewsFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}

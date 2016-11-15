package com.wildwolf.mydzh.presenter;

import com.wildwolf.mydzh.base.BasePresenter;
import com.wildwolf.mydzh.model.NewsDetailsEntity;
import com.wildwolf.mydzh.model.NewsEntity;
import com.wildwolf.mydzh.net.ApiCallBack;
import com.wildwolf.mydzh.ui.activity.NewsDetailsActivity;
import com.wildwolf.mydzh.view.NewsDetailsView;

/**
 * Created by ${wild00wolf} on 2016/11/15.
 */
public class NewsDetailsPresenter extends BasePresenter<NewsDetailsView>{


    public NewsDetailsPresenter(NewsDetailsView view) {
        attachView(view);
    }

    public void getDetailNews(String id) {
        mvpView.showLoading();
        addSubscription(zhihuApiStores.getDetailNews(id), new ApiCallBack<NewsDetailsEntity>() {
            @Override
            public void onSuccess(NewsDetailsEntity model) {
                mvpView.getNewsDetailsSuccess(model);
            }

            @Override
            public void onFailure(String msg) {
                mvpView.getNewsDetailsFail(msg);
            }

            @Override
            public void onFinish() {
                mvpView.hideLoading();
            }
        });
    }
}

package com.wildwolf.mydzh.base;

import com.wildwolf.mydzh.model.VideoEntity;
import com.wildwolf.mydzh.net.ApiCallBack;
import com.wildwolf.mydzh.net.AppClient;
import com.wildwolf.mydzh.net.VideoApiStores;
import com.wildwolf.mydzh.net.ZhihuApiStores;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${wild00wolf} on 2016/11/14.
 */
public class BasePresenter<V> {

    public V mvpView;
    protected VideoApiStores videoapiStores;
    protected ZhihuApiStores zhihuApiStores;
    private CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        videoapiStores = AppClient.getVideoRetrofit().create(VideoApiStores.class);
        zhihuApiStores = AppClient.getZhiHURetrofit().create(ZhihuApiStores.class);
    }


    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }


    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}

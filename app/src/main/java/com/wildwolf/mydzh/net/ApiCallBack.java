package com.wildwolf.mydzh.net;

import android.text.TextUtils;

import com.wildwolf.mydzh.base.MyApplication;
import com.wildwolf.mydzh.utils.LogUtil;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by ${wild00wolf} on 2016/11/14.
 */
public abstract class ApiCallBack<M> extends Subscriber<M> {

    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        String msg;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            msg = httpException.getMessage();

            LogUtil.d("code=" + code);
            if (code == 504) {
                msg = "网络不给力";
            }
            if (code == 502 || code == 404) {
                msg = "服务器异常，请稍后再试";
            }
        } else {
            msg = e.getMessage();
        }
        if (!MyApplication.isNetworkAvailable(MyApplication.getInstance())) {
            msg = "请检查网络连接";
        }
        if (!TextUtils.isEmpty(msg)) {
            onFailure(msg);
        }
        onFinish();
    }

    @Override
    public void onNext(M m) {
        onSuccess(m);
    }
}

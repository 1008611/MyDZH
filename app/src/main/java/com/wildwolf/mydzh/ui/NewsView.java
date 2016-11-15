package com.wildwolf.mydzh.ui;

import com.wildwolf.mydzh.base.BaseView;
import com.wildwolf.mydzh.model.NewsEntity;

/**
 * Created by ${wild00wolf} on 2016/11/15.
 */
public interface NewsView extends BaseView {
    void getNewsSuccess(NewsEntity newsEntity);

    void getNewsFail(String msg);
}

package com.wildwolf.mydzh.view;

import com.wildwolf.mydzh.base.BaseView;
import com.wildwolf.mydzh.model.NewsDetailsEntity;

/**
 * Created by ${wild00wolf} on 2016/11/15.
 */
public interface NewsDetailsView extends BaseView {
    void getNewsDetailsSuccess(NewsDetailsEntity newsEntity);

    void getNewsDetailsFail(String msg);
}

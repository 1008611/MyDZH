package com.wildwolf.mydzh.ui;

import com.wildwolf.mydzh.base.BaseView;
import com.wildwolf.mydzh.model.VideoEntity;

import java.util.List;

/**
 * Created by ${wild00wolf} on 2016/11/14.
 */
public interface MainView extends BaseView {

    void getVideoSuccess(List<VideoEntity> videoEntity);

    void getVideoFail(String msg);
}

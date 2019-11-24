package com.newshandler.view;

import com.newshandler.model.entities.FeedItem;

import java.util.List;

public interface DataCallBack {
    void  onError();
    void onFinish(List<FeedItem> list);
}
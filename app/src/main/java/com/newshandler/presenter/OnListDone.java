package com.newshandler.presenter;

import com.newshandler.model.entities.FeedItem;

import java.util.List;

public interface OnListDone {
    void OnReceived (List<FeedItem> list);
}
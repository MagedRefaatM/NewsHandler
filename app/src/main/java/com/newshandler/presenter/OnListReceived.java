package com.newshandler.presenter;

import com.newshandler.model.entities.FeedItem;

public interface OnListReceived {
    void onReceive (FeedItem feedItem);
}
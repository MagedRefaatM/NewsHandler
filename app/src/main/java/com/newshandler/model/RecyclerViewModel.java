package com.newshandler.model;

import com.newshandler.model.entities.FeedItem;

import java.util.List;

public class RecyclerViewModel {

    private List<FeedItem> list;

    public RecyclerViewModel(List<FeedItem> list) {
        setList(list);
    }

    public List<FeedItem> getList() {
        return list;
    }

    private void setList(List<FeedItem> list) {
        if (list.size() != 0)
            this.list = list;
    }
}
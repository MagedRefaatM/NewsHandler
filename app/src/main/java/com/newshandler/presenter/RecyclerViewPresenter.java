package com.newshandler.presenter;

import com.newshandler.model.RecyclerViewModel;
import com.newshandler.model.entities.FeedItem;
import com.newshandler.view.AddingToRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPresenter implements OnListDone{

    private String srcLink;

    private List<FeedItem> list = new ArrayList<>();

    private AddingToRecyclerView addingToRecycler;

    public RecyclerViewPresenter(String srcLink, AddingToRecyclerView addingToRecyclerView) {
        setSrcLink(srcLink);
        setAddingToRecycler(addingToRecyclerView); }

    public List<FeedItem> getList() { return list; }

    private AddingToRecyclerView getAddingToRecycler() { return addingToRecycler; }

    private String getSrcLink() { return srcLink; }

    public void setSrcLink(String srcLink) { this.srcLink = srcLink; }

    public void setList(List<FeedItem> list) { this.list = list; }

    private void setAddingToRecycler(AddingToRecyclerView addingToRecyclerView) {
        if (addingToRecyclerView != null)
            addingToRecycler = addingToRecyclerView;
    }

    @Override
    public void OnReceived(List<FeedItem> list) {
        RecyclerViewModel model = new RecyclerViewModel(list);
        setList(model.getList());
        getAddingToRecycler().IsListReceived(getList());
    }
}
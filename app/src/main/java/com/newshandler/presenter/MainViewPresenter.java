package com.newshandler.presenter;

import android.content.Context;

import com.newshandler.model.MainViewModel;
import com.newshandler.model.MyDBHandler;
import com.newshandler.model.entities.UITalker;
import com.newshandler.view.AddingMainView;

import java.util.List;

public class MainViewPresenter implements OnMainAdding , OnCheckingLink , OnPositionSelect , OnApplicationStarts , OnListRequired , DeleteRecord {

    private MyDBHandler dbHandler;
//    private RSSFeedDatabase database;
    private AddingMainView addingMainView;

    private String srcName;
    private String srcLink;

    private int id;

    private List<UITalker> list;

    public MainViewPresenter(Context context , AddingMainView addingMainView) {
        this.addingMainView = addingMainView;
        dbHandler = new MyDBHandler(context);
//        database = Room.databaseBuilder(context , RSSFeedDatabase.class , "news_db").build();
    }

    public String getSrcName() {
        return srcName;
    }

    public String getSrcLink() {
        return srcLink;
    }

    public int getId() {
        return id;
    }

    public List<UITalker> getList() {
        return list;
    }

    private void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    private void setSrcLink(String srcLink) {
        this.srcLink = srcLink;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setList(List<UITalker> list) {
        this.list = list;
    }

    private void validateNameAndLink(String Name , String Link){
        if (Name.isEmpty() || Link.isEmpty()) {
            addingMainView.isNameValid(false);
            addingMainView.isLinkValid(false);
            addingMainView.isAddingCorrect(false , false);
        } else {
            addingMainView.isNameValid(true);
            addingMainView.isLinkValid(true);

            if (OnCheck(Link)) {
                MainViewModel model = new MainViewModel(Name , Link);
                setSrcName(model.getName());
                setSrcLink(model.getLink());
                addingMainView.isLinkExist(false);
                setId((int) dbHandler.addSite(getSrcName(), getSrcLink()));
                addingMainView.isAddingCorrect(true, true);
            } else
                addingMainView.isLinkExist(true);
        }
    }

    @Override
    public void onAddition(String Name, String Link) {
        validateNameAndLink(Name , Link);
    }

    @Override
    public boolean OnCheck(String srcLink) {
        if (dbHandler.CheckIfExist(srcLink) == true)
            return false;
        else
            return true;
    }

    @Override
    public void OnSelect(int position) {
        setSrcLink(dbHandler.getAllSites().get(position).getSrcURL());
    }

    @Override
    public void OnStart(List<UITalker> list) {
        setList(list);
    }

    @Override
    public void onRequired() {
        setList(dbHandler.getAllSites());
    }

    @Override
    public void delete(int id) {
        dbHandler.deleteNews(id);
    }
}
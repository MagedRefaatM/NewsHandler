package com.newshandler.model.entities;

public class UITalker {

    private int id;
    private String srcName;
    private String srcURL;

    public UITalker() {
    }

    public UITalker(int id, String srcName, String srcURL) {
        this.id = id;
        this.srcName = srcName;
        this.srcURL = srcURL;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcURL(String srcURL) {
        this.srcURL = srcURL;
    }

    public String getSrcURL() {
        return srcURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

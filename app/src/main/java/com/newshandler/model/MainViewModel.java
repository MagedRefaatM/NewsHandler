package com.newshandler.model;

public class MainViewModel {

    private String Name = "";
    private String Link = "";

    public MainViewModel(String name, String link) {
        setName(name);
        setLink(link);
    }

    public String getName() {
        return Name;
    }

    public String getLink() {
        return Link;
    }

    private void setName(String name) {
        if (name != null)
            Name = name;
    }

    private void setLink(String link) {
        if (link != null)
            Link = link;
    }
}

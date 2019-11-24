package com.newshandler.model;

public class NewsContentsModel {

    private String imageLink;
    private String Title;
    private String Link;
    private String Source;
    private String Description;
    private String pubDate;

    public NewsContentsModel(String imageLink, String title, String link, String source, String description, String pubDate) {
        setImageLink(imageLink);
        setTitle(title);
        setLink(link);
        setSource(source);
        setDescription(description);
        setPubDate(pubDate);
    }

    public String getImageLink() { return imageLink; }

    public String getTitle() { return Title; }

    public String getLink() { return Link; }

    public String getSource() { return Source; }

    public String getDescription() { return Description; }

    public String getPubDate() { return pubDate; }

    private void setImageLink(String imageLink) {
        if (imageLink != null)
            this.imageLink = imageLink;
    }

    private void setTitle(String title) {
        if (title != null)
            Title = title;
    }

    private void setLink(String link) {
        if (link != null)
            Link = link;
    }

    private void setSource(String source) {
        if (source != null)
            Source = source;
    }

    private void setDescription(String description) {
        if (description != null)
            Description = description;
    }

    private void setPubDate(String pubDate) {
        if (pubDate != null)
            this.pubDate = pubDate;
    }
}
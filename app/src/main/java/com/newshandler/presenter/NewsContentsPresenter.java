package com.newshandler.presenter;

import com.newshandler.model.NewsContentsModel;
import com.newshandler.model.entities.FeedItem;
import com.newshandler.view.AddingToNewsPage;

public class NewsContentsPresenter implements OnFullNews , OnListReceived {

    private String imageUrl;
    private String Title;
    private String newsLink;
    private String Description;
    private String publishDate;
    private String Source;

    private AddingToNewsPage addingToNewsPage;

    public NewsContentsPresenter(AddingToNewsPage addingToNewsPage) {
        setAddingToNewsPage(addingToNewsPage);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return Title;
    }

    public String getNewsLink() {
        return newsLink;
    }

    public String getDescription() {
        return Description;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getSource() {
        return Source;
    }

    private AddingToNewsPage getAddingToNewsPage() {
        return addingToNewsPage;
    }

    private void setImageUrl(String imageUrl) {
        if (imageUrl != null)
            this.imageUrl = imageUrl;
    }

    private void setTitle(String title) {
        if (title != null)
            Title = title;
    }

    private void setNewsLink(String newsLink) {
        if (newsLink != null)
            this.newsLink = newsLink;
    }

    private void setDescription(String description) {
        if (description != null)
            Description = description;
    }

    private void setPublishDate(String publishDate) {
        if (publishDate != null)
            this.publishDate = publishDate;
    }

    private void setSource(String source) {
        if (source != null)
            Source = source;
    }

    private void setAddingToNewsPage(AddingToNewsPage addingToNewsPage) {
        if (addingToNewsPage != null)
            this.addingToNewsPage = addingToNewsPage;
    }

    @Override
    public void FullNewsContents(String imageLink, String Title, String newsLink, String Source, String Description, String publishDate) {
        NewsContentsModel model = new NewsContentsModel(imageLink , Title , newsLink , Source , Description , publishDate);
        setImageUrl(model.getImageLink());
        setTitle(model.getTitle());
        setNewsLink(model.getLink());
        setSource(model.getSource());
        setDescription(model.getDescription());
        setPublishDate(model.getPubDate());
    }

    @Override
    public void onReceive(FeedItem feedItem) {
        getAddingToNewsPage().receivedList(feedItem);
    }
}
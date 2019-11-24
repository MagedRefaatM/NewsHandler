package com.newshandler.model.entities;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.io.Serializable;

@Root(name = "item")
public class FeedItem implements Serializable {

    @Text(required = false)
    @Element(name = "title")
    private String itemTitle;

    @Text(required = false)
    @Element(name = "link")
    private String itemLink;

    @Text(required = false)
    @Element(name = "description")
    private String itemDescription;

    @Element(name = "enclosure", required = false)
    private ImageLink itemImageLink;

    @Text(required = false)
    @Element(name = "pubDate")
    private String itemPublishDate;

    @Text(required = false)
    @Element(name = "source", required = false)
    private String itemSource;

    @Attribute(name = "dc:identifier", required = false)
    private int itemId;

    public FeedItem() {
    }

    public String getItemImageLink() {
        if (itemImageLink == null)
            return null;

        return itemImageLink.getUrl();
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public String getItemLink() {
        return itemLink;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemPublishDate() {
        return itemPublishDate;
    }

    public String getItemSource() {
        return itemSource;
    }
}
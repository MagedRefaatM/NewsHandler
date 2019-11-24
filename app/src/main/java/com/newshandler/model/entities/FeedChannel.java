package com.newshandler.model.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.util.ArrayList;
import java.util.List;

@Root(name = "channel" , strict = false)
public class FeedChannel {

    @Element(name = "title")
    @Text(required = false)
    private String channelTitle;

    @Path("link")
    @Text(required = false)
    private String channelLink;

    @Element(name = "description")
    @Text(required = false)
    private String channelDescription;

    @Element(name = "language")
    @Text(required = false)
    private String channelLanguage;

    @ElementList(inline = true, required = false)
    private List<FeedItem> channelItemList = new ArrayList<>();

    public FeedChannel() {
    }

    public List<FeedItem> getChannelItemList() {
        return channelItemList;
    }
}
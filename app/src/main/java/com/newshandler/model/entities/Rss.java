package com.newshandler.model.entities;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "rss" , strict = false)
public class Rss {

    @Element(name = "channel", required = false)
    FeedChannel channel;

    public FeedChannel getChannel() {
        return channel;
    }
}

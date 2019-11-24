package com.newshandler.model.entities;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "enclosure")
class ImageLink {

    @Text(required = false)
    @Attribute(name = "url", empty = "")

    private String url;

//    @Text(required = false)
//    @Path("length")
//    private long length;

//    @Text(required = false)
//    @Path("type")
//    private String type;

    String getUrl() {
        return url;
    }
}
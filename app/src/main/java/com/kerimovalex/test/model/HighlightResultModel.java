package com.kerimovalex.test.model;

import com.google.gson.annotations.SerializedName;

public class HighlightResultModel {
    @SerializedName("title")
    private TitleModel title;
    @SerializedName("url")
    private UrlModel url;
    @SerializedName("author")
    private AuthorModel author;

    public TitleModel getTitle() {
        return title;
    }

    public UrlModel getUrl() {
        return url;
    }

    public AuthorModel getAuthor() {
        return author;
    }
}

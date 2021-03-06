package com.kerimovalex.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AuthorModel {
    @SerializedName("value")
    private String value;
    @SerializedName("matchLevel")
    private String matchLevel;
    @SerializedName("matchedWords")
    private List<Object> matchedWords = null;

    public String getValue() {
        return value;
    }

    public String getMatchLevel() {
        return matchLevel;
    }

    public List<Object> getMatchedWords() {
        return matchedWords;
    }
}

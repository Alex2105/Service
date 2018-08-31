package com.kerimovalex.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HitModel {
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("title")
    private String title;
    @SerializedName("url")
    private String url;
    @SerializedName("author")
    private String author;
    @SerializedName("points")
    private Integer points;
    @SerializedName("story_text")
    private Object storyText;
    @SerializedName("comment_text")
    private Object commentText;
    @SerializedName("num_comments")
    private Integer numComments;
    @SerializedName("story_id")
    private Object storyId;
    @SerializedName("story_title")
    private Object storyTitle;
    @SerializedName("story_url")
    private Object storyUrl;
    @SerializedName("parent_id")
    private Object parentId;
    @SerializedName("created_at_i")
    private Integer createdAtI;
    @SerializedName("_tags")
    private List<String> tags = null;
    @SerializedName("objectID")
    private String objectID;
    @SerializedName("_highlightResult")
    private HighlightResultModel highlightResult;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getPoints() {
        return points;
    }

    public Object getStoryText() {
        return storyText;
    }

    public Object getCommentText() {
        return commentText;
    }

    public Integer getNumComments() {
        return numComments;
    }

    public Object getStoryId() {
        return storyId;
    }

    public Object getStoryTitle() {
        return storyTitle;
    }

    public Object getStoryUrl() {
        return storyUrl;
    }

    public Object getParentId() {
        return parentId;
    }

    public Integer getCreatedAtI() {
        return createdAtI;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getObjectID() {
        return objectID;
    }

    public HighlightResultModel getHighlightResult() {
        return highlightResult;
    }
}

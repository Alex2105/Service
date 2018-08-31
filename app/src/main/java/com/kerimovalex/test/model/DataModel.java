package com.kerimovalex.test.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataModel {
    @SerializedName("hits")
    private List<HitModel> hits = null;
    @SerializedName("nbHits")
    private Integer nbHits;
    @SerializedName("page")
    private Integer page;
    @SerializedName("nbPages")
    private Integer nbPages;
    @SerializedName("hitsPerPage")
    private Integer hitsPerPage;
    @SerializedName("processingTimeMS")
    private Integer processingTimeMS;
    @SerializedName("exhaustiveNbHits")
    private Boolean exhaustiveNbHits;
    @SerializedName("query")
    private String query;
    @SerializedName("params")
    private String params;

    public List<HitModel> getHits() {
        return hits;
    }

    public Integer getNbHits() {
        return nbHits;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getNbPages() {
        return nbPages;
    }

    public Integer getHitsPerPage() {
        return hitsPerPage;
    }

    public Integer getProcessingTimeMS() {
        return processingTimeMS;
    }

    public Boolean getExhaustiveNbHits() {
        return exhaustiveNbHits;
    }

    public String getQuery() {
        return query;
    }

    public String getParams() {
        return params;
    }
}

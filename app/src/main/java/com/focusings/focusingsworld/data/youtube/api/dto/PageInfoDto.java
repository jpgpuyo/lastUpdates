package com.focusings.focusingsworld.data.youtube.api.dto;

import com.google.gson.annotations.SerializedName;

public class PageInfoDto {

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("resultsPerPage")
    private int resultsPerPage;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getResultsPerPage() {
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
    }
}

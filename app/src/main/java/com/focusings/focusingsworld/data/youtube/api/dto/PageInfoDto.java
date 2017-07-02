package com.focusings.focusingsworld.data.youtube.api.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by usuario on 04/04/2017.
 */

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

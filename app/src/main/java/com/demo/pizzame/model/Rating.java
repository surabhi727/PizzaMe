package com.demo.pizzame.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Surabhi on 7/29/17.
 */

public class Rating implements Serializable
{
    @SerializedName("LastReviewIntro")
    private String lastReviewIntro;

    @SerializedName("TotalReviews")
    private String totalReviews;

    @SerializedName("TotalRatings")
    private String totalRatings;

    @SerializedName("LastReviewDate")
    private long lastReviewDate;

    @SerializedName("AverageRating")
    private String averageRating;

    public String getLastReviewIntro ()
{
    return lastReviewIntro;
}

    public void setLastReviewIntro (String lastReviewIntro)
    {
        this.lastReviewIntro = lastReviewIntro;
    }

    public String getTotalReviews ()
    {
        return totalReviews;
    }

    public void setTotalReviews (String totalReviews)
    {
        this.totalReviews = totalReviews;
    }

    public String getTotalRatings ()
    {
        return totalRatings;
    }

    public void setTotalRatings (String totalRatings)
    {
        this.totalRatings = totalRatings;
    }

    public long getLastReviewDate ()
{
    return lastReviewDate;
}

    public void setLastReviewDate (long lastReviewDate)
    {
        this.lastReviewDate = lastReviewDate;
    }

    public String getAverageRating ()
    {
        return averageRating;
    }

    public void setAverageRating (String averageRating)
    {
        this.averageRating = averageRating;
    }

}

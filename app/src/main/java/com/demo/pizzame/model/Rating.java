package com.demo.pizzame.model;

import java.io.Serializable;

/**
 * Created by Surabhi on 7/29/17.
 */

public class Rating implements Serializable
{
    private String LastReviewIntro;

    private String TotalReviews;

    private String TotalRatings;

    private long LastReviewDate;

    private String AverageRating;

    public String getLastReviewIntro ()
{
    return LastReviewIntro;
}

    public void setLastReviewIntro (String LastReviewIntro)
    {
        this.LastReviewIntro = LastReviewIntro;
    }

    public String getTotalReviews ()
    {
        return TotalReviews;
    }

    public void setTotalReviews (String TotalReviews)
    {
        this.TotalReviews = TotalReviews;
    }

    public String getTotalRatings ()
    {
        return TotalRatings;
    }

    public void setTotalRatings (String TotalRatings)
    {
        this.TotalRatings = TotalRatings;
    }

    public long getLastReviewDate ()
{
    return LastReviewDate;
}

    public void setLastReviewDate (long LastReviewDate)
    {
        this.LastReviewDate = LastReviewDate;
    }

    public String getAverageRating ()
    {
        return AverageRating;
    }

    public void setAverageRating (String AverageRating)
    {
        this.AverageRating = AverageRating;
    }

}

package com.demo.pizzame.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Surabhi on 7/29/17.
 */

public class Rating implements Parcelable
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

    public Rating(Parcel in) {
        this.lastReviewIntro = in.readString();
        this.totalReviews = in.readString();
        this.totalRatings = in.readString();
        this.lastReviewDate = in.readLong();
        this.averageRating = in.readString();

    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lastReviewIntro);
        dest.writeString(totalReviews);
        dest.writeString(totalRatings);
        dest.writeLong(lastReviewDate);
        dest.writeString(averageRating);

    }

    public static final Creator<Rating> CREATOR = new Creator<Rating>() {
        @Override
        public Rating createFromParcel(Parcel source) {
            return new Rating(source);
        }

        @Override
        public Rating[] newArray(int size) {
            return new Rating[size];
        }
    };
}

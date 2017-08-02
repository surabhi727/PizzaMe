package com.demo.pizzame.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Surabhi on 7/29/17.
 */

public class Result implements Serializable {

    @SerializedName("Phone")
    private String phone;

    @SerializedName("Distance")
    private String distance;

    @SerializedName("Title")
    private String title;

    @SerializedName("City")
    private String city;

    @SerializedName("BusinessClickUrl")
    private String businessClickUrl;

    @SerializedName("State")
    private String state;

    @SerializedName("Rating")
    private Rating rating;

    @SerializedName("Address")
    private String address;

    @SerializedName("Latitude")
    private String latitude;

    @SerializedName("Longitude")
    private String longitude;

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String Phone)
    {
        this.phone = Phone;
    }

    public String getDistance ()
    {
        return distance;
    }

    public void setDistance (String Distance)
    {
        this.distance = Distance;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String Title)
    {
        this.title = Title;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getBusinessClickUrl ()
    {
        return businessClickUrl;
    }

    public void setBusinessClickUrl (String businessClickUrl)
    {
        this.businessClickUrl = businessClickUrl;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String State)
    {
        this.state = State;
    }

    public Rating getRating ()
    {
        return rating;
    }

    public void setRating (Rating rating)
    {
        this.rating = rating;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public double getLatitude ()
    {
        return Double.valueOf(latitude);
    }
    public String getLatitudeString() {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude ()
    {
        return Double.valueOf(longitude);
    }

    public String getLongitudeString() {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getFullAddress() {
        return this.address + ", " + this.city + ", " + this.state;
    }
}

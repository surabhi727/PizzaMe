package com.demo.pizzame.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Surabhi on 7/29/17.
 */

public class PizzaPlace implements Parcelable {

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

    public PizzaPlace(Parcel in) {
        this.phone = in.readString();
        this.distance = in.readString();
        this.title = in.readString();
        this.city = in.readString();
        this.businessClickUrl = in.readString();
        this.state = in.readString();
        this.rating = in.readParcelable(getClass().getClassLoader());
        this.address = in.readString();
        this.latitude = in.readString();
        this.longitude = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phone);
        dest.writeString(distance);
        dest.writeString(title);
        dest.writeString(city);
        dest.writeString(businessClickUrl);
        dest.writeString(state);
        dest.writeParcelable(rating,flags);
        dest.writeString(address);
        dest.writeString(latitude);
        dest.writeString(longitude);

    }

    public static final Creator<PizzaPlace> CREATOR = new Creator<PizzaPlace>() {
        @Override
        public PizzaPlace createFromParcel(Parcel source) {
            return new PizzaPlace(source);
        }

        @Override
        public PizzaPlace[] newArray(int size) {
            return new PizzaPlace[size];
        }
    };
}

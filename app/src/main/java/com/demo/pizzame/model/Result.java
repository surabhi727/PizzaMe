package com.demo.pizzame.model;

import java.io.Serializable;

/**
 * Created by Surabhi on 7/29/17.
 */

public class Result implements Serializable {
    private String Phone;

    private String Distance;

    private String MapUrl;

    private String xmlns;

    private String Title;

    private String City;

    private String BusinessClickUrl;

    private String ClickUrl;

    private String id;

    private String BusinessUrl;

    private String State;

    private String Url;

    private Rating Rating;

    private String Address;

    private String Latitude;

    private String Longitude;

    public String getPhone ()
    {
        return Phone;
    }

    public void setPhone (String Phone)
    {
        this.Phone = Phone;
    }

    public String getDistance ()
    {
        return Distance;
    }

    public void setDistance (String Distance)
    {
        this.Distance = Distance;
    }

    public String getMapUrl ()
    {
        return MapUrl;
    }

    public void setMapUrl (String MapUrl)
    {
        this.MapUrl = MapUrl;
    }

    public String getXmlns ()
    {
        return xmlns;
    }

    public void setXmlns (String xmlns)
    {
        this.xmlns = xmlns;
    }

    public String getTitle ()
    {
        return Title;
    }

    public void setTitle (String Title)
    {
        this.Title = Title;
    }

    public String getCity ()
    {
        return City;
    }

    public void setCity (String City)
    {
        this.City = City;
    }

    public String getBusinessClickUrl ()
    {
        return BusinessClickUrl;
    }

    public void setBusinessClickUrl (String BusinessClickUrl)
    {
        this.BusinessClickUrl = BusinessClickUrl;
    }

    public String getClickUrl ()
    {
        return ClickUrl;
    }

    public void setClickUrl (String ClickUrl)
    {
        this.ClickUrl = ClickUrl;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getBusinessUrl ()
    {
        return BusinessUrl;
    }

    public void setBusinessUrl (String BusinessUrl)
    {
        this.BusinessUrl = BusinessUrl;
    }

    public String getState ()
    {
        return State;
    }

    public void setState (String State)
    {
        this.State = State;
    }

    public String getUrl ()
    {
        return Url;
    }

    public void setUrl (String Url)
    {
        this.Url = Url;
    }

    public Rating getRating ()
    {
        return Rating;
    }

    public void setRating (Rating Rating)
    {
        this.Rating = Rating;
    }

    public String getAddress ()
    {
        return Address;
    }

    public void setAddress (String Address)
    {
        this.Address = Address;
    }

    public double getLatitude ()
    {
        return Double.valueOf(Latitude);
    }
    public String getLatitudeString() {
        return Latitude;
    }

    public void setLatitude (String Latitude)
    {
        this.Latitude = Latitude;
    }

    public double getLongitude ()
    {
        return Double.valueOf(Longitude);
    }

    public String getLongitudeString() {
        return Longitude;
    }

    public void setLongitude (String Longitude)
    {
        this.Longitude = Longitude;
    }

    public String getFullAddress() {
        return this.Address + ", " + this.City + ", " + this.State;
    }
}

package com.demo.pizzame.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Surabhi on 7/29/17.
 */

public class Results
{
    @SerializedName("Result")
    private PizzaPlace[] placeList;

    public PizzaPlace[] getPlaceList()
    {
        return placeList;
    }

    public void setPizzaPlace(PizzaPlace[] placeList)
    {
        this.placeList = placeList;
    }

}

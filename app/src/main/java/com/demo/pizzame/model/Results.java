package com.demo.pizzame.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Surabhi on 7/29/17.
 */

public class Results
{
    @SerializedName("Result")
    private Result[] result;

    public Result[] getResult ()
    {
        return result;
    }

    public void setResult (Result[] result)
    {
        this.result = result;
    }

}

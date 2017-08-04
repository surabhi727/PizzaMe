package com.demo.pizzame;

import com.demo.pizzame.model.QueryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Surabhi on 7/29/17.
 */

public interface LocalSearchAPI {
    @GET("/v1/public/yql/?format=json&sort=distance")
    Call<QueryResponse> getNearByPizzaPlaces(@Query("q") String query);
}

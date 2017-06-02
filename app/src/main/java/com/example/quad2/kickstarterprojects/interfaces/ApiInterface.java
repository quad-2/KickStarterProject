package com.example.quad2.kickstarterprojects.interfaces;

import com.example.quad2.kickstarterprojects.pojo.ApiDatum;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by quad2 on 31/5/17.
 */

public interface ApiInterface {

    @GET("kickstarter")
    Call<List<ApiDatum>> getData();
}

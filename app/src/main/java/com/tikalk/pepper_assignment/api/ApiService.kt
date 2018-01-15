package com.tikalk.pepper_assignment.api

import com.tikalk.pepper_assignment.model.group.CitiesResponse
import com.tikalk.pepper_assignment.model.single.ApiResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by motibartov on 14/01/2018.
 */
interface ApiService {

    @GET("group")
    fun getGroupWeather(@Query("id") cities: String, @Query("units") units: String = "metric" ) : Flowable<CitiesResponse>


    @GET("forecast/daily")
    fun getForecast(@Query("id") location: String, @Query("cnt") period: Int, @Query("units") units: String = "metric" ) : Flowable<ApiResponse>
}
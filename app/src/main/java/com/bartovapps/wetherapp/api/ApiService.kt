package com.bartovapps.wetherapp.api

import com.bartovapps.wetherapp.model.global.ApiResponseForGlobal
import com.bartovapps.wetherapp.model.local.ApiResponseForLocation
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by motibartov on 14/01/2018.
 */
interface ApiService {

    @GET("group")
    fun getGlobalWeather(@Query("id") cities: String, @Query("units") units: String = "metric" ) : Flowable<ApiResponseForGlobal>


    @GET("forecast/daily")
    fun getLocalWeather(@Query("id") location: String, @Query("cnt") period: Int, @Query("units") units: String = "metric" ) : Flowable<ApiResponseForLocation>
}
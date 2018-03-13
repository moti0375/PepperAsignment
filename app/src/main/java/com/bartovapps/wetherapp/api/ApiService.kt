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


    companion object {
        val cities = HashMap<String, String>()
//        val cities = "2643743,293397,5128581,2800866,3128760,2988507,1850147,1816670,2147714,3432043,4164138,6173331,524901,1609350,993800,2464470,1701668"

        init {
            cities.put("London", "2643743")
            cities.put("Tel Aviv", "293397")
            cities.put("New York", "5128581")
            cities.put("Brussels", "2800866")
            cities.put("Barcelona", "3128760")
            cities.put("Paris", "2988507")
            cities.put("Tokyo", "1850147")
            cities.put("Beijing", "1816670")
            cities.put("Sydney", "2147714")
            cities.put("La Plata", "3432043")
            cities.put("Miami", "4164138")
            cities.put("Vancouver", "6173331")
            cities.put("Moscow", "524901")
            cities.put("Bangkok", "1609350")
            cities.put("Johannesburg", "993800")
            cities.put("Tunis", "2464470")
            cities.put("Manila", "1701668")
        }
    }


    @GET("forecast")
    fun getGlobalWeather(@Query("id") cities: String, @Query("cnt") period : Int, @Query("units") units: String = "metric" ) : Flowable<ApiResponseForGlobal>


    @GET("forecast/daily")
    fun getLocalWeather(@Query("id") location: String, @Query("cnt") period: Int, @Query("units") units: String = "metric" ) : Flowable<ApiResponseForLocation>
}
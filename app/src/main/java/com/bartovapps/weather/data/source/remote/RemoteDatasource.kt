package com.bartovapps.weather.data.source.remote

import com.bartovapps.weather.api.ApiService
import com.bartovapps.weather.data.source.BaseDatasource
import com.bartovapps.weather.model.global.GlobalForecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */

class RemoteDatasource (val apiService: ApiService) : BaseDatasource{


    override fun getLocalWeather(location: String, period: Int) : Flowable<List<com.bartovapps.weather.model.local.LocalForecast>>{
        val map = hashMapOf<String, String>()
        return apiService.getLocalWeather(location, period).map{
            it -> it.forecast
        }
    }


    override fun getGlobalWeather(c: String, period: Int): Flowable<List<GlobalForecast>> {
        return apiService.getGlobalWeather(c, period).map {
            it -> it.dailyForecast
        }
    }
}
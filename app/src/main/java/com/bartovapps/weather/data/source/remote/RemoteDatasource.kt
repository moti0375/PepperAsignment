package com.bartovapps.weather.data.source.remote

import com.bartovapps.weather.api.ApiService
import com.bartovapps.weather.data.source.BaseDatasource
import com.bartovapps.weather.model.daily_forecast.DailyForecast
import com.bartovapps.weather.model.forecast.Forecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */

class RemoteDatasource (val apiService: ApiService) : BaseDatasource{

    override fun fetchForecast(location: String, period: Int) : Flowable<List<Forecast>>{
        val map = hashMapOf<String, String>()
        return apiService.fetchForecast(location, period).map{
            it -> it.forecast
        }
    }

    override fun fetchDailyForecast(c: String, period: Int): Flowable<List<DailyForecast>> {
        return apiService.fetchDailyForecast(c, period).map {
            it -> it.dailyForecast
        }
    }

    override fun insertForecast(forecast: List<Forecast>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertDailyForecast(dailyForecast: List<DailyForecast>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteForecast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
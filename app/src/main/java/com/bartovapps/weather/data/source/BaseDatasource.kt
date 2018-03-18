package com.bartovapps.weather.data.source

import android.arch.lifecycle.LiveData
import com.bartovapps.weather.model.daily_forecast.DailyForecast
import com.bartovapps.weather.model.forecast.Forecast
import io.reactivex.Flowable


interface BaseDatasource {
    fun fetchForecast(location: String, period: Int) : Flowable<List<Forecast>>
    fun fetchDailyForecast(location: String, period: Int) : Flowable<List<DailyForecast>>
    fun insertForecast(forecast: List<Forecast>)
    fun insertDailyForecast(dailyForecast: List<DailyForecast>)
    fun deleteForecast()
}
package com.bartovapps.weather.data.source.local

import android.provider.ContactsContract
import com.bartovapps.weather.data.source.BaseDatasource
import com.bartovapps.weather.model.forecast.Forecast
import com.bartovapps.weather.model.daily_forecast.DailyForecast
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Callable

/**
 * Created by motibartov on 14/01/2018.
 */
class LocalDatasource (private val weatherDao: WeatherDao?): BaseDatasource{

    override fun fetchForecast(location: String, period : Int) : Flowable<List<Forecast>>{
        return weatherDao!!.getForecast()
    }


    override fun fetchDailyForecast(location: String, period: Int): Flowable<List<DailyForecast>> {
        return weatherDao!!.getDailyForecast()
    }

    override fun insertDailyForecast(dailyForecast: List<DailyForecast>) {
        weatherDao?.insertDailyForecast(dailyForecast)
    }

    override fun deleteForecast() {
        Observable.fromCallable {
            weatherDao?.deleteAllDailyForecast()
            weatherDao?.deleteAllForecast()
        }.subscribeOn(Schedulers.io()).subscribe()
    }

    override fun insertForecast(forecast: List<Forecast>) {
        weatherDao?.insertForefcast(forecast)
    }

}
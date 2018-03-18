package com.bartovapps.weather.data

import android.util.Log
import android.util.Log.i
import com.bartovapps.weather.data.source.BaseDatasource
import com.bartovapps.weather.data.source.local.LocalDatasource
import com.bartovapps.weather.data.source.remote.RemoteDatasource
import com.bartovapps.weather.model.daily_forecast.DailyForecast
import com.bartovapps.weather.model.forecast.Forecast
import io.reactivex.Flowable


class AppRepository (val remoteDatasource: RemoteDatasource, val localDatasource: LocalDatasource) : BaseDatasource{


    companion object {
        val TAG = "TAG_AppRepository"
    }

    val dailyCache = ArrayList<com.bartovapps.weather.model.daily_forecast.DailyForecast>()
    val forecastCache = ArrayList<Forecast>()
    var locationId: String? = null
    var period : Int = 5
    var cacheDirty : Boolean = true

    override fun fetchForecast(location : String, period: Int) : Flowable<List<Forecast>>{
        i(TAG, "fetchForecast: ")
        return if(!forecastCache.isEmpty() && location == locationId && this.period == period && !cacheDirty){
            Log.i(TAG, "Loading from forecastCache")
            Flowable.just(forecastCache)
        }else{
            Log.i(TAG, "Loading from local or remote")
            deleteForecast()
            locationId = location
            this.period = period
            loadForecastFromLocal(location, period)
        }
    }

    private fun loadForecastFromLocal(location: String, period: Int) : Flowable<List<Forecast>>{
        return localDatasource.fetchForecast(location, period).
                take(1).
                flatMap { list -> Flowable.fromIterable(list) }.
                doOnNext{
                    it -> forecastCache.add(it)
                }.
                toList().
                toFlowable().
                filter{
                    l -> !l.isEmpty()
                }.
                switchIfEmpty(loadForecastFromRemote(location, period))
    }


   private fun loadForecastFromRemote(location: String, period: Int) : Flowable<List<Forecast>>{
        return remoteDatasource.fetchForecast(location, period).
                doOnNext{
                    list -> forecastCache.clear()
                    forecastCache.addAll(list)
                }
    }

    override fun fetchDailyForecast(location: String, period: Int): Flowable<List<DailyForecast>> {
        i(TAG, "fetchDailyForecast: ")
        return if(!dailyCache.isEmpty() && location == locationId && this.period == period && !cacheDirty){
            Log.i(TAG, "Loading from forecastCache")
            Flowable.just(dailyCache)
        }else{
            Log.i(TAG, "Loading daily forecast from local or remote")
            locationId = location
            this.period = period
            return loadDailyFromDatasource(location, period)
        }

    }

    private fun loadDailyFromDatasource(location: String, period: Int) : Flowable<List<DailyForecast>>{
        return localDatasource.fetchDailyForecast(location, period).
                take(1).
                flatMap { list -> Flowable.fromIterable(list) }.
                doOnNext{
                    it -> dailyCache.add(it)
                }.toList().toFlowable().
                filter{
                    !it.isEmpty()
                }.switchIfEmpty(loadDailyFromRemote(location, period))
    }

    private fun loadDailyFromRemote(location: String, period: Int) : Flowable<List<DailyForecast>>{
        return remoteDatasource.fetchDailyForecast(location, period).doOnNext{
            it -> forecastCache.clear()
            dailyCache.addAll(it)
            insertDailyForecast(it)
            i(TAG, "loadDailyFromRemote: doOnNext")
        }
    }

    fun forceUpdate(){
        cacheDirty = true
    }

    override fun insertForecast(forecast: List<Forecast>) {
        localDatasource.insertForecast(forecast)
    }

    override fun insertDailyForecast(dailyForecast: List<DailyForecast>) {
        localDatasource.insertDailyForecast(dailyForecast)
    }

    override fun deleteForecast() {
        localDatasource.deleteForecast()
    }


}
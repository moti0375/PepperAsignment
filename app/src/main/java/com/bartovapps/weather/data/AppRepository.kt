package com.bartovapps.weather.data

import android.util.Log
import android.util.Log.i
import com.bartovapps.weather.data.source.BaseDatasource
import com.bartovapps.weather.data.source.local.LocalDatasource
import com.bartovapps.weather.data.source.remote.RemoteDatasource
import com.bartovapps.weather.model.global.GlobalForecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */
class AppRepository (val remoteDatasource: RemoteDatasource, val localDatasource: LocalDatasource) : BaseDatasource{


    companion object {
        val TAG = "TAG_AppRepository"
    }

    val cache = ArrayList<com.bartovapps.weather.model.local.LocalForecast>()
    val groupCache = ArrayList<GlobalForecast>()
    var locationId: String? = null
    var period : Int = 5

    override fun getLocalWeather(location : String, period: Int) : Flowable<List<com.bartovapps.weather.model.local.LocalForecast>>{
        return if(!cache.isEmpty() && location == locationId && this.period == period){
            Log.i(TAG, "Loading from cache")
            Flowable.just(cache)
        }else{
            Log.i(TAG, "Loading from local or remote")
            locationId = location
            this.period = period
            loadForecastFromLocal(location, period)
        }
    }

    private fun loadForecastFromLocal(location: String, period: Int) : Flowable<List<com.bartovapps.weather.model.local.LocalForecast>>{
        return localDatasource.getLocalWeather(location, period).
                take(1).
                flatMap { list -> Flowable.fromIterable(list) }.
                doOnNext{
                    it -> cache.add(it)
                }.
                toList().
                toFlowable().
                filter{
                    l -> !l.isEmpty()
                }.
                switchIfEmpty(loadForecastFromRemote(location, period))
    }


   private fun loadForecastFromRemote(location: String, period: Int) : Flowable<List<com.bartovapps.weather.model.local.LocalForecast>>{
        return remoteDatasource.getLocalWeather(location, period).
                doOnNext{
                    list -> cache.clear()
                    cache.addAll(list)
                }
    }

    override fun getGlobalWeather(cities: String, period: Int): Flowable<List<GlobalForecast>> {
        i(TAG, "getGlobalWeather:  called")
        return remoteDatasource.getGlobalWeather(cities, period).doOnNext{
            it -> groupCache.clear()
        groupCache.addAll(it)
        }
    }

}
package com.tikalk.pepper_assignment.data

import android.util.Log
import com.tikalk.pepper_assignment.data.source.BaseDatasource
import com.tikalk.pepper_assignment.data.source.local.LocalDatasource
import com.tikalk.pepper_assignment.data.source.remote.RemoteDatasource
import com.tikalk.pepper_assignment.model.group.City
import com.tikalk.pepper_assignment.model.single.Forecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */
class AppRepository (val remoteDatasource: RemoteDatasource, val localDatasource: LocalDatasource) : BaseDatasource{


    companion object {
        val TAG = "TAG_AppRepository"
    }

    val cache = ArrayList<Forecast>()
    val groupCache = ArrayList<City>()
    var locationId: String? = null

    override fun getForecastForLocation(location : String, period: Int) : Flowable<List<Forecast>>{
        return if(!cache.isEmpty() && location.equals(locationId)){
            Log.i(TAG, "Loading from cache")
            Flowable.just(cache)
        }else{
            Log.i(TAG, "Loading from local or remote")
            locationId = location
            loadForecastFromLocal(location, period)
        }
    }

    fun loadForecastFromLocal(location: String, period: Int) : Flowable<List<Forecast>>{
        return localDatasource.getForecastForLocation(location, period).
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


    fun loadForecastFromRemote(location: String, period: Int) : Flowable<List<Forecast>>{
        return remoteDatasource.getForecastForLocation(location, period).
                doOnNext{
                    list -> cache.clear()
                    cache.addAll(list)
                }
    }

    override fun getGroupWeather(cities: String): Flowable<List<City>> {
    return remoteDatasource.getGroupWeather(cities).doOnNext{
            it -> groupCache.clear()
            groupCache.addAll(it)
        }

    }

}
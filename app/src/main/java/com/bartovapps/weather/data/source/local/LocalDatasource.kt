package com.bartovapps.weather.data.source.local

import com.bartovapps.weather.data.source.BaseDatasource
import com.bartovapps.weather.model.global.GlobalForecast
import com.bartovapps.weather.model.local.LocalForecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */
class LocalDatasource : BaseDatasource{


    override fun getLocalWeather(location: String, period : Int) : Flowable<List<LocalForecast>>{
        return Flowable.empty()
    }


    override fun getGlobalWeather(group: String, period: Int): Flowable<List<GlobalForecast>> {
        return Flowable.empty()
    }
}
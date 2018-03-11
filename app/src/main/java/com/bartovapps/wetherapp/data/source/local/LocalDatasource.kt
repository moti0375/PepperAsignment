package com.bartovapps.wetherapp.data.source.local

import com.bartovapps.wetherapp.data.source.BaseDatasource
import com.bartovapps.wetherapp.model.global.GlobalForecast
import com.bartovapps.wetherapp.model.local.LocalForecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */
class LocalDatasource : BaseDatasource{


    override fun getLocalWeather(location: String, period : Int) : Flowable<List<LocalForecast>>{
        return Flowable.empty()
    }


    override fun getGlobalWeather(group: String): Flowable<List<GlobalForecast>> {
        return Flowable.empty()
    }
}
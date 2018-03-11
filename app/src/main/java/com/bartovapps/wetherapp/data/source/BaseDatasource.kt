package com.bartovapps.wetherapp.data.source

import com.bartovapps.wetherapp.model.global.GlobalForecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */
interface BaseDatasource {
    fun getLocalWeather(location: String, period: Int) : Flowable<List<com.bartovapps.wetherapp.model.local.LocalForecast>>

    fun getGlobalWeather(group: String) : Flowable<List<GlobalForecast>>
}
package com.bartovapps.weather.data.source

import com.bartovapps.weather.model.global.GlobalForecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */
interface BaseDatasource {
    fun getLocalWeather(location: String, period: Int) : Flowable<List<com.bartovapps.weather.model.local.LocalForecast>>

    fun getGlobalWeather(group: String, period: Int) : Flowable<List<GlobalForecast>>
}
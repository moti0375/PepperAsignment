package com.tikalk.pepper_assignment.data.source

import com.tikalk.pepper_assignment.model.group.City
import com.tikalk.pepper_assignment.model.single.Forecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */
interface BaseDatasource {
    fun getForecastForLocation(location: String, period: Int) : Flowable<List<Forecast>>

    fun getGroupWeather(group: String) : Flowable<List<City>>
}
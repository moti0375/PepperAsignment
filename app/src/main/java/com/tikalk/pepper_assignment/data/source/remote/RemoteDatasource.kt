package com.tikalk.pepper_assignment.data.source.remote

import com.tikalk.pepper_assignment.api.ApiService
import com.tikalk.pepper_assignment.data.source.BaseDatasource
import com.tikalk.pepper_assignment.model.group.City
import com.tikalk.pepper_assignment.model.single.Forecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */

class RemoteDatasource (val apiService: ApiService) : BaseDatasource{


    override fun getForecastForLocation(location: String, period: Int) : Flowable<List<Forecast>>{
        val map = hashMapOf<String, String>()
        return apiService.getForecast(location, period).map{
            it -> it.forecast
        }
    }


    override fun getGroupWeather(group: String): Flowable<List<City>> {
        return apiService.getGroupWeather(group).map {
            it -> it.city
        }
    }
}
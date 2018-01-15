package com.tikalk.pepper_assignment.data.source.local

import com.tikalk.pepper_assignment.data.source.BaseDatasource
import com.tikalk.pepper_assignment.model.group.City
import com.tikalk.pepper_assignment.model.single.Forecast
import io.reactivex.Flowable

/**
 * Created by motibartov on 14/01/2018.
 */
class LocalDatasource : BaseDatasource{


    override fun getForecastForLocation(location: String, period : Int) : Flowable<List<Forecast>>{
        return Flowable.empty()
    }


    override fun getGroupWeather(group: String): Flowable<List<City>> {
        return Flowable.empty()
    }
}
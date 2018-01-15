package com.tikalk.pepper_assignment.cities

import com.tikalk.pepper_assignment.BasePresenter
import com.tikalk.pepper_assignment.BaseView
import com.tikalk.pepper_assignment.model.group.City

/**
 * Created by motibartov on 15/01/2018.
 */
interface CitiesContract {

    interface View : BaseView{
        fun showCitiesForecast(forecast: List<City>)
    }

    interface Presenter : BasePresenter{
        fun loadCitiesForecast()
    }
}
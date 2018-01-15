package com.tikalk.pepper_assignment.forcast

import com.tikalk.pepper_assignment.BasePresenter
import com.tikalk.pepper_assignment.BaseView
import com.tikalk.pepper_assignment.model.single.Forecast

/**
 * Created by motibartov on 14/01/2018.
 */
interface ForecastContract {
    interface View : BaseView{
        fun showForecast(forecast : List<Forecast>)
    }

    interface Presenter : BasePresenter{
        fun loadForecastForLocation(id: String, period: Int)
    }
}
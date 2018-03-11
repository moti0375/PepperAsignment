package com.bartovapps.wetherapp.forcast

import com.bartovapps.wetherapp.BasePresenter
import com.bartovapps.wetherapp.BaseView
import com.bartovapps.wetherapp.model.local.LocalForecast

/**
 * Created by motibartov on 14/01/2018.
 */
interface ForecastContract {
    interface View : BaseView{
        fun showForecast(forecast : List<LocalForecast>?)
    }

    interface Presenter : BasePresenter{
        fun loadForecastForLocation(id: String, period: Int)
    }
}
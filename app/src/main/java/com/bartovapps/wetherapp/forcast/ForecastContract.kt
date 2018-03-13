package com.bartovapps.wetherapp.forcast

import com.bartovapps.wetherapp.BasePresenter
import com.bartovapps.wetherapp.BaseView
import com.bartovapps.wetherapp.model.global.GlobalForecast
import com.bartovapps.wetherapp.model.local.LocalForecast

/**
 * Created by motibartov on 14/01/2018.
 */
interface ForecastContract {
    interface View : BaseView{
        fun showForecast(forecast : List<LocalForecast>?)
        fun showDailyForecast(dailyForecast : List<GlobalForecast>?)
    }

    interface Presenter : BasePresenter{
        fun onLocationSelected(location : String)
        fun loadForecastForLocation(id: String?, period: Int)
    }
}
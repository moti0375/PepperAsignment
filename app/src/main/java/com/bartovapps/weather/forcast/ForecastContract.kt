package com.bartovapps.weather.forcast

import com.bartovapps.weather.BasePresenter
import com.bartovapps.weather.BaseView
import com.bartovapps.weather.model.global.GlobalForecast
import com.bartovapps.weather.model.local.LocalForecast

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
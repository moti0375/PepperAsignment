package com.bartovapps.weather.forcast

import com.bartovapps.weather.BasePresenter
import com.bartovapps.weather.BaseView
import com.bartovapps.weather.model.forecast.Forecast
import com.bartovapps.weather.model.daily_forecast.DailyForecast

/**
 * Created by motibartov on 14/01/2018.
 */
interface ForecastContract {
    interface View : BaseView{
        fun showForecast(forecast : List<Forecast>?)
        fun showDailyForecast(dailyForecast : List<DailyForecast>?)
    }

    interface Presenter : BasePresenter{
        fun onLocationSelected(location : String)
        fun loadForecastForLocation(id: String?, period: Int,  forceUpdate: Boolean)
    }
}
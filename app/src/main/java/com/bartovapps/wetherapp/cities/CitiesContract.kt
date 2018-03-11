package com.bartovapps.wetherapp.cities

import com.bartovapps.wetherapp.BasePresenter
import com.bartovapps.wetherapp.BaseView
import com.bartovapps.wetherapp.model.global.GlobalForecast
import com.bartovapps.wetherapp.model.local.LocalForecast

/**
 * Created by motibartov on 15/01/2018.
 */
interface CitiesContract {

    interface View : BaseView{
        fun showCitiesForecast(forecast: List<GlobalForecast>)
    }

    interface Presenter : BasePresenter{
        fun loadCitiesForecast()
    }
}
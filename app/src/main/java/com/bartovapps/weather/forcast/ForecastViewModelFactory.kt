package com.bartovapps.weather.forcast

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bartovapps.weather.data.AppRepository


class ForecastViewModelFactory(private val forecastRepository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(forecastRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
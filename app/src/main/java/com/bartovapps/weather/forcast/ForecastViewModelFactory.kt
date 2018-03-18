package com.bartovapps.weather.forcast

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.bartovapps.weather.data.AppRepository
import com.bartovapps.weather.settings.PreferencesHelper


class ForecastViewModelFactory(private val forecastRepository: AppRepository, private val preferencesHelper: PreferencesHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ForecastViewModel::class.java)) {
            return ForecastViewModel(forecastRepository, preferencesHelper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
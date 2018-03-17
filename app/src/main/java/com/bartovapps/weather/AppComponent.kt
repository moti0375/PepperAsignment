package com.bartovapps.weather

import com.bartovapps.weather.api.ApiModule
import com.bartovapps.weather.data.source.local.StorageModule
import com.bartovapps.weather.forcast.ForecastFragment
import com.bartovapps.weather.forcast.ForecastModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by motibartov on 14/01/2018.
 */

@Component(modules = arrayOf(AppModule::class, ApiModule::class, ForecastModule::class, StorageModule::class))
@Singleton
interface AppComponent {
    fun injectForecast(target: ForecastFragment)
}
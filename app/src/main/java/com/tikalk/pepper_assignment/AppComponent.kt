package com.tikalk.pepper_assignment

import com.tikalk.pepper_assignment.api.ApiModule
import com.tikalk.pepper_assignment.cities.CitiesFragment
import com.tikalk.pepper_assignment.cities.CitiesModule
import com.tikalk.pepper_assignment.forcast.ForecastFragment
import com.tikalk.pepper_assignment.forcast.ForecastModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by motibartov on 14/01/2018.
 */

@Component(modules = arrayOf(AppModule::class, ApiModule::class, ForecastModule::class, CitiesModule::class))
@Singleton
interface AppComponent {

    fun injectCities(target: CitiesFragment)
    fun injectForecast(target: ForecastFragment)
}
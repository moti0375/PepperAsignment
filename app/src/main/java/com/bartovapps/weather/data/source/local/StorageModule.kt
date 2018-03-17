package com.bartovapps.weather.data.source.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.bartovapps.weather.settings.PreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by motibartov on 17/03/2018.
 */
@Module
class StorageModule {


    @Provides
    fun provideWeatherDatabase(context: Context): WeatherDatabase? {
        return WeatherDatabase.getInstance(context)
    }

    @Provides
    fun provideWeatherDao(database: WeatherDatabase?): WeatherDao? {
        return database?.weatherDao()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun providePreferenceHelper(sharedPreferences: SharedPreferences) : PreferencesHelper {
        return PreferencesHelper(sharedPreferences)
    }


}
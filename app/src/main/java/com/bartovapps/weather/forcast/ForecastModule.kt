package com.bartovapps.weather.forcast

import com.bartovapps.weather.api.ApiService
import com.bartovapps.weather.data.AppRepository
import com.bartovapps.weather.data.source.local.LocalDatasource
import com.bartovapps.weather.data.source.local.WeatherDao
import com.bartovapps.weather.data.source.remote.RemoteDatasource
import com.bartovapps.weather.settings.PreferencesHelper
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton


@Module
class ForecastModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(dao: WeatherDao?) : LocalDatasource{
        return LocalDatasource(dao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService) : RemoteDatasource{
        return RemoteDatasource(apiService)
    }

    @Provides
    @Singleton
    fun provideRepository(localDatasource: LocalDatasource, remoteDatasource: RemoteDatasource) : AppRepository{
        return AppRepository(remoteDatasource, localDatasource)
    }

    @Provides
    @Singleton
    fun provideForecastAdapter(): ForecastAdapter {
        return ForecastAdapter()
    }

    @Provides
    @Singleton
    fun provideDailyAdapter(): DailyForecastAdapter{
        return DailyForecastAdapter()
    }

    @Provides
    @Singleton
    fun provideSdf() : SimpleDateFormat{
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    }

    @Provides
    fun provideViewModelFactory(repository: AppRepository, preferencesHelper: PreferencesHelper) : ForecastViewModelFactory{
        return ForecastViewModelFactory(repository, preferencesHelper)
    }

}
package com.bartovapps.weather.forcast

import com.bartovapps.weather.api.ApiService
import com.bartovapps.weather.data.AppRepository
import com.bartovapps.weather.data.source.local.LocalDatasource
import com.bartovapps.weather.data.source.local.WeatherDao
import com.bartovapps.weather.data.source.remote.RemoteDatasource
import dagger.Module
import dagger.Provides
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Singleton

/**
 * Created by motibartov on 14/01/2018.
 */
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
    fun provideForecastPresenter(repository: AppRepository) : ForecastPresenter{
        return ForecastPresenter(repository)
    }


    @Provides
    @Singleton
    fun provideForecastAdapter(): WeeklyForecastAdapter {
        return WeeklyForecastAdapter()
    }

    @Provides
    @Singleton
    fun provideDailyAdapter(): DailyForcastAdapter{
        return DailyForcastAdapter()
    }

    @Provides
    @Singleton
    fun provideSdf() : SimpleDateFormat{
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    }


}
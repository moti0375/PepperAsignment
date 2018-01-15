package com.tikalk.pepper_assignment.forcast

import com.tikalk.pepper_assignment.api.ApiService
import com.tikalk.pepper_assignment.data.AppRepository
import com.tikalk.pepper_assignment.data.source.local.LocalDatasource
import com.tikalk.pepper_assignment.data.source.remote.RemoteDatasource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by motibartov on 14/01/2018.
 */
@Module
class ForecastModule {

    @Provides
    @Singleton
    fun provideLocalDataSource() : LocalDatasource{
        return LocalDatasource()
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
    fun provideForecastAdapter(): ForecastRecyclerAdapter{
        return ForecastRecyclerAdapter()
    }


}
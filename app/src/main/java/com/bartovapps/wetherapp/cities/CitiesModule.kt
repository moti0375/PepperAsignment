package com.bartovapps.wetherapp.cities

import com.bartovapps.wetherapp.data.AppRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by motibartov on 15/01/2018.
 */

@Module
class CitiesModule {

    @Provides
    fun provideCitiesPresenter(repository: AppRepository) : CitiesPresenter{
        return CitiesPresenter(repository)
    }


    @Provides
    @Singleton
    fun provideCitiesAdapter() : CitiesRecyclerAdapter{
        return CitiesRecyclerAdapter()
    }
}
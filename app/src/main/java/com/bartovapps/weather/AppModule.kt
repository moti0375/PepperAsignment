package com.bartovapps.weather

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by motibartov on 14/01/2018.
 */

@Module
class AppModule(var application: MyApplication){

    @Provides
    @Singleton
    fun provideAppContext() : Context{
        return this.application
    }
}
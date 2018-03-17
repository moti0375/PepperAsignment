package com.bartovapps.weather.data.source.local

import android.arch.persistence.room.OnConflictStrategy
import io.reactivex.Flowable
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.bartovapps.weather.model.daily_forecast.DailyForecast
import com.bartovapps.weather.model.forecast.Forecast


/**
 * Created by motibartov on 17/03/2018.
 */
@Dao
interface WeatherDao {

    @Query("SELECT * FROM forecast")
    fun getForecast() : Flowable<List<Forecast>>

    @Query("SELECT * FROM daily_forecast")
    fun getDailyForecast() : Flowable<List<DailyForecast>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForefcast(forecast: List<Forecast>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDailyForecast(forecast: List<DailyForecast>)


    @Query("DELETE FROM forecast")
    fun deleteAllForecast()

    @Query("DELETE FROM daily_forecast")
    fun deleteAllDailyForecast()
}
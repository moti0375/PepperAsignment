package com.bartovapps.weather.data.source.local

import android.arch.persistence.room.*
import com.bartovapps.weather.model.daily_forecast.DailyForecast
import com.bartovapps.weather.model.forecast.Forecast
import android.content.Context
import com.bartovapps.weather.data.source.local.type_converters.DailyTempConverter
import com.bartovapps.weather.data.source.local.type_converters.MainTypeConverter
import com.bartovapps.weather.data.source.local.type_converters.WeatherTypeConverter


/**
 * Created by motibartov on 17/03/2018.
 */
@Database(entities = arrayOf(Forecast::class, DailyForecast::class), version = 1)
@TypeConverters(WeatherTypeConverter::class, DailyTempConverter::class, MainTypeConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        private var INSTANCE: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase? {
            if (INSTANCE == null) {
                synchronized(WeatherDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WeatherDatabase::class.java, "weather.db")
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
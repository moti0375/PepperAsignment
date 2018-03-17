package com.bartovapps.weather.model.daily_forecast

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.bartovapps.weather.model.Weather
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by motibartov on 14/01/2018.
 */
@Entity(tableName = "daily_forecast")
data class DailyForecast(
        @PrimaryKey
        @SerializedName("dt")
        @Expose
        var dt: Long,
        @SerializedName("temp")
        @Expose
        var temp: DailyTemp? = null,
        @SerializedName("weather")
        @Expose
        var weather: List<Weather>

)
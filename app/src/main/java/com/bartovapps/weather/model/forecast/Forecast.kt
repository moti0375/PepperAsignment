package com.bartovapps.weather.model.forecast

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.bartovapps.weather.model.Weather


@Entity(tableName = "forecast")
data class Forecast(
        @PrimaryKey
        @SerializedName("dt")
        @Expose
        var dt: Long,

        @SerializedName("main")
        @Expose
        var main: Main,

        @Expose
        @SerializedName("weather")
        var weather: List<Weather>

)
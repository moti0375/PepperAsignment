package com.bartovapps.weather.model.daily_forecast

import com.bartovapps.weather.model.City
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DailyResponse(
        @SerializedName("city")
                         @Expose
                         var city: City,
        @SerializedName("list")
                         @Expose
                         var dailyForecast: List<DailyForecast>)

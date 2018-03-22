package com.bartovapps.weather.model.forecast

import com.bartovapps.weather.model.City
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class ForecastResponse(

        @SerializedName("cnt")
        @Expose
        var cnt: Int? = null,
        @SerializedName("city")
        @Expose
        var city: City,
        @SerializedName("list")
        @Expose
        var forecast: List<Forecast>? = null

)
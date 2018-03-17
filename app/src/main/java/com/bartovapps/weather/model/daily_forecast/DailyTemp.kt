package com.bartovapps.weather.model.daily_forecast

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by motibartov on 14/01/2018.
 */
data class DailyTemp(
        @SerializedName("day")
        @Expose
        var day: Double? = null,
        @SerializedName("min")
        @Expose
        var min: Double? = null,
        @SerializedName("max")
        @Expose
        var max: Double? = null

)
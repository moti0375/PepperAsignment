package com.tikalk.pepper_assignment.model.single

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by motibartov on 14/01/2018.
 */
data class Forecast(
        @SerializedName("dt")
        @Expose
        var dt: Long,
        @SerializedName("temp")
        @Expose
        var temp: Temp? = null,
        @SerializedName("weather")
        @Expose
        var weather: List<Weather>

)